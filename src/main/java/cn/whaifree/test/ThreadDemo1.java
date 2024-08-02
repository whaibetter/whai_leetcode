package cn.whaifree.test;

public class ThreadDemo1 {

    static Object lock = new Object();
    static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock){
                    while (num%2!=0){
                        try {
                            lock.wait(); // wait会释放锁
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    num++;
                    System.out.println("a");
                    lock.notifyAll();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock){ // wait释放锁后这个线程就能拿到锁
                    while (num%2!=1){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    num++;
                    System.out.println("b");
                    lock.notifyAll();
                }
            }
        }).start();
    }
}
