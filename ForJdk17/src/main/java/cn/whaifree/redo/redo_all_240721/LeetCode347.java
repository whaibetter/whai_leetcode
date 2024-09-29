package cn.whaifree.redo.redo_all_240721;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/24 22:23
 * @注释
 */
public class LeetCode347 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] ints = topKFrequent(nums, k);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if (map.get(o1) > map.get(o2)) {
                            return -1;
                        }else {
                            return 1;
                        }
                    }
                }
        );

        priorityQueue.addAll(map.keySet());

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(priorityQueue.poll());
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
