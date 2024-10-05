package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 12:25
 * @注释
 */
public class LeetCode718 {

    @Test
    public void test() {
        int[] nums1 = new int[]{0,1,1,1,1};
        int[] nums2 = new int[]{1,0,1,0,1};
        Solution solution = new Solution();
        int length = solution.findLength(nums1, nums2);
        System.out.println(length);
    }

    class Solution {

        /**
         * 这个问题关键在于重复的数字会被重复使用
         *
         *    0,1,1,1,1
         *  1 0 1 1 1 1
         *  0 1 1 1 1 1
         *  1 1 2
         *  0
         *  1
         *以下标i - 1为结尾的A” 标明一定是 以A[i-1]为结尾的字符串
         *
         * @param nums1
         * @param nums2
         * @return
         */
        public int findLength(int[] nums1, int[] nums2) {

            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            int max = 0;
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }

}
