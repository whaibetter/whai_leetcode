package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/25 11:59
 * @注释
 */
public class LeetCode53 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = new int[]{-1,-3};
        int i = solution.maxSubArray(nums);
        System.out.println(i);
    }

    class Solution {

        /**
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            // dp[i] 表示包含元素i的的最大和
            // dp[0] = nums[0]

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0 && dp[i - 1] + nums[i] > 0) { // 如果p[i - 1] < 0 则只能拖后腿
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    // 拖后腿，重新计算
                    dp[i] = nums[i];
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
}
