package cn.whaifree.tech.thread;

import com.github.phantomthief.pool.KeyAffinityExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 12:53
 * @注释
 */
public class AffinityThreadPoolTest {

    final static Object o = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        long l = System.currentTimeMillis();

        synchronized (o) {
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " A " + finalI);

                });

                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " B "+ finalI);

                });
            }
        }




        KeyAffinityExecutor executor = KeyAffinityExecutor.newSerializingExecutor(8,200, "MY-POOL");

        l = System.currentTimeMillis();
        synchronized (o) {
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                executor.submit("A", () -> {
                    System.out.println(Thread.currentThread().getName() + " A " + finalI);
                    return null;
                });
                executor.submit("B", () -> {
                    System.out.println(Thread.currentThread().getName() + " B "+ finalI);
                    return null;
                });
            }
        }
        System.out.println(System.currentTimeMillis() - l);


    }


    /**
     * 效果，相同key的任务可以顺序执行
     * @param args
     */
    public static void main1(String[] args) {
        AffinityThreadPool affinityThreadPool = new AffinityThreadPool();

        // 提交任务
        affinityThreadPool.submitTask("key1", () -> System.out.println("Task 1 - Key 1"));
        affinityThreadPool.submitTask("key1", () -> System.out.println("Task 2 - Key 1"));
        affinityThreadPool.submitTask("key2", () -> System.out.println("Task 1 - Key 2"));
        affinityThreadPool.submitTask("key2", () -> System.out.println("Task 2 - Key 2"));

        // 关闭线程池
        affinityThreadPool.shutdown();
    }
}



class AffinityThreadPool {




    private final Map<String, ExecutorService> threadPools;
    private final AtomicInteger counter; // 当前线程池数量

    public AffinityThreadPool() {
        this.threadPools = new ConcurrentHashMap<>();
        this.counter = new AtomicInteger(0);
    }

    /**
     * 根据键获取或创建单线程执行器
     * @param key 键
     * @return 单线程执行器
     */
    private synchronized ExecutorService getOrCreateThreadPool(String key) {
        ExecutorService executor = threadPools.get(key);
        if (executor == null) {
            // 创建一个新的单线程执行器
            executor = Executors.newSingleThreadExecutor();
            threadPools.put(key, executor);
        }
        return executor;
    }

    /**
     * 提交任务
     * @param key 键
     * @param task 任务
     */
    public void submitTask(String key, Runnable task) {
        ExecutorService executor = getOrCreateThreadPool(key);
        executor.submit(task);
    }

    /**
     * 关闭所有线程池
     */
    public void shutdown() {
        for (ExecutorService executor : threadPools.values()) {
            executor.shutdown();
        }
    }


}
