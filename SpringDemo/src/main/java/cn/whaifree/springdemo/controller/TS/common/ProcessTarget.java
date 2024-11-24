package cn.whaifree.springdemo.controller.TS.common;

import cn.hutool.extra.spring.SpringUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 20:48
 * @注释
 */
@Component
public class ProcessTarget {

    public static final int TARGET_FIND = processToInt(new byte[]{0x00, 0x00, 0x00, 0x00});
    public static final int TARGET_DOWN = processToInt(new byte[]{0x00, 0x00, 0x00, 0x01});

    static HashMap<Integer, ProcessStrategy> processStrategyHashMap = new HashMap<>();

    @PostConstruct
    public void init() {
        processStrategyHashMap.put(TARGET_FIND, SpringUtil.getBean(TargetStorage.class));
        processStrategyHashMap.put(TARGET_DOWN, SpringUtil.getBean(TargetDown.class));
    }

    private static int processToInt(byte[] heads) {
        if (heads.length < 4) {
            return -1;
        }
        // 获取前4个byte转为int
        return (heads[0] & 0xFF) << 24 | (heads[1] & 0xFF) << 16 | (heads[2] & 0xFF) << 8 | (heads[3] & 0xFF);
    }

    public static ProcessStrategy getProcessStrategy(byte[] heads) {
        return processStrategyHashMap.get(processToInt(heads));
    }

    public static ProcessStrategy getProcessStrategy(int code) {
        return processStrategyHashMap.get(code);
    }

}


