package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 16:27
 * @注释
 */
public class LeetCode300 {

    class Solution {

        /**
         * dp[i] 表示以0-i的，最长递增子序列的长度
         * int tmp
         *
         * [10,9,2,5,3,7,101,18]
         *   1 1 1 2 2 3 4   4
         *
         *  i = 0 - len
         *  j = i -- 0
         *
         *
         * 上升的增加尽可能少
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {

            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            for (int i = 1; i < nums.length; i++) {
                // 以i结尾的最大递增长度
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            return Arrays.stream(dp).max().getAsInt();
        }
    }
}
