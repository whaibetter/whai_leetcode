package cn.whaifree.tech.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 11:22
 * @注释
 */
public class SelfDefiniteThreadPool {

    /**
     * @RefreshScope
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                new MyThreadFactory("whai")
        );

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "     " + finalI);
                    return null;
                }
            });

        }




        executor.shutdown();
    }

}



class MyThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;


    MyThreadFactory(String namePrefix) {
       /* JDK8
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = namePrefix + "-thread-";*/
        this.group = Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix + "-thread-";
    }

    /**
     * 创建并返回一个新的线程
     * 该方法配置了线程组、任务、线程名称前缀和线程编号
     * 此外，该方法还将线程设置为非守护线程，并确保线程的优先级为默认优先级
     *
     * @param r 要在线程中执行的任务
     * @return 返回创建的线程对象
     */
    @Override
    public Thread newThread(Runnable r) {
        // 创建线程，使用线程组、任务、线程名称前缀和线程编号
        Thread thread = new Thread(
                group,
                r,
                namePrefix + threadNumber.getAndIncrement(),
                0
        );
        // 将线程设置为非守护线程，非后台线程
        thread.setDaemon(false);
        // 如果线程的优先级不是默认优先级，则设置为默认优先级
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        // 返回创建的线程对象
        return thread;
    }


}


//final class ResizableBlockingQueue<E> extends SizeBlockingQueue<E> {
//
//    private volatile int capacity; // 队列的当前容量
//
//    /**
//     * 构造函数，初始化队列和容量
//     * @param queue 要扩展的阻塞队列
//     * @param initialCapacity 初始容量
//     */
//    ResizableBlockingManager(BlockingQueue<E> queue, int initialCapacity) {
//        super(queue, initialCapacity);
//        this.capacity = initialCapacity;
//    }
//
//    /**
//     * 获取当前队列的容量
//     * @return 当前容量
//     */
//    @Override
//    public int capacity() {
//        return this.capacity;
//    }
//
//    /**
//     * 获取队列剩余容量
//     * @return 剩余容量，不会小于0
//     */
//    @Override
//    public int remainingCapacity() {
//        return Math.max(0, this.capacity());
//    }
//
//    /**
//     * 调整队列的容量限制
//     * @param optimalCapacity 期望的容量
//     * @param adjustmentAmount 调整量
//     * @param minCapacity 最小容量限制
//     * @param maxCapacity 最大容量限制
//     * @return 新的容量限制
//     */
//    public synchronized int adjustCapacity(int optimalCapacity, int adjustmentAmount, int minCapacity, int maxCapacity) {
//        // 断言调整量、期望容量、最小容量和最大容量的合法性
//        assert adjustmentAmount > 0 : "adjustment amount should be a positive value";
//        assert optimalCapacity >= 0 : "desired capacity cannot be negative";
//        assert minCapacity >= 0 : "cannot have min capacity smaller than 0";
//        assert maxCapacity >= minCapacity : "cannot have max capacity smaller than min capacity";
//
//        // 如果期望容量等于当前容量，则无需调整
//        if (optimalCapacity == capacity) {
//            return this.capacity;
//        }
//
//        // 如果期望容量大于当前容量加上调整量，则向上调整容量
//        if (optimalCapacity > capacity + adjustmentAmount) {
//            final int newCapacity = Math.min(maxCapacity, capacity + adjustmentAmount);
//            this.capacity = newCapacity;
//            return newCapacity;
//        }
//        // 如果期望容量小于当前容量减去调整量，则向下调整容量
//        else if (optimalCapacity < capacity - adjustmentAmount) {
//            final int newCapacity = Math.max(minCapacity, capacity - adjustmentAmount);
//            this.capacity = newCapacity;
//            return newCapacity;
//        }
//        // 否则，当前容量已经是最佳容量
//        else {
//            return this.capacity;
//        }
//    }
//}
