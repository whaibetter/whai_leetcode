package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/23 12:22
 * @注释
 */

public class LeetCode718 {
    @Test
    public void test()
    {
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};

        int i = new Solution1().findLength(nums1, nums2);
        System.out.println(i);
    }

    class Solution {
        public int findLength(int[] nums1, int[] nums2) {

            /**
             * 用二维数组可以记录两个字符串的所有比较情况
             * dp[i][j] 表示 以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度
             * dp[i][j] 可以由dp[i-1][j-1] + 1 和 0 两种情况推出
             *
             * dp[i][j]
             *      if nums1[i]==nums[j]
             *          dp[i][j] = dp[i-1][j-1] + 1
             *      else
             *          dp[i][j] = 0
             */
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            int res = 0;
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }

            return res;
        }
    }

    class Solution1 {
        public int findLength(int[] nums1, int[] nums2) {

            /**
             * dp[i][j]都是由dp[i - 1][j - 1]推出。那么压缩为一维数组，也就是dp[j]都是由dp[j - 1]推出。
             *
             */
            int[] dp = new int[nums1.length + 1];
            int res = 0;
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = nums2.length; j > 0; j--) { // 从后面开始比较，避免重复覆盖dp[i]
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[j] = dp[j - 1] + 1;
                    } else {
                        dp[j] = 0;
                    }
                    res = Math.max(res, dp[j]);
                }
            }

            return res;
        }
    }

}
