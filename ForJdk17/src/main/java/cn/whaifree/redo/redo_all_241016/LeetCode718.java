package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/8 17:16
 * @注释
 */
public class LeetCode718 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.findLength(new int[]{1, 2, 3, 2, 1, 5, 8, 9}, new int[]{3, 2, 1, 5, 8, 4, 7}));
    }

    class Solution {
        /**
         * 连续
         * 0-i 0-j内最长公共的子数组的长度
         *
         *   1 2 3 2 1
         * 3 0 0 1 0 0
         * 2 0 1 0 2 0
         * 1 1 0 1
         * 4
         * 7
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
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max;
        }
    }

}
