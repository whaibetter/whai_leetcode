package cn.whaifree.redo.redo_all_240511;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/16 20:36
 * @注释
 */
public class LeetCode647 {

    @Test
    public void test() {
        System.out.println(new Solution1().countSubstrings("a"));
    }


    class Solution1 {
        public int countSubstrings(String s) {
            char[] c = s.toCharArray();
            int sum = 0;
            for (int i = 0; i < c.length; i++) {
                sum += count(c, i, i);
                sum += count(c, i, i + 1);
            }
            return sum;
        }

        public int count(char[] c, int start, int end) {
            int count = 0;
            while (start >= 0 && end < c.length && c[start] == c[end]) {
                start--;
                end++;
                count++;
            }
            return count;
        }

    }

    class Solution {

        /**
         * dp[i][j] 表示i-j回文子串的个数
         * i-j 可以由 i-1 j+1 推出 所以必须先从右到左推
         * if dp[i+1][j-1] && s[i]==s[j] || i==j i+1=j
         * dp[i][j] = true
         * else
         * dp[i][j] = false
         *
         * @param s
         * @return
         */
        public int countSubstrings(String s) {
            int size = s.length();
            boolean[][] dp = new boolean[size + 1][size + 1];
            int count = 0;
            for (int i = size; i > 0; i--) {
                for (int j = i; j <= size; j++) {
                    if (i == j || ((i == j - 1 || dp[i + 1][j - 1]) && s.charAt(i - 1) == s.charAt(j - 1))) {
                        dp[i][j] = true;
                    }
                    if (dp[i][j]) count++;
                }
            }
            return count;
        }
    }


}
