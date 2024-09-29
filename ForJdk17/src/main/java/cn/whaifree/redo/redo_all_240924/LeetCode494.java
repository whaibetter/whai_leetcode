package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 12:48
 * @注释
 */
public class LeetCode494 {


    class Solution {
        /**
         *
         *
         * 变为负数的数量为x
         * 2x + target = sum
         * x = sum-target /2
         *
         * 1 2 1 1
         * 3
         *
         * 0 就是都不放
         *   0 1
         * 1 1 1
         * 1 1 2
         * 1 1 3
         * 1 1 4
         * 1 1 5
         *
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {
            int sum = Arrays.stream(nums).sum();
            int pkgSize = (sum - target) / 2; // 负数的数量
            if (Math.abs(target) > sum)
                return 0; // 此时没有方案
            if ((sum - target) % 2 == 1)
                return 0; // 此时没有方案

            // dp[i][j]：使用 下标为[0, i]的nums[i]能够凑满j（包括j）这么大容量的包，有dp[i][j]种方法。
            int[][] dp = new int[nums.length][pkgSize + 1];
            dp[0][0] = 1;
            for (int i = 1; i < nums.length; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < pkgSize; j++) {
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[nums.length - 1][pkgSize];

        }
    }
}
