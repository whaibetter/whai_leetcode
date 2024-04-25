package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/25 12:41
 * @注释
 */
public class LeetCode392 {
    @Test
    public void test() {
        String s = "abc";
        String t = "ahbgdc";
        boolean subsequence = new Solution1().isSubsequence(s, t);
        System.out.println(subsequence);
    }

    class Solution {
        /**
         * 子序列
         * s是否是t的子序列
         *
         * dp[i][j] 表示
         *
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {

            boolean flag = false;
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                while (j < t.length()) {
                    if (s.charAt(i) == t.charAt(j)) {
                        flag = true;
                        j++;
                        break;
                    }
                    j++;
                }
                if (!flag) {
                    return false;
                }
                flag = false;
            }
            return true;
        }
    }

    class Solution1 {
        /**
         * dp[i][j]表示s的i到t的j 相同子序列的长度
         *
         * 转变为 求两个序列相同子序列的长度
         *
         * ahbgdc
         * acbdc
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {

            int[][] dp = new int[s.length() + 1][t.length() + 1];
            char[] s1 = s.toCharArray();
            char[] t1 = t.toCharArray();
            for (int i = 1; i <= s1.length; i++) {
                for (int j = 1; j <= t1.length; j++) {
                    if (s1[i - 1] == t1[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            return dp[s1.length][t1.length] == s1.length;
        }
    }
}
