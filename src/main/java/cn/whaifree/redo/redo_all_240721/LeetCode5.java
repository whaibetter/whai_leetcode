package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 15:29
 * @注释
 */
public class LeetCode5 {
    @Test
    public void test()
    {
        System.out.println(new Solution().longestPalindrome("babad"));
    }

    class Solution {
        public String longestPalindrome(String s) {
            String maxHuiWen = "";
            for (int i = 0; i < s.length(); i++) {
                String huiWenA = getHuiWen(s, i, i);
                String huiWenB = getHuiWen(s, i, i + 1);
                if (huiWenA.length() > maxHuiWen.length()) {
                    maxHuiWen = huiWenA;
                }
                if (huiWenB.length() > maxHuiWen.length()) {
                    maxHuiWen = huiWenB;
                }
            }
            return maxHuiWen;
        }


        public String getHuiWen(String s, int start, int end) {
            while (start >= 0 && end < s.length() &&s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }
            return s.substring(start + 1, end);
        }
    }
}
