package cn.whaifree.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 16:40
 * @注释
 */
public class ScheduledThreadPoolTest  {
    volatile static Integer i = 0;

    /**
     * 提交任务时，消费任务的时候检查 ScheduledFutureTask 时间
     *
     * 任务提交：当用户提交一个定时任务时，ScheduledThreadPool 会将任务封装成 ScheduledFutureTask 对象，并将其放入延迟队列中。
     *
     * 任务调度：线程池中的线程会从延迟队列中取出任务并执行。如果任务设置了延迟执行时间，线程会等待相应的时间后再执行任务。
     *
     * 任务执行：线程执行任务时，会调用 ScheduledFutureTask 的 run 方法。run 方法会首先检查任务的取消状态，如果任务被取消，则直接返回。否则，会执行任务的 run 方法。
     *
     * 周期性任务：对于周期性任务，ScheduledFutureTask 会根据任务的执行周期重新计算下一次执行的时间，并将其重新放入延迟队列中。这样，周期性任务会按照指定的周期重复执行。
     *
     * 异常处理：如果任务在执行过程中抛出异常，ScheduledFutureTask 会捕获这个异常，并调用 setException 方法将异常设置到 Future 对象中。
     *
     * 任务完成：当任务执行完成后，ScheduledFutureTask 会从延迟队列中移除，并更新 Future 对象的状态。
     *
     * 线程回收：ScheduledThreadPool 中的线程会根据任务的执行情况进行回收。如果线程池中的线程在一段时间内没有任务可执行，它们会被回收，以减少资源消耗。
     * @param args
     */
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay:" + i);
            }
        }, 10, TimeUnit.SECONDS);
        // 10秒后才执行
        scheduledExecutorService.shutdown();


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(finalJ);
                }
            });
        }



    }
}
