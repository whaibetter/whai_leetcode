package cn.whaifree.springdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 12:41
 * @注释
 */
@Configuration
@MapperScan("cn.whaifree.springdemo.mybatis.mapper")
public class MybatisConfig {

}
