package cn.whaifree.springdemo.config;

import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/24 16:38
 * @注释
 */
@Configuration
public class MyAutoConfiguration {


    @Resource
    private MyProperties myProperties;


    @Bean(name = "whaiThreadPool")
    @Conditional(value = {MyCondition.class})
    public ThreadPoolExecutor myService(MyProperties myProperties) {
        return new ThreadPoolExecutor(
                myProperties.getCoreSize(),
                myProperties.getMaxSize(),
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
    }

}


@Component
@ConfigurationProperties(prefix = "my.thread")
class MyProperties{
    String name = "whai";
    int coreSize = 1;
    int maxSize = 2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}

class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("my.condition");
        if ("true".equals(property)) {
            return true;
        }
        System.out.println("未加载");
        return false;
    }

}
