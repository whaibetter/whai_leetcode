package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 16:54
 * @注释
 */
public class LeetCode347 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] res = solution.topKFrequent(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // 频率统计
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }

            PriorityQueue<Integer> queue = new PriorityQueue<>(
                    k, (o1, o2) -> map.get(o2) - map.get(o1)
            );

            for (Integer integer : map.keySet()) {
                queue.add(integer);
            }

            int[] res = new int[k];
            for (int i = 0; i < res.length; i++) {
                res[i] = queue.poll();
            }
            return res;

        }
    }

}
