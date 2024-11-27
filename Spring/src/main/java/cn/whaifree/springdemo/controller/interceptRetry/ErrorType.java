package cn.whaifree.springdemo.controller.interceptRetry;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/10 15:26
 * @注释
 */
public enum ErrorType {
    RetryType("Retry Too Many", 503);

    private String type;
    private Integer code;
    ErrorType (String type, Integer code) {
        this.type = type;
        this.code = code;
    }
}
