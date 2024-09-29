package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 16:04
 * @注释
 */
public class LeetCode277 {

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(new Solution().combinationSum4(nums, target));
    }

    class Solution {
        /**
         * 物品 nums
         * 容量 target
         * <p>
         * 0 1 2 3 4
         * 1 1 1 1 1 1
         * 2 1 1 2 2 2
         * 3 1 1 2 3 3
         *
         * @param nums
         * @param target
         * @return
         */
        public int combinationSum4(int[] nums, int target) {

            int[] dp = new int[target + 1];
            dp[0] = 1;
            // 先遍历背包，再遍历物品 排列（有排序）
            // 先遍历物品，再遍历背包，组合（无排序）
            for (int j = 0; j < target + 1; j++) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= j) {
                        dp[j] = dp[j - nums[i]] + dp[j];
                        // dp[j] 原来的可能性
                        // dp[j-nums[i]] 使用容量j-nums的可能性
                    }
                }
            }
            return dp[target];
        }


    }
}
