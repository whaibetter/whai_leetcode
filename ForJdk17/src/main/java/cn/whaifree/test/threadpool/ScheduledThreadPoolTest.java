package cn.whaifree.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 16:40
 * @注释
 */
public class ScheduledThreadPoolTest  {
    volatile static Integer i = 0;

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay:" + i);
            }
        }, 10, TimeUnit.SECONDS);
        // 10秒后才执行
        scheduledExecutorService.shutdown();


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(finalJ);
                }
            });
        }



    }
}
