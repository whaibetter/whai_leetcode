package cn.whaifree.springdemo.controller.interceptRetry;

import lombok.Getter;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/10 15:26
 * @注释
 */
public class BuinessException extends RuntimeException {
    @Getter
    private ErrorType type;
    private Integer code;


    public BuinessException(ErrorType type, Integer code) {
        this.type = type;
        this.code = code;
    }
}

