package cn.whaifree.redo.redo_all_241016;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/26 12:23
 * @注释
 */
public class LeetCode32 {

    class Solution {
        public int longestValidParentheses(String s) {
            Deque<Integer> stack = new LinkedList<>();
            boolean[] dp = new boolean[s.length()];

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    dp[pop] = true;
                    dp[i] = true;
                }
            }

            int left = 0;
            int right = 0;
            int max = 0;
            while (right < dp.length) {
                if (!dp[right]) {
                    right++;
                    left = right ;
                }else {
                    right++;
                }
                max = Math.max(max, right - left);
            }
            return max;
        }
    }

}
