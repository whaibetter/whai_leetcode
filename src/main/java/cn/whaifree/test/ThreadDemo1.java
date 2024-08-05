package cn.whaifree.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo1 {

    static Object lock = new Object();
    static int num = 0;

//    public static void main(String[] args) {
//        new Thread(() -> {
//            for (int i = 0; i < 50; i++) {
//                synchronized (lock){
//                    while (num%2!=0){
//                        try {
//                            lock.wait(); // wait会释放锁
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    num++;
//                    System.out.println("a");
//                    lock.notifyAll();
//                }
//            }
//
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 50; i++) {
//                synchronized (lock){ // wait释放锁后这个线程就能拿到锁
//                    while (num%2!=1){
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    num++;
//                    System.out.println("b");
//                    lock.notifyAll();
//                }
//            }
//        }).start();
//    }

    static class CountDownLatchDemo2{

        public static void main(String[] args) throws InterruptedException {

            AtomicInteger num = new AtomicInteger(9);
            CountDownLatch countDownLatch = new CountDownLatch(num.get());

            for (int i = 0; i < 9; i++) {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " " + finalI);
                    countDownLatch.countDown();
                }).start();
            }

            countDownLatch.await();

            new Thread(() -> System.out.println("上面的9个执行完了,轮到我了 wait complete！")).start();

        }
    }

    static class CyclicBarrierDemo2{

        public static void main(String[] args) throws InterruptedException {

            // 每9个cyclicBarrier.await()执行后，都执行一次wait
            CyclicBarrier cyclicBarrier = new CyclicBarrier(9,
                    () -> System.out.println("上面的9个执行完了,轮到我了 wait complete！") // 等待前9次执行结束
            );


            for (int i = 0; i < 9; i++) {
                int finalI = i;
                new Thread(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + finalI);
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        }
    }

    static class FutureGetDemo{
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService executorService = Executors.newFixedThreadPool(4); // 设置4，4个核心线程
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                int finalI = i;
                Future<?> submit = executorService.submit(() -> {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " " + finalI);
                    return null;
                });
                futures.add(submit);
            }

            for (Future<?> future : futures) {
                future.get();
            }

            System.out.println("上面的9个执行完了,轮到我了 wait complete！");
            executorService.shutdown();
        }
    }
}
