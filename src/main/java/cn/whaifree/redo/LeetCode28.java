package cn.whaifree.redo;

import org.junit.Test;

public class LeetCode28 {

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
