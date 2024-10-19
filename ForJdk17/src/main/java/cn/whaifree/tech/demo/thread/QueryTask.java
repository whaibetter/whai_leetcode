package cn.whaifree.tech.demo.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryTask implements Runnable {
    private final String queryId;
    private final int sleepTime; // 模拟查询耗时
    private static final AtomicInteger counter = new AtomicInteger(0); // 计数器
    private static final CountDownLatch latch = new CountDownLatch(10); // 计数器锁存器

    public QueryTask(String queryId, int sleepTime) {
        this.queryId = queryId;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime); // 模拟查询耗时
            System.out.println("查询 " + queryId + " 完成");
            counter.incrementAndGet(); // 安全地增加计数器
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // 完成一个任务，计数器减一
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, // 核心线程数
                10, // 最大线程数
                60, // 空闲线程存活时间
                TimeUnit.SECONDS, // 存活时间单位
                new LinkedBlockingQueue<>() // 任务队列
        );

        // 提交任务
        for (int i = 0; i < 10; i++) {
            executor.submit(new QueryTask("Query" + i, 3000));
        }
        /**
         * latch.await开始
         * 执行效果，5个核心线程先提交，等待3秒后完成，并对AtomicInteger counter依次++，counter此时为5
         * 下一波5个线程再提交，latch此时满足10个，await结束
         */

        // 等待所有任务完成
        latch.await();

        // 关闭线程池
        executor.shutdown();

        // 输出结果
        System.out.println("所有查询已完成，查询总数：" + counter.get());
    }
}

