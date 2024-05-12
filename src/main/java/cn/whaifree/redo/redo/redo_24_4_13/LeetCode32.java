package cn.whaifree.redo.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 14:14
 * @注释
 */
public class LeetCode32 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int i = solution.longestValidParentheses("()(()");
        System.out.println(i);
    }

    class Solution {
        public int longestValidParentheses(String s) {
            /**
             * 找到匹配
             * 最长
             */
            boolean[] flag = new boolean[s.length()];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                }else if (!stack.isEmpty()) {
                    flag[stack.pop()] = true;
                    flag[i] = true;
                }
            }

            // 统计flag连续出现的
            int longMax = 0;
            int flagIndex = 0;
            for (int i = 0; i < flag.length; i++) {
                if (flag[i]) {
                    flagIndex++;
                }else {
                    flagIndex = 0;
                }
                longMax = Math.max(longMax, flagIndex);
            }

            return longMax;
        }
    }
    class Solution1 {

        /**
         * 计数器，
         * 计算左括号右括号的数量，如果右括号数量更多就重置
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, maxlength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                }else if (s.charAt(i) == ')') {
                    right++;
                }
                if (right > left) {
                    left = 0;
                    right = 0;
                }
                if (right == left) {
                    maxlength = Math.max(maxlength, 2 * left);
                }
            }

            left = 0;
            right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                }else if (s.charAt(i) == ')') {
                    right++;
                }
                if (left > right) {
                    left = 0;
                    right = 0;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * right);
                }
            }


            return maxlength;
        }
    }
}
