package cn.whaifree.leetCode.String;

import org.junit.Test;

public class LeetCode541 {

    @Test
    public void test() {
        String s = "1234567890";
        int k = 4;
        System.out.println(new Solution().reverseStr(s, k));
    }

    class Solution {
        public String reverseStr(String s, int k) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i = i + k * 2) {
                reverse(chars, i, i + k - 1);
            }
            return new String(chars);
        }

        public void reverse(char[] chars, int start, int end) {
            // 边界判断, 如果要逆转的区间越界，就把后面这一段都逆转了，取最大值就好了
            if (end > chars.length - 1) {
                end = chars.length - 1;
            }
            while (start < end) {
                char tmp = chars[start];
                chars[start] = chars[end];
                chars[end] = tmp;
                start++;
                end--;
            }
        }
    }
}
