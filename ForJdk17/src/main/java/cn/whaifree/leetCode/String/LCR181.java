package cn.whaifree.leetCode.String;

import org.junit.Test;

public class LCR181 {


    @Test
    public void main()
    {
        Solution solution = new Solution();
        String s = solution.reverseMessage(" abx  ");
        System.out.println(s);
    }

    class Solution {
        public String reverseMessage(String message) {
            String trim = message.trim();
            String[] split = trim.split("\s+");
            reverse(split);
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : split) {
                if (s.isEmpty()) {
                    continue;
                }
                stringBuilder.append(s).append(" ");
            }
            if (!stringBuilder.isEmpty()) stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }

        public void reverse(String[] strings) {
            int start = 0;
            int end = strings.length - 1;
            while (start < end) {
                swap(strings, start++, end--);
            }
        }

        public void swap(String[] strings, int start, int end) {
            String temp = strings[start];
            strings[start] = strings[end];
            strings[end] = temp;
        }
    }
}
