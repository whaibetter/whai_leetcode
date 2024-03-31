package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 13:41
 * @注释
 */
public class LeetCode28 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int i = solution.strStr("ab", "a");
        System.out.println(i);
    }

    class Solution {
        public int strStr(String haystack, String needle) {
            int length = needle.length();
            for (int i = 0; i < haystack.length() - length + 1; i++) {
                if (haystack.substring(i, i + length).equals(needle)) {
                    return i;
                }
            }
            return -1;
        }
    }

}
