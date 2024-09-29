package cn.whaifree.redo.redo_all_240721;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/24 22:17
 * @注释
 */
public class LeetCode232 {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }

    static class MyQueue {

        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public MyQueue() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            int res = stack1.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return res;
        }

        public int peek() {
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            int res = stack1.pop();
            stack2.push(res);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return res;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

}
