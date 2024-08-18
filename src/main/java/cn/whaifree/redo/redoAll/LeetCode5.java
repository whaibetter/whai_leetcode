package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/18 9:38
 * @注释
 */
public class LeetCode5 {

    @Test
    public void test() {

        System.out.println(new Solution1().longestPalindrome("cbbd"));

    }
    class Solution {
        /**
         * dp[i][j] 表示i-j是否是回文
         *
         *  b a b a d
         *
         *   0 1 2 3 4
         * 0 1 0 1
         * 1   1 0
         * 2     1
         * 3       1
         * 4         1
         *
         * if s[i]==s[j] && dp[i+1][j-1]
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            char[] charArray = s.toCharArray();
            int length = s.length();
            boolean[][] dp = new boolean[length][length];

            for (int i = 0; i < length; i++) {
                dp[i][i] = true;
            }

            int first = 0;
            int last = 0;
            for (int i = length - 1; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    if (charArray[i] == charArray[j] && (dp[i + 1][j - 1] || j == i + 1)) {
                        dp[i][j] = true;
                        if (j - i > last - first) {
                            first = i;
                            last = j;
                        }
                    }
                }
            }
            return s.substring(first, last + 1);
        }
    }

    class Solution1{
        public String longestPalindrome(String s) {

            String maxLength = "";
            for (int i = 0; i < s.length(); i++) {
                String s1 = maxLengthHuiWen(s, i, i + 1);
                String s2 = maxLengthHuiWen(s, i, i);
                if (s1.length() > maxLength.length()) {
                    maxLength = s1;
                }
                if (s2.length() > maxLength.length()) {
                    maxLength = s2;
                }
            }
            return maxLength;
        }

        /**
         * 向外扩散的两个指针
         *
         * @param s
         * @param start
         * @param end
         * @return
         */
        public String maxLengthHuiWen(String s, int start, int end) {
            int length = s.length();
            while (start >= 0 && end < length) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                start--;
                end++;
            }
            return s.substring(start + 1, end);
        }

    }
}


class LeetCode647 {
    public static void main(String[] args) {
        System.out.println(new Solution().countSubstrings("aaa"));
    }
    static class Solution {
        public int countSubstrings(String s) {
            char[] charArray = s.toCharArray();
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }

            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (charArray[i] == charArray[j] && (j == 1 + i || dp[i + 1][j - 1])) {
                        dp[i][j] = true;
                    }
                }
            }

            int count = 0;
            for (boolean[] booleans : dp) {
                for (boolean aBoolean : booleans) {
                    if (aBoolean) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
