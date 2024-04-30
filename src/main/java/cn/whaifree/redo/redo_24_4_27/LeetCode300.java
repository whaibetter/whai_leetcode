package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/30 11:49
 * @注释
 */
public class LeetCode300 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int res = solution.lengthOfLIS(nums);
        System.out.println(res);
    }

    class Solution {
        /**
         * 最长严格递增子序列的长度。
         *
         * dp[i]表示以i为结尾的最长递增子序列的长度 （计算i的时候，0~(i-1)已经满足最长递增子序列的长度）
         *
         *
         * if (nums[j] < nums[i])
         *      dp[i] = dp[j] + 1
         *      dp[j] = dp[i]
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            int res = 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
}
