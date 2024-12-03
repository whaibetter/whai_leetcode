package com.whai.springcloud.servicea.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
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
public class DynamicThreadPoolController implements InitializingBean {

    static ThreadPoolExecutor executor ;
    @Resource
    private ConfigurableEnvironment environment;


    @GetMapping("/dynamicCoreSize")
    public String dynamicCoreSize() {
        return "Dynamic Core Size: " + executor.getCorePoolSize();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Integer corePoolSize = environment.getProperty("dynamic.coreSize", Integer.class);
        Integer maximumPoolSize = environment.getProperty("dynamic.maxSize", Integer.class);
        executor = new ThreadPoolExecutor(
                Optional.of(corePoolSize).orElse(5), // 如果corePoolSize为null，则默认为5
                Optional.of(maximumPoolSize).orElse(10),
                10, TimeUnit.SECONDS,
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
}

@Configuration
@RefreshScope
@EnableConfigurationProperties(ThreadConfigProperties.class) // 自动引入Properties，会自动加入Context
class DynamicThreadPoolConfig implements ApplicationListener<EnvironmentChangeEvent> {


    @Resource
    ThreadConfigProperties threadConfigProperties;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        System.out.println("DynamicThreadPoolConfig.onApplicationEvent");
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
