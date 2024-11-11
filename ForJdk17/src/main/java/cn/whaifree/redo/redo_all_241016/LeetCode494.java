package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/10 17:19
 * @注释
 */
public class LeetCode494 {

    class Solution {

        /**
         * 背包问题
         *
         * 1 2 1 1 1 ； 3
         * x为需要变成负数的数量
         *
         * sum = x * 2 + target
         *
         * x = sum-target /2
         *
         * 把nums里装到容量为x的背包中
         * 有几种方法可以装满
         *
         *   0 1 2 3
         * 1 1 1 0 0
         * 2 1 1 1 1
         * 2 1 1 2 2
         * 1
         * 1
         *
         * 1 2 -2 1 1
         * 1 2 -2 -1 -1
         * -1 2 -2 1 -1
         * -1 2 -2 -1 1
         *
         *
         *
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {
            Integer sum = Arrays.stream(nums).sum();
            int pkgSize = (sum - target) / 2;
            int[] dp = new int[pkgSize + 1];
            dp[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = pkgSize; j >= 0; j--) {
                    if (j >= nums[i]) {
                        dp[j] = dp[j] + dp[j - nums[i]];
                    }
                }
            }
            return dp[pkgSize];
        }
    }

    @Test
    public void test() {
        int[] nums = {1};
        int target = 1;
        System.out.println(new Solution().findTargetSumWays(nums, target));
    }

}
