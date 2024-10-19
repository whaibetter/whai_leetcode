package cn.whaifree.tech.demo.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/18 21:40
 * @注释
 */
public class OOMTest {

    // 如果起了 2 个线程，当其中一个 OOM 了，另一个线程会受影响吗?

    // -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
    public static void main(String[] args) {
        // 启动一个线程来模拟内存泄漏
        Thread leakThread = new Thread(() -> {
            try {
                // 使用一个大数组来耗尽内存
                while (true) {
                    // 创建一个大的对象数组
                    int[] largeArray = new int[10_000_000]; // 10M
                    Thread.sleep(100); // 暂停一段时间，观察 GC
                }
            } catch (OutOfMemoryError e) {
                System.out.println("OutOfMemoryError caught in leakThread.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        leakThread.start();
    }
}
