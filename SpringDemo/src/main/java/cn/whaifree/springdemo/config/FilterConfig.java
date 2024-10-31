package cn.whaifree.springdemo.config;

import cn.whaifree.springdemo.utils.Filter.SelfFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 22:15
 * @注释
 */
@Slf4j
@Configuration
public class FilterConfig {



    @Bean
    public FilterRegistrationBean<SelfFilter> customFilterRegistration() {
        FilterRegistrationBean<SelfFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SelfFilter());
        registration.addUrlPatterns("/*"); // 指定过滤器应用的 URL 模式
        registration.setName("customFilter");
        registration.setOrder(1); // 设置过滤器的顺序
        return registration;
    }


}
