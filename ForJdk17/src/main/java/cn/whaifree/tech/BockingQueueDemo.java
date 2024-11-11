package cn.whaifree.tech;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/5 22:22
 * @注释
 */
public class BockingQueueDemo {


    static int size = 3;
    static List<String> instanceId = List.of();
    static {
        for (int i = 0; i < 50; i++) {
            instanceId.add("instance" + i);
        }
    }

    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.addAll(instanceId);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {

                }
            }
        }).start();
    }
}
