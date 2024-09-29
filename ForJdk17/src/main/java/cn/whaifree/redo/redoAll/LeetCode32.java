package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 14:22
 * @注释
 */
public class LeetCode32 {

    @Test
    public void test(){
        String s = "()(()";
        Solution solution = new Solution();
        int i = solution.longestValidParentheses(s);
        System.out.println(i);

    }

    class Solution {
        public int longestValidParentheses(String s) {
            char[] charArray = s.toCharArray();
            Deque<Integer> stack = new LinkedList<>();


            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '(') {
                    stack.push(i);
                }else if (!stack.isEmpty()){
                    Integer pop = stack.pop();
                    dp[pop] = true;
                    dp[i] = true;
                }
            }

            // 计算flag中最长连续出现1的次数
            int len = 0;
            int maxLen = Integer.MIN_VALUE;
            for (int i = 0; i < dp.length; i++) {
                if (dp[i]) {
                    len += 1;
                }else {
                    maxLen = Math.max(maxLen,len);
                    len = 0;
                }
            }

            return Math.max(maxLen, len); // (() 这个用例
        }
    }

}
