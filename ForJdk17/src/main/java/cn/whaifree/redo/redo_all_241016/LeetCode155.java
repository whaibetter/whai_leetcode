package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/4 11:10
 * @注释
 */
public class LeetCode155 {

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }

    class MinStack {

        Deque<int[]> deque;
        public MinStack() {
            deque = new ArrayDeque<>();
        }

        public void push(int val) {
            if (deque.isEmpty()) {
                deque.add(new int[]{val, val});
            }else {
                int[] peek = deque.peek();
                int min = peek[1];
                if (min > val) {
                    min = val;
                }
                deque.push(new int[]{val, min});
            }
        }

        public void pop() {
            deque.pop();
        }

        public int top() {
            if (deque.isEmpty()) {
                return -1;
            }
            int[] peek = deque.peek();
            return peek[0];
        }

        public int getMin() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peek()[1];
        }
    }

}
