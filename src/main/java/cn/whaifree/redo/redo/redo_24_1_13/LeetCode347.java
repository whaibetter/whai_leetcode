package cn.whaifree.redo.redo.redo_24_1_13;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/13 15:10
 * @注释
 */
public class LeetCode347 {

    @Test
    public void test() {
        int[] x = new Solution1().topKFrequent(new int[]{2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 5, 5, 5, 3, 3, 3, 3, 3, 3}, 2);

        for (int i : x) {
            System.out.println(i);
        }
    }

    class Solution {

        public int[] topKFrequent(int[] nums, int k) {
            // 统计频率
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], map.get(nums[i]) + 1);
                } else {
                    map.put(nums[i], 1);
                }
            }

            // 大顶堆
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return map.get(o2) - map.get(o1);
                        }
                    }
            );


            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                priorityQueue.add(entry.getKey());
            }

            int[] ints = new int[k];
            for (int i = 0; i < k; i++) {
                ints[i] = priorityQueue.poll();
            }

            return ints;
        }

    }

    class Solution1 {

        public int[] topKFrequent(int[] nums, int k) {
            // 统计频率
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], map.get(nums[i]) + 1);
                } else {
                    map.put(nums[i], 1);
                }
            }

            // 小顶堆
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return map.get(o1) - map.get(o2);
                        }
                    }
            );


            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                // 导入前k个形成初始堆
                if (priorityQueue.size() < k) {
                    priorityQueue.add(entry.getKey());
                } else if (map.get(entry.getKey()) > map.get(priorityQueue.peek())) {
                    // 如果 该元素的频率比最小的值都大，则插入，并且删除最小值
                    priorityQueue.remove();
                    priorityQueue.add(entry.getKey());
                }

            }

            int[] ints = new int[k];
            for (int i = 0; i < k; i++) {
                ints[i] = priorityQueue.poll();
            }

            return ints;
        }

    }
}
