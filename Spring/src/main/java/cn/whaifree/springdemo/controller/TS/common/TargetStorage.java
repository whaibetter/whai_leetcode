package cn.whaifree.springdemo.controller.TS.common;

import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 21:21
 * @注释
 */
@Component
public class TargetStorage implements ProcessStrategy {

    @Override
    public void process(byte[] frame) {
        // 封装
        process(frame);
    }

    @Override
    public void process(Object o) {
        System.out.println("TargetStorage");
    }
}


