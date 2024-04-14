package cn.whaifree.test;

import java.util.concurrent.Semaphore;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 16:06
 * @注释
 */
public class ThreadTest {

    private static Object lock = new Object();
    private static Integer count = 0;

    /**
     * 主函数示例，演示了通过两个线程交替打印字符并控制其执行顺序的简单并发程序。
     * 使用一个共享计数器和一个对象锁来协调两个线程的执行。
     * 线程1打印字符'a'，线程2打印字符'b'，交替进行，直到计数器达到100。
     *
     * @param args 命令行参数（未使用）
     * @throws InterruptedException 如果线程在等待时被中断则抛出此异常
     */
    public static void main1(String[] args) throws InterruptedException{

        // 启动线程1，负责打印'a'并进行同步控制
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (count < 100) {
                            synchronized (lock) {
                                // 当计数器为偶数时，线程1等待
                                if (count % 2 == 0) {
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                System.out.println("a");
                                count++;
                                // 计数器为奇数时，唤醒等待的线程2
                                lock.notify();
                            }
                        }
                    }
                }
        ).start();

        // 启动线程2，负责打印'b'并进行同步控制
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (count < 100) {
                            synchronized (lock) {
                                // 当计数器为奇数时，线程2等待
                                if (count % 2 != 0) {
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                System.out.println("b");
                                count++;
                                // 计数器为偶数时，唤醒等待的线程1
                                lock.notify();
                            }
                        }
                    }
                }
        ).start();


    }

    public static void main3(String[] args) throws InterruptedException{

        // 创建三个线程
        Thread threadA = new Thread(() -> {
            try {
                // 循环100次
                for (int i = 0; i < 100; i++) {
                    // 获取锁
                    synchronized (lock) {
                        // 判断是否轮到自己执行
                        while (count % 3 != 0) {
                            // 不是则等待
                            lock.wait();
                        }
                        // 打印字母
                        System.out.println("A");
                        // 修改状态
                        count++;
                        // 唤醒下一个线程
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (count % 3 != 1) {
                            lock.wait();
                        }
                        System.out.println("B");
                        count++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        // 确保严格到本线程打的时候再等候
                        while (count % 3 != 2) {
                            lock.wait();
                        }
                        System.out.println("C");
                        count++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动三个线程
        threadA.start();
        threadB.start();
        threadC.start();

    }

    private static final Semaphore semaphoreC = new Semaphore(2); // 初始化为0，表示开始时阻止线程2（打印'b'）执行
    private static final Semaphore semaphoreA = new Semaphore(1); // 初始化为1，表示开始时允许线程1（打印'a'）执行
    private static final Semaphore semaphoreB = new Semaphore(0); // 初始化为0，表示开始时阻止线程2（打印'b'）执行
    public static void main2(String[] args) throws InterruptedException {

        new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        try {
                            semaphoreA.acquire();
                            System.out.println("a");
                            semaphoreB.release();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();


        new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        try {
                            semaphoreB.acquire();
                            System.out.println("b");
                            semaphoreA.release();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }

//    public static void main(String[] args) throws InterruptedException{
//
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        while (count < 100) {
//                            synchronized (count) {
//                                System.out.println(count);
//                                if (count % 2 == 0) {
//                                    try {
//                                        count.wait();
//                                    } catch (InterruptedException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                }
//                                System.out.println("a");
//                                count++;
//                                count.notify();
//                            }
//                        }
//                    }
//                }
//        ).start();
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        while (count < 100) {
//                            synchronized (count) {
//                                System.out.println(count);
//                                if (count % 2 != 0) {
//                                    try {
//                                        count.wait(); // 此时就释放锁了
//
//                                    } catch (InterruptedException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                }
//
//                                System.out.println("b");
//                                count++;
//                                count.notify();
//                            }
//                        }
//                    }
//                }
//        ).start();
//
//
//    }


    public static void main(String[] args) {
        new Thread(
                () -> {
                    while (count < 100) {
                        while (count % 3 == 0) {
                            System.out.println("A");
                            count++;
                        }
                    }
                }
        ).start();
        new Thread(
                () -> {
                    while (count < 100) {
                        while (count % 3 == 1) {
                            System.out.println("B");
                            count++;
                        }
                    }
                }
        ).start();
        new Thread(
                () -> {
                    while (count < 100) {
                        while (count % 3 == 2) {
                            System.out.println("C");
                            count++;
                        }
                    }
                }
        ).start();
    }
}

class DidLock{

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(
                ()->{
                    synchronized (a) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "a");
                            Thread.sleep(100);
                            synchronized (b) {

                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

        new Thread(
                ()->{
                    synchronized (b) {
                        System.out.println(Thread.currentThread().getName() + "b");
                        synchronized (a) {

                        }
                    }
                }
        ).start();
    }
}
