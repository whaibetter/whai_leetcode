package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 逆转波兰表达式
 */
public class LeetCode150 {

    @Test
    public void test() {
        System.out.println(new Solution1().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(new Solution1().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }


    class Solution {
        public int evalRPN(String[] tokens) {

            Deque<String> stack = new LinkedList<>();

            int index = 0;
            while (index < tokens.length) {
                String token = tokens[index];
                if (token.equals("+")) {
                    Integer pop1 = Integer.parseInt(stack.pop());
                    Integer pop2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(pop1 + pop2));
                } else if (token.equals("-")) { //避免多次判断
                    Integer pop1 = Integer.parseInt(stack.pop());
                    Integer pop2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(pop2 - pop1));
                } else if (token.equals("*")) {
                    Integer pop1 = Integer.parseInt(stack.pop());
                    Integer pop2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(pop2 * pop1));
                } else if (token.equals("/")) {
                    Integer pop1 = Integer.parseInt(stack.pop());
                    Integer pop2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(pop2 / pop1));
                } else {
                    stack.push(token);
                }
                index++;
            }
            return Integer.parseInt(stack.pop());

        }
    }

    class Solution1 {
        public int evalRPN(String[] tokens) {

            Deque<String> stack = new LinkedList<>();

            for (String token : tokens) {
                switch (token) {
                    case "+":
                        int i = Integer.parseInt(stack.pop());
                        int j = Integer.parseInt(stack.pop());
                        stack.push(String.valueOf(i + j));
                        break;
                    case "-":
                        int k = Integer.parseInt(stack.pop());
                        int l = Integer.parseInt(stack.pop());
                        stack.push(String.valueOf(l - k));
                        break;
                    case "*":
                        int m = Integer.parseInt(stack.pop());
                        int n = Integer.parseInt(stack.pop());
                        stack.push(String.valueOf(n * m));
                        break;
                    case "/":
                        int o = Integer.parseInt(stack.pop());
                        int p = Integer.parseInt(stack.pop());
                        stack.push(String.valueOf(p / o));
                        break;
                    default:
                        stack.push(token);
                        break;
                }
            }
            return Integer.parseInt(stack.pop());
        }
    }

}
