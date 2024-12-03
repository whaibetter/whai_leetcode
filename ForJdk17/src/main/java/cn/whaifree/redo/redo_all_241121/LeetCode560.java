package cn.whaifree.redo.redo_all_241121;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/2 16:26
 * @注释
 */
public class LeetCode560 {

    class Solution {
        /**
         * 连续字数组--->可以考虑前缀和
         *
         * 计算前缀和
         * 对于每个前缀和统计 该前缀和的个数 k为前缀和，value为个数
         *
         * 遍历的过程中+ map.get(k-pre[i])
         *
         * @param nums
         * @param k
         * @return
         */
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            // map为各种前缀和的数量
            int preSum = 0;
            int res = 0;
            for (int num : nums) {
                preSum += num;
                if (map.containsKey(preSum - k)) {
                    res += map.get(preSum - k);
                }
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            }

            return res;
        }
    }

}
