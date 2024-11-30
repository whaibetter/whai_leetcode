package cn.whaifree.tech.ThreadLocalDemo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/28 10:36
 * @注释
 */
public class TTLDemo {

    /**
     * 父子线程传递
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        InheritableThreadLocal<String> context1 = new InheritableThreadLocal<>();

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        // =====================================================

        // 在父线程中设置

        context.set("value-set-in-parent");

        // =====================================================

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在子线程中可以读取，值是"value-set-in-parent"
                String value = context.get();
                System.out.println(value);
            }
        }).start();
    }

    /**
     * 线程池+TTL
     */
    @Test
    public void test2() throws InterruptedException {
        TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
        threadLocal.set("hello");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 在线程池中执行任务
        for (int i = 0; i < 10; i++) {
            executorService.execute(TtlRunnable.get(() -> {
                String value = threadLocal.get();
                System.out.println(StrUtil.format("{}:{}", Thread.currentThread().getName(), value));
            }));
        }

        // 等待任务执行完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    /**
     * 使用 TtlExecutors
     */
    @Test
    public void test3() throws InterruptedException {
        TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
        threadLocal.set("hello");
        threadLocal.set("world");
        // 本质是一个包装设计模式，在传入的时候传输Runnable或者Callable都会被自动转为TtlRunnable
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(10));
        // 在线程池中执行任务
        for (int i = 0; i < 10; i++) {
            ttlExecutorService.execute(() -> {
                String value = threadLocal.get();
                System.out.println(StrUtil.format("{}:{}", Thread.currentThread().getName(), value));
            });
        }
        // 等待任务执行完成
        ttlExecutorService.shutdown();
        ttlExecutorService.awaitTermination(1, TimeUnit.SECONDS);
    }

}
