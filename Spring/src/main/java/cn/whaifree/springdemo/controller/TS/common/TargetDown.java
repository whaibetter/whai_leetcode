package cn.whaifree.springdemo.controller.TS.common;

import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 21:22
 * @注释
 */
@Component
public class TargetDown implements ProcessStrategy {

    @Override
    public void process(byte[] frame) {


        // 封装成object
        process(frame);
    }

    @Override
    public void process(Object o) {
        System.out.println("TargetDown");

    }
}
