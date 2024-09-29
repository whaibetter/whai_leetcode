package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/22 0:14
 * @注释
 */
public class LeetCode377 {

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        int target = 4;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum4(nums, target));

    }
    class Solution {
        /**
         *   dp[i] 表示
         *
         * @param nums
         * @param target
         * @return
         */
        public int combinationSum4(int[] nums, int target) {

            /**
             * 如果我们先遍历物品（nums），再遍历背包（target），那么我们实际上是在考虑每个物品对每个目标值的贡献。
             * 这种方式会忽略组合的顺序，即它只考虑了物品的组合，而不考虑这些物品的排列顺序。
             * - 确定一个物品，就不会关注他的顺序
             *
             * 例如，对于目标值4，如果我们先遍历物品，
             * 可能会得到组合{1,3}，但不会得到组合{3,1}，因为这种方式只考虑了物品的组合，而不考虑排列。
             */
            int[] dp = new int[target + 1];
            dp[0] = 1; // 都不放入 情况有一种
            for (int j = 1; j < target + 1; j++) {
                for (int i = 0; i < nums.length; i++) {
                    if (j >= nums[i]) {
                        dp[j] = dp[j] + dp[j - nums[i]];
                        // dp[j] 不放
                        // dp[j - nums[i]] 放，放也有多种情况
                    }
                }
                /**
                 * 如果把遍历nums（物品）放在外循环，
                 * 遍历target的作为内循环的话，举一个例子：
                 * 计算dp[4]的时候，结果集只有 {1,3} 这样的集合，忽略了{3,1}
                 */
            }

            return dp[target];
        }
    }
}
