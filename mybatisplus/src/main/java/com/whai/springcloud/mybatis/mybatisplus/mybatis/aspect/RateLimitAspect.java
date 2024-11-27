package com.whai.springcloud.mybatis.mybatisplus.mybatis.aspect;

import com.whai.springcloud.mybatis.mybatisplus.mybatis.aspect.annotation.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:46
 * @注释
 */
@Aspect
@Component
public class RateLimitAspect {


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
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        System.out.println("rateLimitAspect");
    }

}
