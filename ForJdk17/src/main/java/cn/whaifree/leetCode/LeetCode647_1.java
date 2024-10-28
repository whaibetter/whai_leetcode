package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/2 12:00
 * @注释
 */
public class LeetCode647_1 {
    @Test
    public void test()
    {
        String s = "abc";
        System.out.println(new Solution().countSubstrings(s));
    }

    class Solution {
        /**
         *
         * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目
         *
         * dp[i][j] 表示 i-j回文字符串的数量
         *
         * dp[i][j] 由 dp[i+1][j-1]推出
         * if n[i]==n[j]
         *      dp[i][j] = dp[i-1][j-1]+2
         * else
         *      dp[i][j] = dp[i][j-1]+dp[i+1][j]
         *
         *   abac
         *    '' 0 1 2 3
         * ''  0 0 0 0 0
         *  0  0 1
         *  1  0 0 1
         *  2  0 1 0 1
         *  3  0 0 0 0 1
         *
         *
         * @param s
         * @return
         */
        public int countSubstrings(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j >= 0; j--) {
                    if (i == j ) {
                        dp[i][j] = true;
                        res++;
                        continue;
                    }
                    if ((s.charAt(i) == s.charAt(j)) && (i == j + 1 || dp[i - 1][j + 1])) {
                        dp[i][j] = true;
                        res++;
                    }
                }
            }
            return res;
        }
    }


}
