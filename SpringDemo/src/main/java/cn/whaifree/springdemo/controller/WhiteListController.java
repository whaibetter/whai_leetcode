package cn.whaifree.springdemo.controller;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 21:29
 * @注释
 */
@RestController
public class WhiteListController {

    @Resource
    private RedisTemplate redisTemplate;
    @PostMapping("/queryIn")
    public boolean query(String userId) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember("whiteList", userId));
    }
}
