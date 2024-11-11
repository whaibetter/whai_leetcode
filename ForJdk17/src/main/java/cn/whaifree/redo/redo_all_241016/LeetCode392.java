package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/8 17:22
 * @注释
 */
public class LeetCode392 {

    @Test
    public void test() {
        System.out.println(new Solution().isSubsequence("abc", "ahbgdc"));
    }

    class Solution {
        public boolean isSubsequence(String s, String t) {
            if (s.isBlank()) {
                return true;
            }

            int sIndex = 0;
            int i = 0;
            for (; i < t.length(); i++) {
                if (sIndex == s.length()) {
                    break;
                }
                if (s.charAt(sIndex) == t.charAt(i)) {
                    sIndex++;
                }
            }
            return sIndex == s.length();
        }

    }
    @Test
    public void test2() {
        System.out.println(new Solution1().isSubsequence("abc", "ahbgdc"));
    }

    class Solution1 {
        /**
         *
         *    ''a h b g d c
         * '' 1 1 1 1 1 1 1
         *  a 0 1 1 1 1 1 1
         *  b 0 0 0 1 1 1 1
         *  c 0 0 0 0 0 0 1
         *
         * 0-1背包问题，
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {
            boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
            for (int i = 0; i < t.length() + 1; i++) {
                dp[0][i] = true;
            }
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) != t.charAt(j - 1) || !dp[i - 1][j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = true;
                    }
                }
            }
            return dp[s.length()][t.length()];
        }

    }

}
