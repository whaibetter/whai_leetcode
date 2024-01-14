package cn.whaifree.redo.redo_24_1_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/13 13:36
 * @注释
 */
public class LeetCode225 {

    @Test
    public void test() {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        myStack.push(9);
        myStack.push(10);
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());


    }


    class MyStack {

        public Deque<Integer> queue = null;
        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        public void push(int x) {
            int size = queue.size();
            queue.add(x);
            for (int i = 0; i < size; i++) {
                queue.add(queue.pop());
            }
        }

        public int pop() {
            return queue.pop();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
