package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 20:38
 * @注释
 */
public class LeetCode5 {

    @Test
    public void test() {
        String s = "baabd";
        Solution solution = new Solution();
        String result = solution.longestPalindrome(s);
        System.out.println(result);
    }

    class Solution {
        public String longestPalindrome(String s) {
            String max = "";
            for (int i = 0; i < s.length(); i++) {
                String A = isHuiWen(s, i, i);
                if (A.length() > max.length()) {
                    max = A;
                }
                String B = isHuiWen(s, i, i + 1);
                if (B.length() > max.length()) {
                    max = B;
                }
            }
            return max;
        }

        public String isHuiWen(String s, int start, int end) {
            while (start >= 0 && end < s.length()) {
                if (s.charAt(start) == s.charAt(end)) {
                    start--;
                    end++;
                }else {
                    break;
                }
            }
            return s.substring(start + 1, end);
        }


    }

    @Test
    public void test1() {
        String s = "aacab";
        Solution1 solution = new Solution1();
        String result = solution.longestPalindrome(s);
        System.out.println(result);
    }

    class Solution1 {
        public String longestPalindrome(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len+1][len+1];
            for (int i = 1; i <= len; i++) {
                dp[i][i] = true;
            }

            // dp[i][j] dp[i+1][j-1]
            int left = 0;
            int right = 0;
            for (int i = len; i > 0; i--) {
                for (int j = i; j <= len; j++) {
                    if (i == j) {
                        continue;
                    }
                    boolean b = s.charAt(i - 1) == s.charAt(j - 1);
                    if ((dp[i + 1][j - 1] || (i == j - 1)) && b) {
                        if (j - i > right - left) {
                            left = i - 1;
                            right = j - 1;
                        }
                        dp[i][j] = true;
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }
}
