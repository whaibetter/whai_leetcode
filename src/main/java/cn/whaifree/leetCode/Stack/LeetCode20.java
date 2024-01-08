package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 括号匹配
 */
public class LeetCode20 {

    @Test
    public void test() {
        System.out.println(new Solution().isValid("()[]{}"));
        System.out.println(new Solution().isValid("}"));
    }

    class Solution {
        public boolean isValid(String s) {
            Deque<Character> stack = new LinkedList<>();
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                if (aChar == '{' || aChar == '[' || aChar == '(') {
                    stack.push(aChar);
                }else if (aChar == ']'){
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }else if (aChar == '}'){
                    if (stack.isEmpty() ||stack.pop()!= '{') {
                        return false;
                    }
                }else if (aChar == ')'){
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }
    }
}
