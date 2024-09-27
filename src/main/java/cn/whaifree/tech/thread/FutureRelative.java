package cn.whaifree.tech.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 13:36
 * @注释
 */
public class FutureRelative {
    final static ExecutorService executorService = Executors.newFixedThreadPool(10,
            Executors.defaultThreadFactory());




    public static void main(String[] args) {
        futureTaskDemo();

    }

    public static void futureTaskDemo() {
        List<FutureTask> futureTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FutureTask futureTask = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000);
                    return 1;
                }
            });
            futureTasks.add(futureTask);
            executorService.submit(futureTask);
        }

        for (FutureTask futureTask : futureTasks) {
            try {
                System.out.println(futureTask.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static volatile int num = 0;

    public static void completeFutureDemo() {
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return num++;
                }
            }, executorService).exceptionally(
                    throwable -> {
                        System.out.println("error");
                        return 0;
                    }
            );
            completableFutures.add(cf);
        }

        // CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));

        int sum = 0;
        for (CompletableFuture<Integer> cf : completableFutures) {
            try {
                sum += cf.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
