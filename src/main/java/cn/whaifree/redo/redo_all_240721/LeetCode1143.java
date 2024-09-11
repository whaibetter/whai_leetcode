package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/12 0:53
 * @注释
 */
public class LeetCode1143 {

    @Test
    public void test() {
        System.out.println(new Solution().longestCommonSubsequence("ddd", "ace"));
    }

    class Solution {
        /**
         *
         *
         * @param text1
         * @param text2
         * @return
         */
        public int longestCommonSubsequence(String text1, String text2) {
            int len1 = text1.length();
            int len2 = text2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    }else {
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }

                }
            }
            return dp[len1][len2];
        }
    }
}
