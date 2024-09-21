package cn.whaifree.test;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCacheThreadPool {

    public static void main(String[] args) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append(i);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes(Charset.defaultCharset()));

        byte[] buffer = new byte[10];
        int length = 0;

        while ((length = byteArrayInputStream.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, length));
            System.out.println("\n");
        }

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "233");
        String compute = map.compute("1", (k, v) -> {
            return k + v;
        });
        String merge = map.merge("1", "233", (k, v) -> {
            return k + v; // 没有就value，有就+value
        });
        System.out.println(merge);
        System.out.println(compute);

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    System.out.println(finalI
//                            +"   "+Thread.currentThread().getName());
//                }
//            });
//        }
    }


    @Test
    public void single() {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + Single.getSingle());
                }
            }).start();
        }
    }

    static class Single {
        private static Single o = null;

        public static Single getSingle() {
            if (o == null) {
                synchronized (Single.class) {
                    if (o == null) { // 防止第二个线程再创建一个
                        System.out.println("创建");
                        o = new Single();
                    }
                }
            }
            return o;
        }
    }


    static class Singleton {
        // 不使用volatile关键字
        private static Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        public static void main(String[] args) {
            // 创建多个线程尝试获取单例实例
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 10; j++) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(Thread.currentThread().getName() + " 获取到的实例为: " + Singleton.getInstance());
                            }
                        }).start();
                    }
                    System.out.println("-======");
                }).start();
            }
        }
    }




    static class MultiConditionDemo {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition1 = lock.newCondition();
        private final Condition condition2 = lock.newCondition();
        private String message = "等待消息";

        public static void main(String[] args) {

            MultiConditionDemo demo = new MultiConditionDemo();
            new Thread(() -> demo.producer()).start();
            new Thread(() -> demo.consumer()).start();
        }

        public void producer() {
            lock.lock();
            try {
                while ("等待消息".equals(message)) {
                    condition1.await(); // 等待条件1
                }
                message = "这是生产者发送的消息";
                System.out.println("生产者发送消息：" + message);
                condition2.signal(); // 通知条件2上的等待线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void consumer() {
            lock.lock();
            try {
                while (!"这是生产者发送的消息".equals(message)) {
                    condition2.await(); // 等待条件2
                }
                System.out.println("消费者接收到的消息：" + message);
                message = "等待消息";
                condition1.signal(); // 通知条件1上的等待线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class reentrantLockDemo {
        static ReentrantLock lock = new ReentrantLock();
        static int num = 0;
        static Condition condition = lock.newCondition();

        public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        lock.lock();

                        while (num % 2 == 0) {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("A");
                        num++;
                        condition.signalAll();
                        lock.unlock();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        lock.lock();
                        while (num % 2 != 0) {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("B");
                        num++;
                        condition.signalAll();
                        lock.unlock();

                    }
                }
            }).start();

        }
    }

    static class synDemo {
        static Object lock = new Object();
//        static Condition condition = lock.newCondition();
        static int num = 0;
        public static void main(String[] args) throws InterruptedException {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        synchronized (lock) {
                            while (num % 2 == 0) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println("A");
                            num++;
                            lock.notify();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        synchronized (lock) {
                            while (num % 2 == 1) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println("B");
                            num++;
                            lock.notify();
                        }
                    }
                }
            }).start();

        }

    }

}
