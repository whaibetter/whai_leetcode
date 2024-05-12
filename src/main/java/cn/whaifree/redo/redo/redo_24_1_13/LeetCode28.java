package cn.whaifree.redo.redo.redo_24_1_13;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/14 11:26
 * @注释
 */
public class LeetCode28 {

    @Test
    public void test() {
        System.out.println(new Solution().strStr("leetcode", "leeto"));
    }

    class Solution {
        public int strStr(String haystack, String needle) {

            int length = needle.length();
            int index = 0;
            int giveLength = haystack.length();
            while (index <= giveLength - length) {
                if (haystack.substring(index, index + length).equals(needle)) {
                    return index;
                }
                index++;
            }
            return -1;
        }
    }
}
