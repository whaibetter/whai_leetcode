package cn.whaifree.springdemo.aspect;

import cn.whaifree.springdemo.aspect.annotation.RateLimiter;
import cn.whaifree.springdemo.constant.LimitType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:46
 * @注释
 */
@Aspect
@Component
public class RateLimitAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimitAspect.class);

    private RedisTemplate<Object, Object> redisTemplate;

    private RedisScript<Long> limitScript;

    @Autowired
    public void setRedisTemplate1(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setLimitScript(RedisScript<Long> limitScript) {
        this.limitScript = limitScript;
    }

    /**
     * key 对应方法，value 已经被访问的次数
     *
     * lua的逻辑：
     *
     *
     *
     * @param point
     * @param rateLimiter
     * @throws Throwable
     */
    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        int time = rateLimiter.time();  // 多长时间
        int count = rateLimiter.count(); // 允许次数


        String combineKey = getCombineKey(rateLimiter, point); // 组合key， Class-Method
        List<Object> keys = Collections.singletonList(combineKey);
        try {
            Long number = redisTemplate.execute(limitScript, keys, count, time);
            if (Objects.isNull(number) || number.intValue() > count) { // 如果超过限额，报错
                throw new Exception("访问过于频繁，请稍候再试");
            }
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), combineKey);
        } catch (Exception e) {

            throw e;
        }
    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key()); // 获取key
//        if (rateLimiter.limitType() == LimitType.IP) {
//            stringBuffer.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append("-");
//        }
        if (rateLimiter.limitType() == LimitType.USER) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String username = attributes.getRequest().getParameter("userId");
            if (username != null) {
                stringBuffer.append(username).append("-");
            }else {
                throw new RuntimeException("用户id为空"); // 抛出异常，禁止继续执行，防止缓存穿透，缓存穿透指缓存中不存在该key，但是数据库中存在该ke
            }
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}
