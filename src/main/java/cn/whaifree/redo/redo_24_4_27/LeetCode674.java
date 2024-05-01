package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/1 12:59
 * @注释
 */
public class LeetCode674 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {1};
        int lengthOfLCIS = solution.findLengthOfLCIS(nums);
        System.out.println(lengthOfLCIS);
    }

    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            /**
             * dp[i] 表示以i结尾的最长递增子序列
             *
             *  1 3 5 4 7
             *  1 2 3 0 1
             */

            int[] dp = new int[nums.length + 1];
            int max = 1;
            dp[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= nums[i - 1]) {
                    dp[i] = 1;
                }else {
                    dp[i] = dp[i - 1] + 1;
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }

    }
}
