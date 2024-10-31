package cn.whaifree.springdemo.config;

import cn.whaifree.springdemo.utils.ResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/11 16:28
 * @注释
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    public ResVo handleException(Exception e) {
        log.error("系统异常", e);
        return ResVo.error(e.getMessage());
    }

}
