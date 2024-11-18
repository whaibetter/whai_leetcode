package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/17 19:49
 * @注释
 */
public class LeetCode718_1 {


    @Test
    public void test() {
        System.out.println(
                new Solution()
                        .findLength(
                                new int[]{1, 2, 3, 2, 1, 5, 8, 9},
                                new int[]{3, 2, 1, 5, 8, 4, 7}
                        ));
    }

    class Solution {
        /**
         *
         * 0-i 0-j 最长的公共字数组的长度 ， 必须连续
         *    1 2 3 2 1 7
         *  3 0 0 1 0 0 0
         *  2 0 1 0 2 0 0   if == math,max(i-1 j-1 +1 )  else
         *  1 1 0 0 0 3 0
         *  4 0
         *  7
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
