package cn.whaifree.springdemo.controller.interceptRetry.aspect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import cn.whaifree.springdemo.controller.interceptRetry.BuinessException;
import cn.whaifree.springdemo.controller.interceptRetry.ErrorType;
import com.github.benmanes.caffeine.cache.Cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import com.mysql.cj.util.LogUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.apache.catalina.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/9 21:57
 * @注释
 */
@Aspect
@Component
@Order(100)
public class RetryAspect  {

    /**
     * 一个Cache只能有一个有效期，
     * 所以要根据有效期进行分组
     * <p>
     * 【exprieTime，【keyOfIp，time】】
     * <p>
     * <p>
     * 对不同时间用不同cache实现
     */
    Map<Integer, Cache<String, AtomicInteger>> cacheMap = new HashMap<>();

    @Resource
    @Lazy
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 这里有疑问，可能拿到RetryAspect走getBean导致循环依赖，RetryAspect循环依赖RetryAspect
            if (beanDefinitionName.equalsIgnoreCase(this.getClass().getSimpleName())) {
                continue;
            }
            Object bean = applicationContext.getBean(beanDefinitionName);

            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    // 手动用反射获取不到注解
                    RetryLimit retryLimit = AnnotationUtils.findAnnotation(method, RetryLimit.class);
//                    RetryLimit retryLimit = method.getAnnotation(RetryLimit.class);
                    if (retryLimit == null) {
                        continue;
                    }
                    int expireTime = retryLimit.limitTime();
                    Cache<String, AtomicInteger> build = Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(expireTime)).maximumSize(1000).build();
                    cacheMap.put(expireTime, build);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(cacheMap);
    }



    @Around("@annotation(cn.whaifree.springdemo.controller.interceptRetry.aspect.RetryLimit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名，设置方法可以访问
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        method.setAccessible(true);

        // 获取注解
        RetryLimit retryAn = AnnotationUtils.findAnnotation(method, RetryLimit.class);
        if (retryAn == null) {
            return joinPoint.proceed();
        }

        // 如果包含注解，放入缓存，key为ip或者其他限流key，value为次数
        Cache<String, AtomicInteger> cache = cacheMap.get(retryAn.limitTime());
        String limitKey = retryAn.limitKey();
        if (limitKey == null || limitKey.equals("ip")) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            limitKey = request.getRemoteAddr();
        }else if (limitKey.equals("userId")) {
            // 其他策略
        }
        // 如果缓存中没有这个ip访问过，初始化为0
        AtomicInteger atomicInteger = cache.get(limitKey, s -> new AtomicInteger(0));
        if (atomicInteger.intValue() >= retryAn.limitCount()) {
            throw new RuntimeException(retryAn.resMsg());
        }

        try {
            return joinPoint.proceed();
        } catch (BuinessException e) {
            // 如果不是验证错误，向上抛出
            if (!e.getType().equals(ErrorType.RetryType)) {
                throw e;
            }
            // 如果验证错误，对atomic++
            atomicInteger.incrementAndGet();
            String msg = retryAn.resMsg() + "，重试次数：" + atomicInteger.intValue();
            throw new RuntimeException(msg);
        }
    }


}
