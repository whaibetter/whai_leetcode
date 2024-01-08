package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 * 注意：
 *
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 */
public class LeetCode225 {

    @Test
    public void test() {

        Deque<Integer> objects = new LinkedList<>();
        objects.add(1);
        objects.add(2);
        objects.push(3);
        System.out.println(objects.pop());
//        objects.forEach(x -> System.out.println(x));


        MyStack1 myStack = new MyStack1();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        System.out.println(myStack.top());
        System.out.println(myStack.pop());

    }


    /**
     * 每次入栈就加入其中的非空队列
     * 出栈就全部移动到另一个队列，并获取最后一个元素
     *
     *  队列用add // add 尾巴进
     *  栈用push // push 头进
     *  pop 头出
     *
     *  - 栈 push pop
     *  - 队列 add pop
     */
    class MyStack {

        Deque<Integer> queue1;
        Deque<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            if (!queue1.isEmpty()) {
                queue1.add(x);
            } else {
                queue2.add(x);
            }
        }

        public int pop() {
            if (!queue1.isEmpty()) {
                // 将queue全部导入queue2，并获取最后一个元素返回
                while (queue1.size()!=1) {
                    queue2.add(queue1.pop());
                }
                return queue1.pop();
            } else {
                // 将queue2全部导入queue1，并获取最后一个元素返回
                while (queue2.size()!=1) {
                    queue1.add(queue2.pop());
                }
                return queue2.pop();
            }
        }

        public int top() {
            if (!queue1.isEmpty()) {
                // 将queue全部导入queue2，并获取最后一个元素返回
                while (queue1.size()>1) {
                    queue2.add(queue1.pop());
                }
                Integer pop = queue1.pop();
                queue2.add(pop);
                return pop;
            } else {
                // 将queue2全部导入queue1，并获取最后一个元素返回
                while (queue2.size()>1) {
                    queue1.add(queue2.pop());
                }

                Integer pop = queue2.pop();
                queue1.add(pop);
                return pop;
            }
        }

        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }


    /**
     * push 的时候 把其替换到头部
     */
    class MyStack1 {

        Deque<Integer> queue;


        public MyStack1() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
            int size = queue.size();
            while (size != 1) {
                queue.add(queue.pop());
                size--;
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


}
