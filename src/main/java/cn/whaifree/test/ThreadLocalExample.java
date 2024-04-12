package cn.whaifree.test;

public class ThreadLocalExample {

    public static void main(String[] args) throws InterruptedException {
        for(int i=0 ; i<3; i++){
            new Thread(new MyThread()).start();
        }
    }
}

class MyThread  implements Runnable{

    private static ThreadLocal<Integer> localVariable = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default value = " + localVariable.get());
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        localVariable.set(localVariable.get() + 1);

        System.out.println("Thread Name= " + Thread.currentThread().getName() + " localVariable = " + localVariable.get());

    }
}

