package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/2 10:31
 * @注释
 */
public class LeetCode17 {

    @Test
    public void test() {
        String digits = "23";
        new Solution1().letterCombinations(digits).forEach(s -> System.out.println(s));
    }

    class Solution {
        List<String> map = new ArrayList<>();
        List<String> result = new ArrayList<>();
        // 记录 路径
        StringBuilder s = new StringBuilder();

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return result;
            }
            map.add(0, null);
            map.add(1, null);
            map.add(2, "abc");
            map.add(3, "def");
            map.add(4, "ghi");
            map.add(5, "jkl");
            map.add(6, "mno");
            map.add(7, "pqrs");
            map.add(8, "tuv");
            map.add(9, "wxyz");
            backTracking(digits, 0, digits.length());
            return result;
        }

        /**
         *
         * @param digits 原始字符 23
         * @param number 使用到第几个字符
         * @param needLength 需要几个字符，等价于digits.length
         */
        void backTracking(String digits, int number,int needLength) {
            if (s.length() == needLength) {
                result.add(new String(s.toString()));
                return;
            }

            int c = digits.charAt(number) - 48;
            String sValue = map.get(c);
            int length = sValue.length();
            // 对每个字符处理
            for (int i = 0; i < length; i++) {
                char c1 = sValue.charAt(i);
                s.append(c1);
                backTracking(digits, number + 1, needLength);
                s.deleteCharAt(s.length() - 1);
            }
        }
    }

    class Solution1 {
        List<String> map = new ArrayList<>();
        List<String> result = new ArrayList<>();
        // 记录 路径
        StringBuilder s = new StringBuilder();

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return result;
            }
            map.add(0, null);
            map.add(1, null);
            map.add(2, "abc");
            map.add(3, "def");
            map.add(4, "ghi");
            map.add(5, "jkl");
            map.add(6, "mno");
            map.add(7, "pqrs");
            map.add(8, "tuv");
            map.add(9, "wxyz");

            backTracking(digits, 0);
            return result;
        }

        /**
         *
         * @param digits 原始字符 23
         * @param number 使用到第几个字符
         */
        void backTracking(String digits, int number) {
            if (s.length() == digits.length()) {
                result.add(new String(s.toString()));
                return;
            }

            int c = digits.charAt(number) - 48;
            String sValue = map.get(c);
            int length = sValue.length();
            // 对每个字符处理
            for (int i = 0; i < length; i++) {
                char c1 = sValue.charAt(i);
                s.append(c1);
                backTracking(digits, number + 1);
                s.deleteCharAt(s.length() - 1);
            }
        }
    }
}
