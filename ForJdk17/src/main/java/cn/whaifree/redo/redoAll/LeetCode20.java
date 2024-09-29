package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 18:15
 * @注释
 */
public class LeetCode20 {

    @Test
    public void test() {
        Solution solution = new Solution();
        String s = "()()[]([])";
        boolean valid = solution.isValid(s);
        System.out.println(valid);
    }
    class Solution {
        public boolean isValid(String s) {
            char[] charArray = s.toCharArray();
            Deque<Character> stack = new LinkedList<>();

            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    Character pop = stack.pop();

                    if (c == ')' && pop != '(') {
                        return false;
                    }else if (c == ']' && pop != '[') {
                        return false;
                    }else if (c == '}' && pop != '{') {
                        return false;
                    }

                }
            }
            return stack.isEmpty();
        }

    }
}
