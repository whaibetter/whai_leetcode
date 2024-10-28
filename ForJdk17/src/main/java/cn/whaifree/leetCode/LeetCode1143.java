package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/24 12:00
 * @注释
 */
public class LeetCode1143 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int res = solution.longestCommonSubsequence("bsbininm","jmjkbkjkv" );
        System.out.println(res);
    }

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {

            // 公共子序列 dp[len1+1][len2+1]
            // dp[i][j] 表示 text1的前i-1与text2的前j-1中有多长的公共子序列长度
            // dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
            // if text1[i]==text2[i]
            //
            //
            int len1 = text1.length();
            int len2 = text2.length();

            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    // 如果相等
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        //dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                        // dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                    }

                }
            }

            return dp[len1][len2];
        }
    }

}
