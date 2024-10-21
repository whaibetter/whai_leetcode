package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 22:46
 * @注释
 */
public class LeetCode20 {

    @Test
    public void test() {
        String s = ")";
        Solution solution = new Solution();
        boolean result = solution.isValid(s);
        System.out.println(result);
    }

    class Solution {
        public boolean isValid(String s) {

            Deque<Character> stack = new LinkedList<>();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    Character pop = stack.pop();
                    if (c == ')' && pop != '(') {
                        return false;
                    }
                    if (c == '}' && pop != '{') {
                        return false;
                    }
                    if (c == ']' && pop != '[') {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

    }
}
