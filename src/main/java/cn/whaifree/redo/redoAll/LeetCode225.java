package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 16:11
 * @注释
 */
public class LeetCode225 {

    @Test
    public void test()
    {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
    }

    class MyStack {

        Deque<Integer> queue1;
        Deque<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue1.add(x);
        }

        public int pop() {
            while (queue1.size() != 1) {
                queue2.add(queue1.pop());
            }
            Integer pop = queue1.pop();
            while (!queue2.isEmpty()) {
                queue1.add(queue2.pop());
            }
            return pop;
        }

        public int top() {
            return queue1.peekLast();
        }

        public boolean empty() {
            return queue1.isEmpty();
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
