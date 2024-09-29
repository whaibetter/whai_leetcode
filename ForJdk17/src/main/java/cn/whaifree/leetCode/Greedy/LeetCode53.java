package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/25 10:53
 * @注释
 */
public class LeetCode53 {
    @Test
    public void test() {
        System.out.println(new Solution1().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new Solution1().maxSubArray(new int[]{5,4,-1,7,8}));

    }

    class Solution {
        /**
         * 遇到加上变为负数，重新从0计算。
         * 因为前面那一串会拖累后面那串
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                // 加上某个数后是否会变为负数，会则直接从0开始计算，因为其只会拖累后面的串
                sum += nums[i];
                maxSum = Math.max(sum, maxSum);
                if (sum  < 0) {
                    sum = 0;
                }
            }


            return maxSum;
        }
    }


    class Solution1 {

        /**
         * 动态规化
         * dp[i] 表示i出最大子序列的和
         * if dp[i-1]+nums[i]>0  dp[i] = dp[i-1] + nums[i]
         * else dp[i] = nums[i]
         *
         * [-2,1,-3,4,-1,2,1,-5,4]
         * [-2,1,-3,4,3,5,6,1,5]
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
                max = Math.max(dp[i], max);
//                if (dp[i - 1] > 0 && dp[i - 1] + nums[i] > 0) {
//                    dp[i] = dp[i - 1] + nums[i];
//                } else {
//                    dp[i] = nums[i];
//                }
//                max = Math.max(dp[i], max);
            }
            return max;

        }
    }


}

