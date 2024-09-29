package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 13:16
 * @注释
 */
public class LeetCode005 {

    @Test
    public void test() {
        String s = "babad";
        String res = new Solution1().longestPalindrome(s);
        System.out.println(res);
    }

    class Solution {
        /**
         * 确定中间节点，不断向外扩展，判断有几个回文
         * - 中间为1个字符
         * - 中间为2个字符
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {

            char[] chars = s.toCharArray();
            String res = "";
            for (int i = 0; i < chars.length; i++) {
                String index1 = getHuiWenByIndex(chars, i, i);
                if (index1.length() > res.length()) {
                    res = index1;
                }
                String index2 = getHuiWenByIndex(chars, i, i + 1);
                if (index2.length() > res.length()) {
                    res = index2;
                }
            }
            return res;
        }

        public String getHuiWenByIndex(char[] chars, int left,int right) {
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
            }
            return new String(chars, left + 1, right - left - 1);
        }
    }

    class Solution1 {
        /**
         * 动态规划
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            /**
             * dp[i][j] 表示i-j是否为回文串
             * dp[i][j] =
             * s[i] == s[j]
             *  1. i=j true
             *  2. i=j+1 true
             *  3. dp[i+1][j-1] 确定了遍历顺序 从下往上，从左往右
             */
            int length = s.length();
            boolean[][] dp = new boolean[length][length];

            int left = 0;
            int right = 0;
            for (int i = length - 1; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (i == j || i == j - 1 || dp[i + 1][j - 1]) {
                            if (right - left <= j - i) {
                                left = i;
                                right = j;
                            }
                            dp[i][j] = true;
                        }
                    }
                }
            }


            return s.substring(left, right + 1);
        }
    }
}
