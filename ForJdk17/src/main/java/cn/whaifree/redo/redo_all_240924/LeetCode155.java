package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 13:06
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

    static class Item{
        public int value;
        public int nowMin;

        public Item(int value, int nowMin) {
            this.value = value;
            this.nowMin = nowMin;
        }

        public int getNowMin() {
            return nowMin;
        }

        public int getValue() {
            return value;
        }
    }

    class MinStack {
        Deque<Item> stack;
        public MinStack() {
            stack = new LinkedList<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new Item(val, val));
                return;
            }
            Item peek = stack.peek();
            int min = Math.min(peek.nowMin, val);
            stack.push(new Item(val, min));
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().getValue();
        }

        public int getMin() {
            return stack.peek().getNowMin();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
