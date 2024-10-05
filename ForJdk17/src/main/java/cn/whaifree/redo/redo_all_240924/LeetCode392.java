package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 11:11
 * @注释
 */
public class LeetCode392 {

    @Test
    public void test() {
        String s = "bb";
        String t = "ahbgdc";
        System.out.println(new Solution().isSubsequence(s, t));
    }

    class Solution {
        /**
         *  '' a h b g d c
         *'' 1 1 1 1 1 1 1
         * a 0 1 1 1 1 1 1 == 用i-1 j-1  else 左边
         * b 0 0 0 1 1 1 1
         * c 0 0 0 0 0 0 1
         *
         *   '' a h b g d c
         *''  1 1 1 1 1 1 1
         * a  0 1 1 1 1 1 1
         * x  0 0 0 0 0 0 0
         * c  0 0 0 0 0 0 0
         *
         *
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {

            if (s.length() == 0) {
                return true;
            }
            if (t.length() == 0) {
                return false;
            }
            if (s.length() == 1 && t.length() == 1 ) {
                if (s.charAt(0) == t.charAt(0)) {
                    return true;
                }else {
                    return false;
                }
            }

            boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
            Arrays.fill(dp[0], true);

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
            return dp[s.length()][t.length()];
        }
    }
}
