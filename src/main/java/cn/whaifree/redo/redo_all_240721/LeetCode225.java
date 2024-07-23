package cn.whaifree.redo.redo_all_240721;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/23 23:43
 * @注释
 */
public class LeetCode225 {
    public static void main(String[] args)
    {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }

    static class MyStack {

        Deque<Integer> q1;
        Deque<Integer> q2;

        public MyStack() {
            q1 = new LinkedList<Integer>();
            q2 = new LinkedList<Integer>();
        }

        public void push(int x) {
            q1.add(x);
        }

        public int pop() {
            while (q1.size() > 1) {
                q2.add(q1.pop());
            }
            int tmp = q1.pop();
            while (!q2.isEmpty()) {
                q1.add(q2.pop());
            }
            return tmp;
        }

        public int top() {
            while (q1.size() > 1) {
                q2.add(q1.pop());
            }
            int tmp = q1.pop();
            q2.add(tmp);
            while (!q2.isEmpty()) {
                q1.add(q2.pop());
            }
            return tmp;
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

}
