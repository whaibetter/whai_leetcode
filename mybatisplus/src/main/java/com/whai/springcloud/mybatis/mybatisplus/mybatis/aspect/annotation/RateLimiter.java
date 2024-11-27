package com.whai.springcloud.mybatis.mybatisplus.mybatis.aspect.annotation;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:43
 * @注释
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {


}
