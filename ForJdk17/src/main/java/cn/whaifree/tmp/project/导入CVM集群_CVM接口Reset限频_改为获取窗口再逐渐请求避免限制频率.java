package cn.whaifree.tmp.project;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/17 18:09
 * @注释
 */
@Slf4j
public class 导入CVM集群_CVM接口Reset限频_改为获取窗口再逐渐请求避免限制频率 {


    public static void originMethod(List<String> instanceIds) {
        /**
         * 不限制
         */
        try {
            for (String instanceId : instanceIds) {
                boolean reset = MasterAPI.reset(instanceId);
                if (!reset) {
                    throw new RuntimeException("RateLimit");
                }
            }
        } catch (Exception e) {
            System.out.println("RateLimit");
        }
    }


    public static List<Integer> advanceMethod(List<String> instanceIds) throws InterruptedException {
        List<Integer> result = new ArrayList<>();
        int rateLimit = RateLimiterExecutor.rateLimit;// 从配置中心获取
        for (int i = 0; i < instanceIds.size(); i += rateLimit) {
            int end = i + rateLimit;
            if (end > instanceIds.size()) {
                end = instanceIds.size();
            }
            List<String> subList = instanceIds.subList(i, end);

            while (!MasterAPI.reset(subList)) {
                log.info("{} RateLimit Retry After 500ms", Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }
        return result;
    }


    public static void main(String[] args) throws InterruptedException {

        List<String> instanceIds = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            instanceIds.add("i-" + i);
        }

        originMethod(instanceIds);

        Thread.sleep(1000);

        System.out.println("=====================================");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    advanceMethod(instanceIds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    advanceMethod(instanceIds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    class MasterAPI{

        public static boolean reset(List<String> instanceIds) {
            for (String instanceId : instanceIds) {
                boolean reset = reset(instanceId);
                if (!reset) {
                    return false;
                }
            }
            return true;
        }
        public static boolean reset(String instanceId) {
            // 限频模拟
            if (RateLimiterExecutor.allowRequest()) {
                log.info(StrUtil.format("reset: {}", instanceId));
                return true;
            }else {
                log.error(StrUtil.format("reset: {} RateLimit !!!!!", instanceId));
                return false;
            }
        }
    }

    class RateLimiterExecutor{
        static AtomicInteger times = new AtomicInteger(0); // 次数
        static long startTime = 0; // 开始时间
        static int rateLimit = 10; // 限流频率，1秒允许多少次

        /**
         * 是否允许请求
         * @return
         */
        public static boolean allowRequest() {
            if (times.get() == 0) {
                // 开始计数
                startTime = System.currentTimeMillis();
            }
            // 1000 为窗口大小
            if (System.currentTimeMillis() - startTime > 1000) {
                // 超过1秒,重新统计窗口
                times.set(0);
                startTime = System.currentTimeMillis();
            }
            int i = times.incrementAndGet();
            // 触发限频,重新统计窗口
            return i <= rateLimit;
        }
    }


}
