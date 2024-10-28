package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/28 11:06
 * @注释
 */
public class LeetCode8 {
    @Test
    public void test() {
        System.out.println(new Solution().myAtoi("999999224324"));
    }

    class Solution {
        public int myAtoi(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            char[] strChar = s.toCharArray();

            int left = 0;
            while (left < s.length() && strChar[left] == ' ') {
                left++;
            }
            if (left == s.length()) {
                return 0;
            }
            boolean neg = false;
            if (s.charAt(left) == '-') {
                left++;
                neg = true;
            } else if (s.charAt(left) == '+') {
                left++;
            }

            long res = 0;
            while (left < s.length() && isDigit(s.charAt(left))) {
                res = res * 10 + (s.charAt(left) - '0');
                left++;
                if (res > Integer.MAX_VALUE) {
                    return neg? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                if (res < Integer.MIN_VALUE) {
                    return neg? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }
            return (int) (neg? -res : res);
        }

        public boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }
    }

}
