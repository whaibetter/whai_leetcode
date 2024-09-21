package cn.whaifree.test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 16:24
 * @注释
 */
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    private int count = 0;
    private final StampedLock lock = new StampedLock();

    public void increment() {
        long stamp = lock.writeLock(); // 获取写锁
        try {
            count++;
        } finally {
            lock.unlockWrite(stamp); // 释放写锁
        }
    }

    public int getCount() {
        long stamp = lock.tryOptimisticRead(); // 获取乐观读锁
        int c = count;
        if (!lock.validate(stamp)) { // 检查乐观读锁是否有效, 如果乐观锁无效，使用悲观锁
            stamp = lock.readLock(); // 获取悲观读锁
            try {
                c = count;
            } finally {
                lock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return c;
    }

    public static void main(String[] args) {
        StampedLockDemo demo = new StampedLockDemo();
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                demo.increment();
            }
        });
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(demo.getCount());
            }
        });
        writer.start();
        reader.start();
        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
