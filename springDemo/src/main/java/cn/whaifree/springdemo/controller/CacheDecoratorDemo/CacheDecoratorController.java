package cn.whaifree.springdemo.controller.CacheDecoratorDemo;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 22:43
 * @注释
 */
@RestController
public class CacheDecoratorController {

    private final SpringUtil springUtil;
    private final RedisCacheManager cacheManager;

    public CacheDecoratorController(SpringUtil springUtil, RedisCacheManager cacheManager) {
        this.springUtil = springUtil;
        this.cacheManager = cacheManager;
    }

    @RequestMapping("/getData")
    public String getData(Long id) {
        // 先从缓存中获取数据
        String data = SpringUtil.getBean(CacheDecoratorController.class).getDataById(id);
        return data;
    }

    @RequestMapping("/update")
    public void update(Long id, String newValue) {
        // 更新数据
        SpringUtil.getBean(CacheDecoratorController.class).updateData(id, newValue);
    }

    @Cacheable(value = "myCache", key = "#id")
    public String getDataById(Long id) {
        // 模拟获取数据操作
        return "Data for ID " + id;
    }

    @Transactional
    @CachePut(value = "myCache", key = "#id", condition = "#result != null")
    public String updateData(Long id, String newValue) {
        // 更新数据库操作
        // ...
        // 返回的新值会在事务提交后自动更新到缓存中
        // return newValue;


        // 或者手动操作缓存
        Cache myCache = cacheManager.getCache("myCache");
        if (myCache != null) {
            myCache.put(id, newValue);
            return null;
        }
        return null;
    }

}
