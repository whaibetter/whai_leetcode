package cn.whaifree.leetCode.String;

import org.junit.Test;

public class LeetCode344 {

    @Test
    public void test() {
        char[] s = "helco1".toCharArray();
        new Solution().reverseString(s);
        for (char c : s) {
            System.out.println(c);
        }
    }



    class Solution {


        public void reverseString(char[] s) {
//            for (int i = 0; i < s.length / 2; i++) {
//                char tmp = s[i];
//                s[i] = s[s.length - i - 1];
//                s[s.length - i - 1] = tmp;
//            }

            int left = 0;
            int right = s.length - 1;
            while (left < right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
                left++;
                right--;
            }
        }
    }

}
