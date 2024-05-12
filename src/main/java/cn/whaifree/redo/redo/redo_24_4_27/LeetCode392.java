package cn.whaifree.redo.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/1 12:17
 * @注释
 */
public class LeetCode392 {

    @Test
    public void test() {

        String s = "agc";
        String t = "ahbgdc";
        boolean subsequence = new Solution().isSubsequence(s, t);
        System.out.println(subsequence);
    }

    class Solution {
        /**
         *
         * dp[i][j] 表示 s 的0-i是否 在 t的子序列中出现的长度
         *
         *    '' a h b g d c
         * ''  0 0 0 0 0 0 0
         * a   0 1 1 1 1 1 1  不相等 左边  相等 i-1 j-1 +1
         * b   0 0 0 2 2 2 2
         * c   0 0 0 0 0 0 3
         *
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {

            int[][] dp = new int[s.length() + 1][t.length() + 1];

            for (int i = 1; i < s.length() + 1; i++) {
                for (int j = 1; j < t.length()+1; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
            return s.length() == dp[s.length()][t.length()];
        }

    }

    class Solution1 {
        public boolean isSubsequence(String s, String t) {
            int indexS = 0;
            int indexT = 0;
            while (indexS < s.length() && indexT < t.length()) {
                if (s.charAt(indexS) == t.charAt(indexT)) {
                    indexS++;
                }
                indexT++;
            }
            return indexS == s.length();
        }
    }
}
