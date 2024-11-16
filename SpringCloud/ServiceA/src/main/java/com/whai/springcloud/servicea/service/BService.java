package com.whai.springcloud.servicea.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 17:58
 * @注释
 */
@Service
@FeignClient("ServiceB")
public interface BService {

    @GetMapping("/getB")
    String getB(@RequestParam("msg") String msg);

}
