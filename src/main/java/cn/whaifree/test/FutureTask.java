package cn.whaifree.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/30 17:14
 * @注释
 */
public class FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个异步任务，该任务返回一个整数
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
                new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        System.out.println("A complete");
                        return 10;
                    }
                }
        );

        // 创建另一个异步任务，该任务同样返回一个整数
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("B start");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("B complete");
                    return 20;
                }
        );

        // 两个异步任务完成计算后，将它们的结果相加
        CompletableFuture<Object> ans = future.thenCombine(future2, new BiFunction<Integer, Integer, Object>() {
                    @Override
                    public Object apply(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }
        );

        // 打印结果
        System.out.println(ans.get());


        // callable创建线程
        java.util.concurrent.FutureTask<Integer> futureTask = new java.util.concurrent.FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 暂停100毫秒
                Thread.sleep(100);
                return 100;
            }
        });



        // 启动线程
        new Thread(futureTask).start();



        // 等待futureTask完成
        while (!futureTask.isDone()) {
            Thread.sleep(50);
            System.out.println("no done");
        }
        // 输出结果
        System.out.println(futureTask.get());


    }
}
class Join{
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("A Start");
                Thread.sleep(10000);
                System.out.println("A DONE");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();

        new Thread(() -> {
            try {
                thread.join(); // 等待A线程执行完成
                System.out.println("B DONE");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

class  local{

    // 创建一个线程局部变量threadLocal1，并设置其初始值为10
    private static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 10);
    // 创建一个线程局部变量threadLocal2，并设置其初始值为"Hello"
    private static ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "Hello");

    public static void main(String[] args) {
        // 创建一个新线程thread，并为其指定一个Runnable实现
        Thread thread = new Thread(() -> {
            // 获取threadLocal1的值
            int value1 = threadLocal1.get();  // 放入Thread的Map key 为ThreadLocal<integer> value为10
            // 获取threadLocal2的值
            String value2 = threadLocal2.get(); // 放入Thread的Map key 为ThreadLocal<String> value为Hello
            // 打印threadLocal1的值
            System.out.println("ThreadLocal1: " + value1);
            // 打印threadLocal2的值
            System.out.println("ThreadLocal2: " + value2);
        });

        // 启动线程thread
        thread.start();
    }
}
