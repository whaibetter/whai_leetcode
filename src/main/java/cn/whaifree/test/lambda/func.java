package cn.whaifree.test.lambda;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/9 18:12
 * @注释
 */
@FunctionalInterface
public interface func {

    public String doSome();
}

class b{
    // 创建一个线程局部变量threadLocal1，并设置其初始值为10
    private static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 10);
    // 创建一个线程局部变量threadLocal2，并设置其初始值为"Hello"
    private static ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "Hello");

    public static void main(String[] args) {
        // 创建一个新线程thread，并为其指定一个Runnable实现
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 获取threadLocal1的值
            int value1 = threadLocal1.get();  // 放入Thread的Map key 为ThreadLocal<integer> value为10
            // 获取threadLocal2的值
            String value2 = threadLocal2.get(); // 放入Thread的Map key 为ThreadLocal<String> value为Hello
            // 打印threadLocal1的值
            System.out.println("ThreadLocal1: " + value1);
            // 打印threadLocal2的值
            System.out.println("ThreadLocal2: " + value2);
        });



        // 创建一个新线程thread，并为其指定一个Runnable实现
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(100000);
            // 获取threadLocal1的值
            int value1 = threadLocal1.get();  // 放入Thread的Map key 为ThreadLocal<integer> value为10
            // 获取threadLocal2的值
            String value2 = threadLocal2.get(); // 放入Thread的Map key 为ThreadLocal<String> value为Hello
            // 打印threadLocal1的值
            System.out.println("ThreadLocal1: " + value1);
            // 打印threadLocal2的值
            System.out.println("ThreadLocal2: " + value2);
        });

        // 启动线程thread
        thread.start();
        // 启动线程thread
        thread2.start();
    }
}




class fu{
    public static void main(String[] args) {

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("call");
                return "1234";
            }
        };
        FutureTask futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        while (!futureTask.isDone()) {
            System.out.println("2222");
        }


        func func = () -> "123";
    }
}
