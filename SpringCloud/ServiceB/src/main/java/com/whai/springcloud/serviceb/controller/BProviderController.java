package com.whai.springcloud.serviceb.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 17:58
 * @注释
 */
@RestController
public class BProviderController {

    private final Environment environment;

    @Autowired
    public BProviderController(Environment environment) {
        this.environment = environment;
    }
    // 提供服务
    @RequestMapping("/getB")
    public String getB(String msg) {
        return StrUtil.format("BProviderController.getB, msg: {}, port: {}", msg, environment.getProperty("server.port"));
    }


}
