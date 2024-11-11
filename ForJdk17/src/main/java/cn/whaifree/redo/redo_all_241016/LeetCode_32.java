package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/11 11:36
 * @注释
 */
public class LeetCode_32 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("(()(()()"));

    }

    class Solution {
        public int longestValidParentheses(String s) {

            boolean[] mark = new boolean[s.length()];

            char[] arr = s.toCharArray();
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '(') {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    mark[pop] = true;
                    mark[i] = true;
                }
            }

            int left = 0;
            int right = 0;
            int maxLen = 0;
            while (right < mark.length) {
                if (!mark[right]) {
                    maxLen = Math.max(maxLen, right - left);
                    left = right + 1;
                }
                right++;
            }
            return Math.max(maxLen, right - left);
        }
    }

}
