package cn.whaifree.springdemo.aspect.annotation;

import cn.whaifree.springdemo.constant.CacheConstants;
import cn.whaifree.springdemo.constant.LimitType;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:43
 * @注释
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    /**
     * 限流key
     */
    public String key() default CacheConstants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    public int time() default 60;

    /**
     * 限流次数
     */
    public int count() default 100;

    /**
     * 限流类型
     */
    public LimitType limitType() default LimitType.DEFAULT;
}
