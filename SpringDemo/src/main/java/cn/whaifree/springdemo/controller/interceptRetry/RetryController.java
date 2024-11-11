package cn.whaifree.springdemo.controller.interceptRetry;

import cn.whaifree.springdemo.controller.interceptRetry.aspect.RetryLimit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/9 21:56
 * @注释
 */
@RestController
public class RetryController {

    @PostMapping("/try")
    @RetryLimit(limitCount = 3, limitTime = 1, limitKey = "ip", resMsg = "retry请求频繁")
    public String tryMethod(int success) {
        if (success == 1) {
            throw new BuinessException(ErrorType.RetryType, 500);
        }
        return "tryMethod";
    }
}
