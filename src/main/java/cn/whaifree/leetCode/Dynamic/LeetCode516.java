package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/2 15:57
 * @注释
 */
public class LeetCode516 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }

    class Solution {
        /**
         * 子序列
         * dp[i][j] 表示 i-j的最长回文子序列长度
         *    0 1 2 3 4
         * 0  1
         * 1  2 1
         * 2  3 2 1
         * 3  3 2 1 1  不相等取上、右边的最大值
         * 4  4 3 3 1 1  相等 取dp[i+1][j-1]+2
         *
         * @param s
         * @return
         */
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j >= 0; j--) {
                    if (i == j) {
                        dp[i][j] = 1;
                        continue;
                    }
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j]= dp[i - 1][j + 1] + 2;
                    }else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j + 1]);
                    }
                }
            }
            return dp[s.length() - 1][0];
        }
    }
}
