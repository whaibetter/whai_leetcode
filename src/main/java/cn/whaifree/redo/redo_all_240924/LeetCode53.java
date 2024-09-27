package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 12:37
 * @注释
 */
public class LeetCode53 {

    @Test
    public void test() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Solution().maxSubArray(nums));
    }

    class Solution {
        /**
         *
         * dp[i] 表示以i结尾的最大连续子数组的最大和
         *
         * -2,1,-3,4,-1,2,1,-5,4
         * -2 1 -2 4  3 5 6  1 5
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int nowMax = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                nowMax = Math.max(nowMax, dp[i]);
            }
            return nowMax;
        }
    }
}
