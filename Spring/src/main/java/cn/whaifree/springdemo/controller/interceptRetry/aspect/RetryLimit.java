package cn.whaifree.springdemo.controller.interceptRetry.aspect;

import cn.whaifree.springdemo.controller.interceptRetry.ErrorType;
import cn.whaifree.springdemo.utils.ResVo;
import org.springframework.data.redis.connection.ReturnType;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/9 21:57
 * @注释
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetryLimit {

    int limitCount() default 3;
    int limitTime() default 60;
    String limitKey() default "ip";
    String resMsg() default "请求过于频繁";
    ErrorType errorType() default ErrorType.RetryType;

}
