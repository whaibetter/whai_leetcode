package cn.whaifree.test;

import java.lang.reflect.Proxy;
import java.util.*;
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
//            Proxy.newProxyInstance()
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

class p2{

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Boolean>> ar = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future<Boolean> submit = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    if (finalI == 3) {
                        throw new MyException();
                    }
                    return true;
                }
            });
            ar.add(submit);
        }


        try {
            for (Future<Boolean> submit : ar) {
                submit.get();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof MyException){
                System.out.println("我捕获到了");
            }
        }
        System.out.println("1");


        try {
            for (int count = 1; count <= 5; count++) {
                if (count == 3) {
                    //故意制造一下异常
                    int num = 1 / 0;
                } else {
                    System.out.println("count:" + count + " 业务正常执行");
                }
            }
        } catch (Exception e) {
            System.out.println("try catch  在for 外面的情形， 出现了异常，for循环显然被中断");
        }

        for (int count = 1; count <= 5; count++) {
            try {
                if (count == 3) {
                    //故意制造一下异常
                    int num = 1 / 0;
                } else {
                    System.out.println("count:" + count + " 业务正常执行");
                }
            } catch (Exception e) {
                System.out.println("try catch  在for 里面的情形， 出现了异常，for循环显然继续执行");
            }
        }




    }
}


class MyException extends RuntimeException {


    private static ThreadPoolExecutor resetInstanceExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {


        List<String> instance = Arrays.asList("i-12345678", "i-23456789", "i-34567890", "i-45678901", "i-56789012",
                "i-67890123", "i-78901234", "i-89012345", "i-90123456", "i-101234567");
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Long startTimestamp = System.currentTimeMillis();

        Map<String, Future<Boolean>> resetInstanceMap = new HashMap<>();
        boolean exceedRateLimit = false; // 没有被限频
        for (String instanceId : instance) {
            Future<Boolean> result = resetInstanceExecutor.submit(new ResetInstanceCallable(instanceId,countDownLatch));
            resetInstanceMap.put(instanceId, result);
        }

        for (Map.Entry<String, Future<Boolean>> futureEntry : resetInstanceMap.entrySet()) {
            try {
                futureEntry.getValue().get();
            }
            catch (MyException ex){
                System.out.println("my exception"); // Callable内抛出的自定义异常无法被捕获，Callable内抛出的自定义异常无法被捕获
                // get()方法会抛出一个ExecutionException，而这个异常的cause才是原始的异常
                exceedRateLimit = true;
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            } catch (ExecutionException e) {
                System.out.println("exception");
            }
        }

        System.out.println(exceedRateLimit==false? "没有超过限制" : "超过限制");
    }
}

class ResetInstanceCallable implements Callable<Boolean> {

    private String instanceId;
    private CountDownLatch countDownLatch;

    public ResetInstanceCallable(String instanceId, CountDownLatch countDownLatch) {
        this.instanceId = instanceId;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Boolean call() {
        throw new MyException();
    }
}

