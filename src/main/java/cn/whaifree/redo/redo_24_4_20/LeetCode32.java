package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/22 11:20
 * @注释
 */
public class LeetCode32 {

    @Test
    public void test() {

        int i = new Solution1().longestValidParentheses("()(()");
        System.out.println(i);
    }

    class Solution {
        /**
         * 左右括号的数量，遇到右括号数量>左括号就重置
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int max = 0;

            int left = 0, right = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                }else {
                    right++;
                }

                if (left == right) {
                    max = Math.max(max, 2 * right);
                }

                if (left < right) {
                    left = 0;
                    right = 0;
                }
            }

            left = 0;
            right = 0;
            for (int i = s.length() - 1; i > 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }

                if (left == right) {
                    max = Math.max(max, 2 * right);
                }

                if (left > right) {
                    left = 0;
                    right = 0;
                }
            }

            return max;
        }
    }

    class Solution1 {
        public int longestValidParentheses(String s) {
            Deque<Integer> stack = new LinkedList<>();
            // boolean 判断是否出现过
            boolean[] flag = new boolean[s.length()];
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(') {
                    stack.push(i);
                }else if (!stack.isEmpty()){
                    Integer index = stack.pop();
                    flag[index] = true;
                    flag[i] = true;
                }
            }
            // 计算boolean[]中连续true的数量
            int max = 0;
            int now = 0;
            for (boolean b : flag) {
                if (b) {
                    now++;
                }else {
                    now = 0;
                }
                max = Math.max(max, now);
            }
            return max;
        }

    }
}
