package com.whai.springcloud.serviceb.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.shaded.com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 17:58
 * @注释
 */
@RestController
@RefreshScope
public class BProviderController {

    private final Environment environment;

    private RateLimiter rateLimiter;

    @Autowired
    public BProviderController(Environment environment, @Qualifier("nacosConfigManager") NacosConfigManager nacosConfigManager) {
        this.environment = environment;
        initRateLimiter();
        ConfigService configService = nacosConfigManager.getConfigService();
        try {
            configService.addListener("cvm.reset.ratelimit", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("收到配置变更：cvm.reset.ratelimit" + configInfo);
                    rateLimiter.setRate(Double.parseDouble(configInfo));
                }
            });
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }

    }
    @Value("${cvm.reset.ratelimit:0}")
    private int rateLimitCount;
    private void initRateLimiter() {
        if (rateLimitCount <= 0) {
            rateLimitCount = 1;
        }
        System.out.println("初始化限流器:" + rateLimitCount);
        rateLimiter = RateLimiter.create(rateLimitCount);
    }
    // 提供服务
    @RequestMapping("/getB")
    public String getB(String msg) {
        return StrUtil.format("BProviderController.getB, msg: {}, port: {}", msg, environment.getProperty("server.port"));
    }

    @RequestMapping("/getProper")
    public int getProper() {
        return environment.getProperty("cvm.reset.ratelimit", Integer.class);
    }



    @PostMapping("/CVM/reset")
    public String reset(@RequestBody List<String> InstanceIds) {
        System.out.println("当前限流数：" + rateLimiter.getRate());
        List<String> succ = new ArrayList<>();
        for (String instanceId : InstanceIds) {
            if (rateLimiter.tryAcquire()) {
                // 处理重置请求
                succ.add(instanceId);
            }else {
                return "请求过于频繁，请稍后重试";
            }
        }
        return StrUtil.format("重置成功，成功实例：{}", succ);
    }

}
