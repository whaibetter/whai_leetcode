package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class LeetCode151 {

    @Test
    public void test() {
        String s = "  the   sky    is    blue";


        char[] chars = new Solution1().deleteSpace(s.toCharArray());
        for (char aChar : chars) {
            System.out.println("=" + aChar + "=");
        }
    }

    class Solution {
        public String reverseWords(String s) {
            List<String> list = new LinkedList<>();
            String[] split = s.split(" ");
            for (String s1 : split) {
                if (!(s1.equals("") || s1.equals(" "))) {
                    list.add(s1);
                }
            }
            StringBuilder buffer = new StringBuilder();
            int index = list.size() - 1;
            while (index > 0) {
                buffer.append(list.get(index--)+" ");
            }
            buffer.append(list.get(0));
            return buffer.toString();
        }

    }

    // 不要使用辅助空间，空间复杂度要求为O(1)
    class Solution1 {
        public String reverseWords(String s) {
            char[] chars = s.toCharArray();
            // 移除多余空格
            // 反转char
            // 反转每个字符
            return null;
        }

        public char[] deleteSpace(char[] chars) {

            int superFlowSpace = 0;

            int index = 0;
            System.out.println(chars[index] == ' ');
            while (chars[index] == ' ') {
                superFlowSpace++;
                index++;
            }
            index = 0;
            while (index < chars.length - 1 - superFlowSpace) {
                chars[index] = chars[index + superFlowSpace];
                if (' ' == chars[index + superFlowSpace] && ' ' == chars[index + superFlowSpace + 1]) {
                    superFlowSpace++;
                } else {
                    index++;
                }

            }


            return Arrays.copyOfRange(chars, 0, chars.length - superFlowSpace);
        }

    }

}
