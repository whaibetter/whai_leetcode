package cn.whaifree.redo.redo_24_1_7;

import org.junit.Test;

public class LeetCode28_false {

    @Test
    public void test() {
        System.out.println(new Solution().strStr("hello", "h"));
    }

    class Solution {
        public int strStr(String haystack, String needle) {


            int begin = 0;
            int end = begin + needle.length();
            while (end <= haystack.length()) {
                if (haystack.substring(begin, end).equals(needle)) {
                    return begin;
                }
                begin++;
                end++;
            }



            return -1;
        }
    }
}
