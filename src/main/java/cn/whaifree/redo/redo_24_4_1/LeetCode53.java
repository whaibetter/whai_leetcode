package cn.whaifree.redo.redo_24_4_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 12:44
 * @注释
 */
public class LeetCode53 {

    @Test
    public void test() {

        int[] nums = new int[]{1};
        int maxSubArray = new Solution1().maxSubArray(nums);
        System.out.println(maxSubArray);
    }

    class Solution {
        public int maxSubArray(int[] nums) {
            // 滑动窗口
            // 遇到和为负数就一直缩小left

            int maxSum = Integer.MIN_VALUE;
            int nowSum = 0;
            for (int i = 0; i < nums.length; i++) {
                nowSum += nums[i];
                maxSum = Math.max(maxSum, nowSum);
                if (nowSum < 0) {
                    nowSum = 0;
                }
            }
            return maxSum;
        }
    }


    class Solution1 {
        /**
         * dp
         *
         * dp[i] 表示i处结尾的的最大连续子数组的和
         *
         * dp[0] = nums[0]
         * dp[i]
         *      if nums[i]+dp[i-1] > 0
         *              dp[i] = dp[i-1]+nums[i]
         *      else
         *              dp[i] = nums[i]
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0 && nums[i] + dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    class Solution4 {
        /**
         * 前缀和
         * @param nums
         * @return
         */
//        public int maxSubArray(int[] nums) {
//
//            int[] preSum = new int[nums.length + 1];
//            for (int i = 1; i < nums.length; i++) {
//                preSum[i] = preSum[i - 1] + nums[i - 1];
//            }
//
//
//        }
    }


}

class XieChen {

    /**
     * 将偶数的区间/2 让整个区间最大
     * 给定一个数组，对一个区间（区间内全部都是偶数）全部/2，使得整个数组和最大，求数组最大和。
     */
    public static void main(String[] args) {



    }

    public void xc(int[] nums) {

        // 前缀和
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int minLoc = 0;

        int left = 0;
        int right = 0;
        while (right < nums.length) {


            right++;
        }


    }


}
