package cn.whaifree.springdemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 20:56
 * @注释
 */
@Configuration
public class RedissonConfig {

//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        config.setThreads(10);
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//    }
}
