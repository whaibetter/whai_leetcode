package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/19 14:37
 * @注释
 */
public class LeetCode647 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int res = solution.countSubstrings("abc");
        System.out.println(res);
    }

    class Solution {
        public int countSubstrings(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                res += count(s, i, i);
                res += count(s, i, i + 1);
            }
            return res;
        }

        public int count(String s, int start, int end) {
            int res = 0;
            while (start >= 0 && end < s.length()) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                res++;
                start--;
                end++;
            }
            return res;
        }
    }
}
