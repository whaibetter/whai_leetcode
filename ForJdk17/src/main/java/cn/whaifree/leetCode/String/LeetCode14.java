package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 22:09
 * @注释
 */
public class LeetCode14 {
    @Test
    public void test() {
        Solution solution = new Solution();
        String[] strs = {"d", "c"};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }

    class Solution {
        public String longestCommonPrefix(String[] strs) {

            String min = strs[0];
            for (String s : strs) {
                if (s.length() < min.length()) {
                    min = s;
                }
            }

            int index = 0;

            while (index < min.length()) {
                char c = strs[0].charAt(index);
                for (int i = 1; i < strs.length; i++) {
                    if (strs[i].charAt(index) != c) {
                        return strs[0].substring(0, index);
                    }
                }
                index++;
            }

            return min;
        }
    }

    @Test
    public void test2() {
        Solution2 solution = new Solution2();
        String[] strs = {"flower","flow","flight"};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }

    class Solution2 {
        public String longestCommonPrefix(String[] strs) {
            String str = strs[0];
            for (int i = 1; i < strs.length; i++) {
                str = common(str, strs[i]);
            }
            return str;
        }

        public String common(String a, String b) {
            int len = Math.min(a.length(), b.length());
            for (int i = 0; i < len; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return a.substring(0, i);
                }
            }
            return a.substring(0, len);
        }
    }
}
