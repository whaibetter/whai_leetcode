package cn.whaifree.tmp.Thread.T241006;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 19:20
 * @注释
 */
public class ThreadChangePrintDemo {


}
class DownLatch{

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(99);

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                while (countDownLatch.getCount() % 3 != 0) {}
                System.out.println("A");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                while (countDownLatch.getCount() % 3 != 2) {}
                System.out.println("B");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    while (countDownLatch.getCount() % 3 != 1) {}
                    System.out.println("C");
                    countDownLatch.countDown();
                }
            }
        }).start();
    }
}

class SemaphoreDemo{

    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);
    static Semaphore s3 = new Semaphore(0);

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {
                        s1.acquire();
                        System.out.println("A");
                        s2.release();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {
                        s2.acquire();
                        System.out.println("B");
                        s3.release();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {
                        s3.acquire();
                        System.out.println("C");
                        s1.release();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}


class Syn{
    static Object o = new Object();
    static int count = 0;
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (o) {
                        while (count % 3 != 0) {
                            o.wait();
                        }
                        System.out.println("A");
                        o.notifyAll();
                        count++;
                    }

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (o) {
                        while (count % 3 != 1) {
                            o.wait();
                        }
                        System.out.println("B");
                        o.notifyAll();
                        count++;
                    }

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (o) {
                        while (count % 3 != 2) {
                            o.wait();
                        }
                        System.out.println("C");
                        o.notifyAll();
                        count++;
                    }

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
