package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/25 11:28
 * @注释
 */
public class LeetCode1035 {

    @Test
    public void test()
    {
        int[] nums1 = new int[]{1,3,7,1,7,5};
        int[] nums2 = new int[]{1,9,2,5,1};
        System.out.println(new Solution().maxUncrossedLines(nums1, nums2));
    }

    class Solution {
        /**
         * 不相交，就是挨个求nums1的子序列在nums中出现的次数
         * - 不用连续
         * - 子序列
         *
         * dp[i][j] 表示 nums1从0-i-1，nums2从0-j-1，有几个相同的子序列
         *
         * if nums1[i] == nums2[j]
         *      dp[i][j] = dp[i-1][j-1]+1
         * else
         *      dp[i][j] = dp[i-1][j-1]
         *
         *
         * @param nums1 短的
         * @param nums2 长的
         * @return
         */
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return maxUncrossedLines(nums2, nums1);
            }
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];

            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        // 对应位置相等，则从i-1 j-1的值+1
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        // 对应的值不相等，则获取前面已经匹配的最大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[nums1.length][nums2.length];
        }

    }

}
