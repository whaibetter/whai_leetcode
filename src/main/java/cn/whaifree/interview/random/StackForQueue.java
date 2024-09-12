package cn.whaifree.interview.random;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackForQueue {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.size());
        while (myStack.size()!=0){
            System.out.println(myStack.pop());
        }
    }

        static  class  MyStack{
            Queue<Integer> queue1 = new ArrayDeque<>();
            Queue<Integer> queue2 = new ArrayDeque<>();

            void push(int x){
                queue1.add(x);
            }

            int peek() {
                if (queue1.isEmpty()) {
                    return Integer.MAX_VALUE;
                }
                while (queue1.size()>1){
                    queue2.add(queue1.poll());
                }
                Integer poll = queue1.poll();
                queue2.add(poll);
                while (!queue2.isEmpty()) {
                    queue1.add(queue2.poll());
                }
                return poll;
            }

            int pop(){
                if (queue1.isEmpty()) {
                    return Integer.MAX_VALUE;
                }
                while (queue1.size()>1){
                    queue2.add(queue1.poll());
                }
                Integer poll = queue1.poll();
                while (!queue2.isEmpty()) {
                    queue1.add(queue2.poll());
                }
                return poll;
            }

            int size(){
                return queue1.size();
            }

        }
}


class MyStack {
    Queue<Integer> queue1 = null;
    Queue<Integer> queue2 = null;
    public MyStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
        queue1.add(x);

    }

    public int pop() {
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        while (queue1.size()>1){
            queue2.add(queue1.poll());
        }
        Integer poll = queue1.poll();
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return poll;
    }

    public int top() {
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        while (queue1.size()>1){
            queue2.add(queue1.poll());
        }
        Integer poll = queue1.poll();
        queue2.add(poll);
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return poll;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}