package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 15:14
 * @注释
 */
public class LeetCode703 {

    @Test
    public void test() {
        KthLargest kthLargest = new KthLargest(3, new int[]{});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    class KthLargest {

        PriorityQueue<Integer> priorityQueue = null;
        int size = 0;

        // 小顶堆 第k
        public KthLargest(int k, int[] nums) {
            priorityQueue = new PriorityQueue<>();
            this.size = k;
            for (int num : nums) {
                priorityQueue.offer(num);
                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
        }

        public int add(int val) {
            priorityQueue.offer(val);
            if (priorityQueue.size() > size) {
                priorityQueue.poll();
            }
            return priorityQueue.peek();
        }
    }

}
