package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/22 12:16
 * @注释
 */
public class LeetCode5 {

    @Test
    public void test()
    {
        Solution1 solution = new Solution1();
        String s = "cbbd";
        String s1 = solution.longestPalindrome(s);
        System.out.println(s1);
    }

    class Solution {
        /**
         * 动态规划
         * dp[i][j] 表示 i-j的串是不是回文串
         * 同时记录i和j的最大差值的i和j
         *
         * s[i] = s[j] 时
         * dp[i][j] =
         * 1. i=j   true
         * 2. i=j+1  true
         * 3.       if(dp[i+1][j-1]) true
         *
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int length = s.length();
            boolean[][] dp = new boolean[length][length];

            int maxI = 0;
            int maxJ = 0;

            // dp[i+1][j-1] 决定了dp[i][j] 所以 要从下往上，从左往右遍历
            for (int i = length; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                    if (s.charAt(i) == s.charAt(j) && (i == j || i + 1 == j || dp[i + 1][j - 1])) {
                        if ((maxJ - maxI) <= j - i) {
                            maxI = i;
                            maxJ = j;
                        }
                        dp[i][j] = true;
                    }
                }
            }
            return s.substring(maxI, maxJ+1);
        }
    }

    class Solution1 {
        public String longestPalindrome(String s) {
            char[] chars = s.toCharArray();
            // 向外扩展，统计回文长度
            int length = s.length();
            int start = 0;
            int end = 0;
            for (int i = 0; i < length; i++) {

                int a = getHuiWenLengthReturnLength(chars, i, i);
                int b = getHuiWenLengthReturnLength(chars, i, i + 1);

                int maxLength = Math.max(a, b);
                if (maxLength > end - start){
                    start = i - (maxLength - 1) / 2;
                    end = i + maxLength / 2;
                }


            }
            return s.substring(start, end + 1);
        }

        public String getHuiWenLength(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
            }
            return new String(chars, left + 1, right - left - 1);
        }

        public int getHuiWenLengthReturnLength(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
            }
            return right - left - 1;
        }

    }

}
