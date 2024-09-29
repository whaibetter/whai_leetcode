package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 11:27
 * @注释
 */
public class LeetCode32 {
    @Test
    public void test() {
        int i = new Solution3().longestValidParentheses("(()");
        System.out.println(i);
    }

    class Solution {
        /**
         * 找出最长有效（格式正确且连续）括号子串的长度。
         *
         * dp[i] 表示从0-i内最长有效括号子串的长度
         *
         * if char[i]='(' || retailLeft<0
         *      = dp[i-1]
         * if char[i]=')'&&retailLeft>0
         *      = dp[i-1]+2
         *
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            char[] chars = s.toCharArray();
            int[] dp = new int[s.length() + 1];
            int retailLeft = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(' ) {
                    retailLeft++;
                    dp[i] = dp[i - 1];
                } else if (chars[i] == ')' && retailLeft > 0) {

                }
            }


            return 0;
        }

    }

    class Solution1 {
        /**
         * ()(()
         *
         * <a href="https://leetcode.cn/problems/longest-valid-parentheses/solutions/2719468/chao-jian-dan-fang-fa-zhi-hui-gua-hao-pi-nbby">...</a>
         *
         * 1.匹配成功 用栈匹配，把所有匹配成功的flag设置为1
         * 2.最长连续 统计flag中最长连续1的长度
         *
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int[] flag = new int[s.length()];
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    if (s.charAt(pop) == '(') {
                        flag[i] = 1;
                        flag[pop] = 1;
                    }
                }
            }

            // 计算flag中最长连续出现1的次数
            int len = 0;
            int maxLen = Integer.MIN_VALUE;
            for (int i = 0; i < flag.length; i++) {
                if (flag[i] == 1) {
                    len += 1;
                }else {
                    maxLen = Math.max(maxLen,len);
                    len = 0;
                }
            }

            return Math.max(maxLen, len); // (() 这个用例
        }
    }
    class Solution3 {
        /**
         * 使用两个计数器
         * - 当两个计数器相等 则检查最长匹配
         * - 当右边比左边还多，重置
         *
         * 需要 从前往后+从后往前
         *
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, maxlength = 0;
            // 左右计数器，
            // - 一旦右边计数比左边大的时候，重置
            // - 左右计数器相等的时候，匹配maxLength

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }

                if (left == right) {
                    maxlength = Math.max(maxlength, left + right);
                } else if (right > left) {
                    // 右边比左边还多，重置，重新计算
                    right = 0;
                    left = 0;
                }
            }
            // 重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。

            // 只要从左到右再来一次，

            left = 0;
            right = 0;
            for (int i = s.length() - 1; i > 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }

                if (left == right) {
                    maxlength = Math.max(maxlength, left + right);
                } else if (left > right) {
                    // 右边比左边还多，重置，重新计算
                    right = 0;
                    left = 0;
                }
            }

            return maxlength;
        }
    }

}
