package cn.whaifree.tech.thread;

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

        Semaphore s0 = new Semaphore(1);
        Semaphore s1 = new Semaphore(0);

        new Thread(new FutureTask<>(new Callable<>() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 50; i++) {
                    s0.acquire();
                    System.out.println("a");
                    s1.release();
                }
                return null;
            }
        })).start();


        new Thread(new FutureTask<>(new Callable<>() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 50; i++) {
                    s1.acquire();
                    System.out.println("b");
                    s0.release();
                }
                return null;
            }
        })).start();
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
