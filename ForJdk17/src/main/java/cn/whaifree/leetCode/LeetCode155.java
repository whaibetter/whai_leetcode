package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 0:52
 * @注释
 */
public class LeetCode155 {

    @Test
    public void test2() {
        MinStack minStack = new MinStack();
        // ["MinStack","push","push","push","getMin","pop","top","getMin"]
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    class MinStack {

        static class Item{
            Integer item;
            Integer minV;

            public Item(Integer item, Integer minV) {
                this.item = item;
                this.minV = minV;
            }
        }

        Deque<Item> itemStack = null;

        public MinStack() {
            itemStack = new ArrayDeque<>();
        }

        public void push(int val) {
            // 找到当前最小值
            Integer minNow = Math.min(getMin(), val);

            Item item = new Item(val, minNow);
            itemStack.push(item);
        }

        public void pop() {
            itemStack.pop();
        }

        public int top() {
            if (itemStack.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            return itemStack.peek().item;
        }

        public int getMin() {
            if (itemStack.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            return itemStack.peek().minV;
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
    @Test
    public void test() {
        MinStack minStack = new MinStack();
        // ["MinStack","push","push","push","getMin","pop","top","getMin"]
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
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
