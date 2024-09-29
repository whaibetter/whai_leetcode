package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/22 13:23
 * @注释
 */
public class LeetCode300 {

    @Test
    public void test()
    {
        int[] nums = {4,10,4,3,8,9};
        Solution solution = new Solution();
        int i = solution.lengthOfLIS(nums);
        System.out.println(i);
    }

    class Solution {
        /**
         * int[i][j] 表示i-j的最长严格递增子序列长度
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];


            // dp[i]表示i之前包括i的以(nums[i]结尾)(即每次都必须包括i，每次都需要和i比较)的最长递增子序列的长度
            // 位置i的最长升序子序列 = j从0到i-1各个位置的 最长升序子序列 + 1 的 最大值
            Arrays.fill(dp, 1);
            int res = 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                res = Math.max(res, dp[i]);// 取长的子序列
            }

            return res;
        }
    }


    class Solution1 {

        public int lengthOfLIS(int[] nums) {
            // 最长严格递增子序列的长度
            /**
             * dp[i] 表示包含从0-i包含i（i在每次循环中是最后一个）的最长递增子序列的长度
             */
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1); // 最初就是1个

            int res = 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    // 从0到i-1
                    // nums[j] < nums[i] 就证明 nums[j] 小于 nums[i] ，前面有多少个j就计算多少次
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1); // dp[i] 为0-（j-1）的最长递增子序列长度，dp[j] + 1 为0-j的最长递增子序列长度，取两者最大值
                    }
                }
                res = Math.max(res, dp[i]); // 每次获得以i为最后一个的最长递增子序列的长度
            }
            return res;
        }
    }


}
