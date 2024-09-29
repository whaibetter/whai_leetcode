package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/12 0:37
 * @注释
 */
public class LeetCode718 {

    @Test
    public void test() {
        System.out.println(new Solution().findLength(new int[]{3,2,1,4,7},new int[]{1,2,3,2,1}));
    }


    class Solution {
        /**
         * dp[i][j] 表示 i-1，j-1为结尾的最长字数组长度
         *
         *     1 2 3 2 1
         *
         *  3  0 0 1 1 1
         *  2  0 1 1 2 2       max上左边 相等取i-1 j-1 +1
         *  1
         *  4
         *  7
         *
         * @param nums1
         * @param nums2
         * @return
         */
        public int findLength(int[] nums1, int[] nums2) {
            int[][] dp = new int[nums1.length+1][nums2.length+1];
            int max = 0;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums1[i] == nums2[j]) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    }else {
                        dp[i + 1][j + 1] = 0;
                    }
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
            return max;
        }
    }
}
