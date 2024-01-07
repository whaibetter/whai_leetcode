package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.*;


public class LeetCode151 {


    // todo
    @Test
    public void test() {
        String s = "  a b cd ef g  ";
        System.out.println(new Solution2().reverseWords(s));


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
            StringBuilder stringBuilder = deleteSpace(s, ' ');
            reverse(stringBuilder, 0, stringBuilder.length() - 1);
            reverseWord(stringBuilder);
            // 移除多余空格
            // 反转char
            // 反转每个字符
            return stringBuilder.toString();
        }

        public void reverseWord(StringBuilder stringBuilder) {
            int start = 0;
            int index = 0;
            while (index < stringBuilder.length() + 1) {
                if (index == stringBuilder.length() || stringBuilder.charAt(index) == ' ') {
                    reverse(stringBuilder, start, index - 1);
                    start = index + 1;
                }
                index++;
            }
        }
        public void reverse(StringBuilder str, int start, int end) {
            while (start < end) {
                char tmp = str.charAt(start);
                str.setCharAt(start, str.charAt(end));
                str.setCharAt(end, tmp);
                end--;
                start++;
            }
        }


        /**
         * 双指针移动元素，移除target
         * - 参考LeetCode 27

         * @param target
         * @return
         */
        public StringBuilder deleteSpace(String s,char target) {

            // 前后指针
            int start = 0;
            int end = s.length() - 1;
            while (s.charAt(start) == target) start++;
            while (s.charAt(end) == target) end--;
            // 1. 删除首尾空格

            StringBuilder stringBuilder = new StringBuilder();
            while (start <= end) {
                if (s.charAt(start) !=target){
                    stringBuilder.append(s.charAt(start) );
                }else if (s.charAt(start)  == target && stringBuilder.charAt(stringBuilder.length() - 1) != target) {
                    stringBuilder.append(s.charAt(start) );
                }
                start++;
            }
            // 2. 如果遇到空格，判断上一个是不是空格
            //   - 不是，入String
            //   - 是，跳过


            return stringBuilder;
        }

    }


    class Solution2{

        public String reverseWords(String s) {
            // 删除前后空白
            String trim = s.trim();
            String[] split = trim.split("\\s+");
            List<String> list = Arrays.asList(split);
            Collections.reverse(list);
            return String.join(" ", list);
        }

    }

}
