package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 14:02
 * @注释
 */
public class LeetCode300 {

    @Test
    public void test() {
        System.out.println(new Solution().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    class Solution {
        /**
         *
         * dp[i] 表示0-i内最长递增子序列长度
         *
         * 10 9 2 5 3 7 101 18
         * 1  1 1 2 2 3 4   1
         *
         * for right
         *    for left=0--> right
         *        if left < right
         *             dp[right] = max dp[right], dp[left]+1
         *
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            for (int right = 0; right < nums.length; right++) {
                for (int left = 0; left < right; left++) {
                    if (nums[left] < nums[right]) {
                        dp[right] = Math.max(dp[right], dp[left] + 1);
                    }
                }
            }

            return Arrays.stream(dp).max().getAsInt();
        }
    }
}
