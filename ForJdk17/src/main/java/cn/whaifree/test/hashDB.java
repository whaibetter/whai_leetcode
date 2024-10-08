package cn.whaifree.test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/1 20:01
 * @注释
 */
public class hashDB {

    static final int[] hashes = {1,4}; // 哈希函数数组

    // 3个库
    private static final int NUM_SHARDS = 3;



    public static void main(String[] args) {
        // 要分库的数据
        String[] id = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        // 对每个数据计算哈希值并分配到库
        for (String datum : id) {
            int[] shard = getShard(datum);
            System.out.println("Data: " + datum + " -> Shard: " + shard[0] + ", " + shard[1]);
        }
    }

    // 计算数据的哈希值并分配到库
    private static int[] getShard(String data) {
        // 使用Java的hashCode()方法计算哈希值
        int hash = data.hashCode();
        // 取模运算得到库编号
        int[] loc = new int[hashes.length];
        for (int i = 0; i < loc.length; i++) {
            loc[i] = Math.abs((hash ^ hashes[i]) % NUM_SHARDS) + 1;
        }

        return loc;
    }


}

class test{

    public static void main(String[] args) {
        ArrayList<String> sharedList= new ArrayList<>();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sharedList.add("Item " + i);
                    // 模拟一些工作时间
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted.");
            }
        }).start();


        new Thread(() -> {
            try {
                ListIterator<String> iterator = sharedList.listIterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                    // 模拟一些工作时间
                    Thread.sleep(100);
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Caught ConcurrentModificationException during iteration.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted.");
            }
        }).start();


    }
}
