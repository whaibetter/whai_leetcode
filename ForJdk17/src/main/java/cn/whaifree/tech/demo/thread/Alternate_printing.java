package cn.whaifree.tech.demo.thread;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/12 19:10
 * @注释
 */
public class Alternate_printing {
    public static void main(String[] args) {

        Semaphore s1 = new Semaphore(1); // permits = 1 表示 可以使用acquire对Semaphore的state--，或者使用release对Semaphore的state++
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    s1.acquire(); // 获取信号量
                    System.out.println("a");
                    s2.release(); // 释放信号量给下一个线程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread A interrupted.");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    s2.acquire(); // 获取信号量
                    System.out.println("b");
                    s3.release(); // 释放信号量给下一个线程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread B interrupted.");
                }
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    s3.acquire(); // 获取信号量
                    System.out.println("c");
                    s1.release(); // 释放信号量给第一个线程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread C interrupted.");
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class Alternate_printing_2 {

    static Object o = new Object();
    static volatile int num = 0;

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("file");
        // BufferedInputStream 装饰器类
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        new Thread(new FutureTask<>(new Callable<>() {
            @Override
            public Object call() throws Exception {
                while (num < 100) {
                    synchronized (o) {
                        while (num % 2 == 0) {
                            o.wait();
                        }
                        System.out.println("a" + num);
                        num++;
                        o.notifyAll();
                    }
                }
                return null;
            }
        })).start();


        new Thread(new FutureTask<>(new Callable<>() {
            @Override
            public Object call() throws Exception {
                while (num < 100) {
                    synchronized (o) {
                        while (num % 2 == 1) { // 自旋
                            o.wait();
                        }
                        System.out.println("b" + num);
                        num++;
                        o.notifyAll();
                    }
                }
                return null;
            }
        })).start();
    }
}
