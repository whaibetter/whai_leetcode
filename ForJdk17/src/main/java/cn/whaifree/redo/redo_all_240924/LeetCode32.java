package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/29 11:36
 * @注释
 */
public class LeetCode32 {

    @Test
    public void test() {
        String s = "()(()";
        Solution solution = new Solution();
        int i = solution.longestValidParentheses(s);
        System.out.println(i);
    }

    class Solution {


        public int longestValidParentheses(String s) {
            char[] charArray = s.toCharArray();
            boolean[] dp = new boolean[charArray.length];
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '(') {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    dp[i] = true;
                    dp[pop] = true;
                }
            }

            // 统计连续的trur
            int left = 0;
            int right = 0;
            int max = 0;
            while (right < dp.length) {
                if (!dp[right]) {
                    max = Math.max(max, right - left);
                    left = right + 1;
                }
                right++;
            }
            max = Math.max(max, right - left);
            return max;
        }
    }
}
