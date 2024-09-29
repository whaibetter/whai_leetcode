package cn.whaifree.redo.redo_all_240511;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/3 10:36
 * @注释
 */
public class LeetCode28 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int i = solution.strStr("hello", "ll");
        System.out.println(i);
        System.out.println(solution.strStr("aaaaa", "bba"));
        System.out.println(solution.strStr("a", "a"));
    }
    class Solution {
        public int strStr(String haystack, String needle) {
            for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }
            return -1;
        }
    }
}
