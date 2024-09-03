package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/4 0:09
 * @注释
 */
public class LeetCode674 {

    @Test
    public void test() {
        int[] nums = {1,3,5,4,7};
        System.out.println(new Solution1().findLengthOfLCIS(nums));
    }

    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int max = 0;
            int left = 0;
            int right = 0;
            while (right < nums.length - 1) {
                if (nums[right] < nums[right + 1]) {
                    right++;
                }else {
                    max = Math.max(max, right - left + 1);
                    right++;
                    left = right;
                }
            }

            return Math.max(max, right - left + 1);
        }
    }

    class Solution1 {
        /**
         * dp[i] 表示i目前的最长连续最长递增长度
         * 1,3,5,4,7
         * 1 2 3 1 2
         * @param nums
         * @return
         */
        public int findLengthOfLCIS(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    dp[i] = dp[i - 1] + 1;
                }else {
                    dp[i] = 1;
                }
            }
            return Arrays.stream(dp).max().getAsInt();
        }
    }
}
