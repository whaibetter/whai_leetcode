package com.whai.springcloud.mybatis.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@SpringBootApplication
@MapperScan("com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MybatisplusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisplusApplication.class, args);
    }

}
