package cn.whaifree.interview.random;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

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

    public static void main(String[] args) {
        // 创建一个固定大小的线程池，使用 SynchronousQueue 作为任务队列
        ExecutorService executor = new ThreadPoolExecutor(
                2, // 核心线程数
                2, // 最大线程数
                0L, TimeUnit.MILLISECONDS, // 线程空闲时间
                new SynchronousQueue<Runnable>() // 使用 SynchronousQueue
        );

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        // 提交10个任务到线程池
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Task " + taskNumber + " is running on thread " + Thread.currentThread().getName());
                try {
                    // 模拟任务执行时间
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskNumber + " is completed");
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
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