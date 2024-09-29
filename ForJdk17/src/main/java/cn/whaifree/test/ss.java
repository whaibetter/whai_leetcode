package cn.whaifree.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ss {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Object o = new Object();
        Object o1 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (o1) {

                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1) {
                }
            }
        }).start();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 1;
            }
        });
        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 2;
            }
        });
        System.out.println(integerCompletableFuture.get()+integerCompletableFuture2.get());
        CompletableFuture<Integer> integerCompletableFuture1 = integerCompletableFuture.thenCombine(integerCompletableFuture2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println(integerCompletableFuture1.get());
    }
}
