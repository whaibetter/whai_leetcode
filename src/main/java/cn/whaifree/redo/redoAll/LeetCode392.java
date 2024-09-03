package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/3 23:49
 * @注释
 */
public class LeetCode392 {
    @Test
    public void test() {
        String s = "aaaaaa";
        String t = "bbaaaa";
        Solution1 solution = new Solution1();
        boolean subsequence = solution.isSubsequence(s, t);
        System.out.println(subsequence);
    }

    class Solution {
        public boolean isSubsequence(String s, String t) {
            char[] sCharArray = s.toCharArray();
            char[] tCharArray = t.toCharArray();
            int index = 0;
            for (int i = 0; i < sCharArray.length; i++) {
                boolean find = false;
                while (index < tCharArray.length) {
                    if (sCharArray[i] == tCharArray[index]) {
                        find = true;
                        index++;
                        break;
                    }else {
                        index++;
                    }
                }
                if (!find) {
                    return false;
                }
            }
            return true;
        }
    }

    class Solution1 {
        /**
         * dp[i][j] 表示s的i在t的j中出现的长度
         *
         *  "abc" "ahbgdc"
         *     a h b q d c
         *   0
         * a 0 1 1 1 1 1 1  相等，取上+1
         * b 0 0 0 2
         * c 0
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {
            int[][] dp = new int[s.length() + 1][t.length() + 1];

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }

            return dp[s.length()][t.length()] == s.length();
        }
    }
}
