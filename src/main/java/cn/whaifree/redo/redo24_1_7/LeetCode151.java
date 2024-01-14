package cn.whaifree.redo.redo24_1_7;

import org.junit.Test;

public class LeetCode151 {

    @Test
    public void test() {
        Solution solution = new Solution();
        String s = solution.reverseWords(" abc    def  ");

        System.out.println(s);
    }

    class Solution {
        public String reverseWords(String s) {

            // 去除空格
            StringBuilder stringBuilder = removeExtraSpace(s);
            // 逆转
            reverse(stringBuilder, 0, stringBuilder.length() - 1);
            // 对单词逆转
            reverseWord(stringBuilder);
            return stringBuilder.toString();
        }


        public StringBuilder removeExtraSpace(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (s.charAt(left)==' ') left++;
            while (s.charAt(right)==' ') right--;

            StringBuilder stringBuilder = new StringBuilder();
            while (left <= right) {
                if (s.charAt(left) != ' ' || s.charAt(left + 1) != ' ') {
                    stringBuilder.append(s.charAt(left));
                }
                left++;
            }
            return stringBuilder;
        }

        public void reverse(StringBuilder s, int left, int right) {
            while (left < right) {
                char tmp = s.charAt(left);
                s.setCharAt(left, s.charAt(right));
                s.setCharAt(right, tmp);
                left++;
                right--;
            }

        }


        public void reverseWord(StringBuilder s) {

            int left = 0;
            int right = 0;
            while (right <= s.length()) {
                if (right == s.length() - 1 || s.charAt(right + 1) == ' ') {
                    reverse(s, left, right);
                    left = right++ + 2;
                }
                right++;
            }


        }
    }
}
