//package cn.whaifree.springdemo.config;
//
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import java.util.ArrayList;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @author : 村雨遥
// * @version : 1.0
// * @project : springboot-swagger3-demo
// * @package : com.cunyu.springbootswagger3demo.config
// * @className : SwaggerConfig
// * @createTime : 2022/1/6 14:19
// * @email : 747731461@qq.com
// * @微信 : cunyu1024
// * @公众号 : 村雨遥
// * @网站 : https://cunyu1943.github.io
// * @description :
// */
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("接口文档标题")
//                        .description("SpringBoot3 集成 Swagger3接口文档")
//                        .version("v1"))
//                .externalDocs(new ExternalDocumentation()
//                        .description("项目API文档")
//                        .url("/"));
//    }
//}
