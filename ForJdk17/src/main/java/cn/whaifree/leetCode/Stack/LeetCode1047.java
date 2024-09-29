package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class LeetCode1047 {

    @Test
    public void test() {
        System.out.println(new Solution1().removeDuplicates("adff"));
    }

    class Solution {
        public String removeDuplicates(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                // 入栈前判断第一个是不是相同的
                if (!stack.isEmpty() && stack.peek() == aChar) {
                    stack.pop();
                }else {
                    stack.push(aChar);
                }

            }

            char[] ans = new char[stack.size()];
            for (int i = stack.size() - 1; i >=0; i--) {
                ans[i] = stack.pop();
            }

            return new String(ans);
        }
    }

    class Solution1 {
        /**
         * stringBuilder也能作为栈使用
         */
        public String removeDuplicates(String s) {
            // 将StringBuilder作为栈 abbacd
            StringBuilder res = new StringBuilder();

            int top = -1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (res.length() != 0 && c == res.charAt(top)) {
                    res.deleteCharAt(top);
                    top--;
                } else {
                    res.append(c);
                    top++;
                }
            }

            return res.toString();
        }
    }

}
