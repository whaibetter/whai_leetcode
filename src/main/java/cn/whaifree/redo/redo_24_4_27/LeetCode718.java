package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/1 13:06
 * @注释
 */
public class LeetCode718 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums1 = new int[]{1,2,3,2,1};
        int[] nums2 = new int[]{3,2,1,4,7};
        int length = solution.findLength(nums2, nums1);
        System.out.println(length);
    }

    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            /**
             * 子数组 连续==>遇到不相等要重置
             * dp[i][j]表示以i-1 j-1为结尾的最长子数组长度
             *    ''  1, 2, 3, 2, 1
             * '' [0, 0, 0, 0, 0, 0],
             * 3  [0, 0, 0, 1, 0, 0],
             * 2  [0, 0, 1, 0, 2, 0],
             * 1  [0, 1, 0, 0, 0, 3],
             * 4  [0, 0, 0, 0, 0, 0],
             * 7  [0, 0, 0, 0, 0, 0]
             *
             * 相等 取i-1 j-1 + 1
             *
             */
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            int max = 0;
            for (int i = 1; i < nums1.length + 1; i++) {
                for (int j = 1; j < nums2.length + 1; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }else {
                        dp[i][j] = 0;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }
}
