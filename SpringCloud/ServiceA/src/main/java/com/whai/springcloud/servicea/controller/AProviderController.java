package com.whai.springcloud.servicea.controller;

import com.whai.springcloud.servicea.service.BService;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 17:58
 * @注释
 */
@RestController
public class AProviderController {

    @Resource
    private BService bService;


    @GetMapping("/hello")
    public String hello(String msg) {
        System.out.println("A---->B");
        return bService.getB(msg);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/getBDiscovey")
    public String getBDiscovey(){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-b");
        ServiceInstance serviceInstance = instances.get(0);
        return restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getB?msg=hello", String.class);
    }
}

