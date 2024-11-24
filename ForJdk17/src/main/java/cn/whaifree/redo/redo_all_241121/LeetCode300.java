package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/21 22:50
 * @注释
 */
public class LeetCode300 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(solution.lengthOfLIS(nums));
    }


    class Solution {
        /**
         * 最长递增子序列
         * [10,9,2,5,3,7,101,18]
         *  1  1 1 2
         *
         *  right从左到右
         *  left 往左搜索到0，找到有序列更大的
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            int max = 1;
            for (int right = 1; right < nums.length; right++) {
                int left = right - 1;
                while (left >= 0) {
                    if (nums[left] < nums[right]) {
                        dp[right] = Math.max(dp[left] + 1, dp[right]);
                    }
                    left--;
                }
            }
            return max;
        }
    }
}
