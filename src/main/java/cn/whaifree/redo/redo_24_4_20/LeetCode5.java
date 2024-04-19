package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/19 13:18
 * @注释
 */
public class LeetCode5 {

    @Test
    public void test()
    {

        String s = "bb";
        String s1 = new Solution1().longestPalindrome(s);
        System.out.println(s1);
    }

    class Solution {
        /**
         * dp[i][j] 表示i-j是不是回文
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int length = s.length();
            boolean[][] dp = new boolean[length][length];

            int maxLeft = 0;
            int maxRight = 0;

            // dp[i][j] 由(i+1)~(j-1)确定，即子串
            // 则 dp[i][j] 由dp[i+1][j-1]确定
            for (int i = length; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                    if (s.charAt(i) == s.charAt(j)
                            && (i == j || i + 1 == j || dp[i + 1][j - 1])
                            // 一个元素是回文 两个元素也是回文
                    ) {
                        if ((maxRight - maxLeft) <= j - i) {
                            maxLeft = i;
                            maxRight = j;
                        }
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }

            return s.substring(maxLeft, maxRight + 1);
        }
    }

    class Solution1 {
        /**
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            // 从中间向外扩散获得最长的回文串
            int length = s.length();
            String maxRes = "";
            for (int i = 0; i < length; i++) {
                String s1 = maxLengthHuiWen(s, i, i);
                String s2 = maxLengthHuiWen(s, i, i + 1);
                if (maxRes.length() < s1.length()) {
                    maxRes = s1;
                }
                if (maxRes.length() < s2.length()){
                    maxRes = s2;
                }
            }
            return maxRes;
        }

        /**
         * 向外扩散的两个指针
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
