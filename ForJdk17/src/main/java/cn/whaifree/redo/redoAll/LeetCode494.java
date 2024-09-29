package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/22 22:05
 * @注释
 */
public class LeetCode494 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = new int[]{1,0};
        int target = 1;
        int i = solution.findTargetSumWays(nums, target);
        System.out.println(i);
    }

    class Solution {
        /**
         *
         * sum =
         * x需要变负数的数量
         *
         * sum = x*2+target
         * x = ( sum - target ) / 2
         *
         *
         * 背包容量 x
         * 物品nums
         *
         * dp[i][j] 表示 从0-i中取，满足背包容量j的数量
         *
         *
         * nums[1,2,3,1,1] target 5
         *     0 1 2 3
         * 0 1 1 1 1 1
         * 1 2 1 1 2 2
         * 2 3 1 1 2 3
         * 3 1 1 2 3
         * 4 1 1
         *
         *
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {

            int sum = Arrays.stream(nums).sum();

            int packageSize = (sum - target) / 2;
            if (Math.abs(target) > sum) return 0; // 此时没有方案
            if ((sum - target) % 2 == 1) return 0; // 此时没有方案


            int[][] dp = new int[nums.length + 1][packageSize + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= nums.length; i++) {
                for (int j = 0; j <= packageSize; j++) {
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    }else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[nums.length][packageSize];
        }
    }
}
