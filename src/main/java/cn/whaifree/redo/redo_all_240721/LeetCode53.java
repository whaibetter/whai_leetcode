package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/4 17:52
 * @注释
 */
public class LeetCode53 {

    @Test
    public void test()
    {

        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        Solution1 solution = new Solution1();
        System.out.println(solution.maxSubArray(nums));
        System.out.println(solution.maxSubArray(new int[]{5,4,-1,7,8}));

    }

    class Solution {
        public int maxSubArray(int[] nums) {
            int nowSum = 0;
            int right = 0;
            int max = Integer.MIN_VALUE;
            while (right < nums.length) {
                nowSum += nums[right];
                max = Math.max(max, nowSum);
                if (nowSum < 0) {
                    nowSum = 0;
                }
                right++;
            }
            return max;
        }
    }

    class Solution1 {
        /**
         * dp
         *
         * dp[i] 表示包含第i个元素的最大和的连续子数组的和
         * dp[0] = nums[0]
         *   [-2,1,-3,4,-1,2,1,-5,4]
         * dp -2 1 -3 4  3 5 6 1 5
         *
         * if nums[i]+dp[i-1]>0
         *    dp[i] = nums[i]+dp[i-1]
         * else
         *    dp[i] = nums[i]
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0 && nums[i] + dp[i - 1] > 0) {
                    // dp[i-1]>0 才能用，不然只能拖累
                    dp[i] = nums[i] + dp[i - 1];
                } else {
                    dp[i] = nums[i];
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    @Test
    public void test2()
    {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        Solution2 solution = new Solution2();
        System.out.println(solution.maxSubArray(nums));
    }

    class Solution2 {
        /**
         * dp[i] 表述i位置的最大子数组的和
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0 && dp[i - 1] + nums[i] > 0) {
                    // 不要拖后腿
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
            }
            return Arrays.stream(dp).max().getAsInt();
        }

    }
}
