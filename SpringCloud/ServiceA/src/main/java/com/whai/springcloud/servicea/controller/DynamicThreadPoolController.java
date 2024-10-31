package com.whai.springcloud.servicea.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 21:51
 * @注释
 */
@RestController
public class DynamicThreadPoolController {

    static ThreadPoolExecutor executor ;
    @Resource
    private ConfigurableEnvironment environment;

    @PostConstruct
    public void init() {


        executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("DynamicThreadPoolController");
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
    }


    @GetMapping("/dynamicCoreSize")
    public String dynamicCoreSize() {
        return "Dynamic Core Size: " + executor.getCorePoolSize();
    }

}

@Configuration
@RefreshScope
@EnableConfigurationProperties(ThreadConfigProperties.class) // 自动引入Properties，会自动加入Context
class DynamicThreadPoolConfig implements ApplicationListener<EnvironmentChangeEvent> {


    @Resource
    ThreadConfigProperties threadConfigProperties;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        if (event.getKeys().contains("dynamic.coreSize")) {
            // 获取值
            Integer coreSize = threadConfigProperties.getCoreSize();
            // 更新线程池
            DynamicThreadPoolController.executor.setCorePoolSize(coreSize);
        }
    }
}

@ConfigurationProperties(prefix = "dynamic")
class ThreadConfigProperties {
    int coreSize;
    int maxSize;
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public int getCoreSize() {
        return coreSize;
    }
    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }
}
