package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/1 15:39
 * @注释
 */
public class LeetCode1143 {
    @Test
    public void test() {
        System.out.println(new Solution().longestCommonSubsequence("abcde", "ace"));
    }

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            /**
             * dp[i][j] 表示t1的0-i t2的0-j的公共子序列的长度
             *   '' a b c d e
             *''  0 0 0 0 0 0
             * a  0 1 1 1 1 1
             * c  0 1 1 2 2 2
             * e  0 1 1 2 2 3
             *
             * if t1[i]==t2[j]
             *      dp[i-1][j-1]+1
             * else
             *      dp[i-1][j]
             *
             */
            int len2 = text2.length();
            int len1 = text1.length();
            int[][] dp = new int[len2 + 1][len1 + 1];

            for (int i = 1; i < len2 + 1; i++) {
                for (int j = 1; j < len1 + 1; j++) {
                    if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        // 取前面已经匹配过的最大长度
                    }
                }
            }
            return dp[len2][len1];
        }
    }

}
