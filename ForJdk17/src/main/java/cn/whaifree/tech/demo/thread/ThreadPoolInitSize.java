package cn.whaifree.tech.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/18 22:37
 * @注释
 */
public class ThreadPoolInitSize {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 6, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
        System.out.println(executor);
        executor.submit(() -> System.out.println(1));
        executor.submit(() -> System.out.println(1));
        Thread.sleep(1000);
        System.out.println(executor);

        /**
         * java.util.concurrent.ThreadPoolExecutor@1ddc4ec2[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
         * 1 逐渐创建
         * 1
         * java.util.concurrent.ThreadPoolExecutor@1ddc4ec2[Running, pool size = 2, active threads = 0, queued tasks = 0, completed tasks = 2]
         */
    }

}
