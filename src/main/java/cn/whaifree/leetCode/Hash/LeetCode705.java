package cn.whaifree.leetCode.Hash;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/14 13:15
 * @注释
 */
public class LeetCode705 {

    @Test
    public void test()
    {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        System.out.println(myHashSet.contains(1)); // 返回 True
        System.out.println(myHashSet.contains(3)); // 返回 False ，（未找到）
        System.out.println(myHashSet.contains(2));
        myHashSet.remove(2);
        System.out.println(myHashSet.contains(2)); // 返回 False ，（已移除）

    }

    /**
     * int 表示4字节 32位 每一位代表一个位置
     * 1000000/32  40000表示桶
     */
    class MyHashSet {

        int[] bucket = null;

        public MyHashSet() {
            bucket = new int[40000];
        }

        public void add(int key) {
            int bkLoc = key / 32;
            int ex = key % 32;
            int num = bucket[bkLoc];
            //  如num=4 (100)  1<<1= 010 进行|操作后变成 110
            bucket[bkLoc] = num | (1 << ex);
        }

        public void remove(int key) {
            int bkLoc = key / 32;
            int ex = key % 32;
            int num = bucket[bkLoc];
            // 如num=7 (111)  1<<1= 010 进行~后变成101 & 操作后变成 101
            bucket[bkLoc] = num & ~(1 << ex);
        }

        public boolean contains(int key) {
            int bkLoc = key / 32;
            int ex = key % 32; // 偏移
            int num = bucket[bkLoc]; // 数字
            return ((num >> ex) & 1) == 1;
            //  如num=7 (111)  1<<1= 010 进行~后变成101 & 操作后变成 101
//            int i = num & (1 << ex);
//            return 1 << ex == i;
        }

    }

}
