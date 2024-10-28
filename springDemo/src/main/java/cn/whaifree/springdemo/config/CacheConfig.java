package cn.whaifree.springdemo.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Collection;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 22:44
 * @注释
 */
@Configuration
@EnableCaching()
@EnableTransactionManagement()
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new CustomCacheManager();
    }
    // 自定义的 CacheManager，装饰每个缓存为 TransactionAwareCacheDecorator
    static class CustomCacheManager implements CacheManager {
        @Override
        public Cache getCache(String name) {
            Cache cache = new ConcurrentMapCache(name);  // 使用 ConcurrentMapCache 实现缓存
            return new TransactionAwareCacheDecorator(cache); // 包装为 TransactionAwareCacheDecorator
        }

        @Override
        public Collection<String> getCacheNames() {
            return Arrays.asList("myCache"); // 设定缓存的名称
        }
    }

}
