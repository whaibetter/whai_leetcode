package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 14:02
 * @注释
 */
public class LeetCode647 {

    @Test
    public void test()
    {
        System.out.println(new Solution1().countSubstrings("aaa"));
    }


    class Solution {
        public int countSubstrings(String s) {

            char[] chars = s.toCharArray();
            int res = 0;
            for (int i = 0; i < chars.length; i++) {
                res += getHuiWenCount(chars, i, i);
                res += getHuiWenCount(chars, i, i + 1);

            }
            return res;
        }

        /**
         *
         * @param chars
         * @param left
         * @param right
         * @return left 和 right 为中心的回文串的个数
         */
        public int getHuiWenCount(char[] chars, int left, int right) {
            int res = 0;
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                res++;
                left--;
                right++;
            }
            return res;
        }
    }

    class Solution1 {
        /**
         * dp[i][j] 表示i-j内回文的个数
         *
         * s[i]==s[j]
         * 1. i=j  1
         * 2. i=j-1 2
         * 3. dp[i+1][j-1] + 1 从下往上，左到右遍历
         *
         * @param s
         * @return
         */
        public int countSubstrings(String s) {
            char[] chars = s.toCharArray();
            int length = chars.length;
            boolean[][] dp = new boolean[chars.length][chars.length];

            int res = 0;
            for (int i = length - 1; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                    if (chars[i] == chars[j]) {
                        if (j - i == 1 || i == j || dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                            res++;
                        }
                    }
                }
            }

            return res;
        }
    }

}
