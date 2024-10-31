package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 14:04
 * @注释
 */
public class LeetCode377 {
    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        int target = 4;
        int result = new Solution().combinationSum4(nums, target);
        System.out.println(result);
    }

    class Solution {
        /**
         * 背包容量 target+1
         * 无穷背包
         *
         * 0-i 中任选，装满背包的组合数
         *
         * 组合数，有排序
         *
         *   0 1 2 3 4 5
         * 1 1 1 1 1 1 1
         * 2 1 1 2 2 3 3            dp[j]+dp[j-nums[i]] // 没有排序
         * 3 1 1 2 3 4 5
         *
         * @param nums
         * @param target
         * @return
         */
        public int combinationSum4(int[] nums, int target) {

            /**
             * 先遍历背包，就可以让之前的背包都获得所有物品放入的可能性，
             *
             * 计算dp[4]的时候，结果集只有 {1,3} 这样的集合，忽略了{3,1}
             */

            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int j = 1 ; j <= target; j++) { // 有顺序，先遍历背包
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= j) {
                        dp[j] = dp[j] + dp[j - nums[i]];
                    }
                }
            }
            return dp[target];
        }
    }

}
