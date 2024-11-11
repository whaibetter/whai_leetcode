package cn.whaifree.tech.demo.thread;

import org.junit.Test;

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

    final static ExecutorService executorService1 = Executors.newScheduledThreadPool(10);




    public static void main(String[] args) {

//        futureTaskDemo();

    }

    public static void futureTaskDemo() {
        List<FutureTask> futureTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FutureTask futureTask = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(60 * 1000);
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

    @Test
    public void completeFutureDemoTest() {
        completeFutureDemo();
    }

    static volatile int num = 0;
    public static void completeFutureDemo() {
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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


    @Test
    public void howFutureGet() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 0;
            }
        });
        cf.get();
    }

    class MyFutureTask extends FutureTask<Integer> {

        public MyFutureTask(Callable<Integer> callable) {
            super(callable);
        }

        public MyFutureTask(Runnable runnable, Integer result) {
            super(runnable, result);
        }

        private volatile int state;  // 任务状态
        private static final int NEW = 0;
        private static final int COMPLETING = 1;
        private static final int NORMAL = 2;
        private static final int EXCEPTIONAL = 3;
        private static final int CANCELLED = 4;
        private static final int INTERRUPTING = 5;
        private static final int INTERRUPTED = 6;
//        private Callable<V> callable;  // 用户提交的任务
//        private Object outcome;  // 任务的结果或异常
//        private volatile Thread runner;  // 当前执行任务的线程
//        private volatile WaitNode waiters;  // 阻塞线程链表

        @Override
        public Integer get() throws InterruptedException, ExecutionException {
            return super.get();
            /*
            int s = state;
            if (s <= COMPLETING)
                s = awaitDone(false, 0L); // 未完成的任务调用awaitDone阻塞
            return report(s);             //   根据任务状态返回结果或抛出异常
            */
        }



        /**
         * Awaits completion or aborts on interrupt or timeout.
         *
         * @param timed true if use timed waits
         * @param nanos time to wait, if timed
         * @return state upon completion or at timeout
         */
//        private int awaitDone(boolean timed, long nanos)
//                throws InterruptedException {
//            long startTime = 0L;    // Special value 0L means not yet parked
//            WaitNode q = null;
//            boolean queued = false;
//            for (;;) {
//                int s = state;
//                if (s > COMPLETING) {
//                    if (q != null)
//                        q.thread = null;
//                    return s;
//                }
//                else if (s == COMPLETING)
//                    // We may have already promised (via isDone) that we are done
//                    // so never return empty-handed or throw InterruptedException
//                    Thread.yield();
//                else if (Thread.interrupted()) {
//                    removeWaiter(q);
//                    throw new InterruptedException();
//                }
//                else if (q == null) {
//                    if (timed && nanos <= 0L)
//                        return s;
//                    q = new WaitNode();
//                }
//                else if (!queued)
//                    queued = WAITERS.weakCompareAndSet(this, q.next = waiters, q);
//                else if (timed) {
//                    final long parkNanos;
//                    if (startTime == 0L) { // first time
//                        startTime = System.nanoTime();
//                        if (startTime == 0L)
//                            startTime = 1L;
//                        parkNanos = nanos;
//                    } else {
//                        long elapsed = System.nanoTime() - startTime;
//                        if (elapsed >= nanos) {
//                            removeWaiter(q);
//                            return state;
//                        }
//                        parkNanos = nanos - elapsed;
//                    }
//                    // nanoTime may be slow; recheck before parking
//                    if (state < COMPLETING)
//                        LockSupport.parkNanos(this, parkNanos);
//                }
//                else
//                    LockSupport.park(this);
//            }
//        }



    }

    class MyCompletableFuture extends CompletableFuture<Integer> {

    }

}
