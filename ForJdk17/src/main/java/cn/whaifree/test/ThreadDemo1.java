package cn.whaifree.test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class ThreadDemo1 {

    static Object lock = new Object();
    static int num = 0;

//    public static void main(String[] args) {
//        new Thread(() -> {
//            for (int i = 0; i < 50; i++) {
//                synchronized (lock){
//                    while (num%2!=0){
//                        try {
//                            lock.wait(); // wait会释放锁
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    num++;
//                    System.out.println("a");
//                    lock.notifyAll();
//                }
//            }
//
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 50; i++) {
//                synchronized (lock){ // wait释放锁后这个线程就能拿到锁
//                    while (num%2!=1){
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    num++;
//                    System.out.println("b");
//                    lock.notifyAll();
//                }
//            }
//        }).start();
//    }

    static class CountDownLatchDemo2{

        public static void main(String[] args) throws InterruptedException {

            AtomicInteger num = new AtomicInteger(9);
            CountDownLatch countDownLatch = new CountDownLatch(num.get());

            for (int i = 0; i < 9; i++) {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " " + finalI);
                    countDownLatch.countDown();
                }).start();
            }


            countDownLatch.await(10, TimeUnit.SECONDS);

            new Thread(() -> System.out.println("上面的9个执行完了,轮到我了 wait complete！")).start();

        }
    }

    static class CyclicBarrierDemo2{

        public static void main(String[] args) throws InterruptedException {
//            Proxy.newProxyInstance()
            // 每9个cyclicBarrier.await()执行后，都执行一次wait
            CyclicBarrier cyclicBarrier = new CyclicBarrier(9,
                    () -> System.out.println("上面的9个执行完了,轮到我了 wait complete！") // 等待前9次执行结束
            );


            for (int i = 0; i < 9; i++) {
                int finalI = i;
                new Thread(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + finalI);
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        }
    }

    static class FutureGetDemo{
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService executorService = Executors.newFixedThreadPool(4); // 设置4，4个核心线程
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                int finalI = i;
                Future<?> submit = executorService.submit(() -> {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " " + finalI);
                    return null;
                });
                futures.add(submit);
            }

            for (Future<?> future : futures) {
                future.get();
            }

            System.out.println("上面的9个执行完了,轮到我了 wait complete！");
            executorService.shutdown();
        }
    }



}

class p2{

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Boolean>> ar = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future<Boolean> submit = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    if (finalI == 3) {
                        throw new MyException();
                    }
                    return true;
                }
            });
            ar.add(submit);
        }


        try {
            for (Future<Boolean> submit : ar) {
                submit.get();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof MyException){
                System.out.println("我捕获到了");
            }
        }
        System.out.println("1");


        try {
            for (int count = 1; count <= 5; count++) {
                if (count == 3) {
                    //故意制造一下异常
                    int num = 1 / 0;
                } else {
                    System.out.println("count:" + count + " 业务正常执行");
                }
            }
        } catch (Exception e) {
            System.out.println("try catch  在for 外面的情形， 出现了异常，for循环显然被中断");
        }

        for (int count = 1; count <= 5; count++) {
            try {
                if (count == 3) {
                    //故意制造一下异常
                    int num = 1 / 0;
                } else {
                    System.out.println("count:" + count + " 业务正常执行");
                }
            } catch (Exception e) {
                System.out.println("try catch  在for 里面的情形， 出现了异常，for循环显然继续执行");
            }
        }




    }
}

class dMyException extends Exception {

    static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                if (map.containsKey("key")) {
                    map.remove("key");
                }

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (!map.containsKey("key")) {
                    map.put("key", 1);
                }
            }
        }).start();
    }

}
class mockException{

    public static void main(String[] args) throws ExecutionException, InterruptedException {




        List<Future<Integer>> futures = new ArrayList<>();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
                () -> 1
                , Executors.newFixedThreadPool(10));
        futures.add(future);
        for (Future<Integer> integerFuture : futures) {
            System.out.println(integerFuture.get());
        }



        ThreadLocal<Object> objectThreadLocal = new InheritableThreadLocal<>();
        objectThreadLocal.set("test");
        objectThreadLocal.get();

        ReentrantLock lock = new ReentrantLock();
        lock.tryLock();
        lock.lock();
        /**
         * lock 方法
         * 阻塞等待：调用 lock() 方法时，如果锁已经被其他线程持有，当前线程将阻塞等待直到获得锁。
         * 不可中断：默认情况下，lock() 方法不会响应中断信号。这意味着即使当前线程被中断，它仍然会继续等待锁。
         * 保证获取锁：只要线程最终没有被中断或终止，它总会获取到锁。
         * tryLock 方法
         * 非阻塞：调用 tryLock() 方法时，如果锁可用，则立即获取锁并返回 true；如果锁已被其他线程持有，则立即返回 false 而不会阻塞等待。
         * 可选超时：tryLock 还有一个带超时参数的版本 tryLock(long time, TimeUnit unit)，允许线程在指定时间内等待锁。如果在超时时间内锁仍未被获取，则返回 false。
         * 可中断：tryLock(long time, TimeUnit unit) 方法可以响应中断信号，如果线程在等待过程中被中断，则抛出 InterruptedException。
         */

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    int num = 1 / 0;
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "";
            }
        });
        String s = "-";
        try {
             s = stringCompletableFuture.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
//            System.out.println("超时了");
            s = "超时了";
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            throw new RuntimeException(e);
        }

        System.out.println(s);
    }

    private int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 表示四个方向
    /**
     *
     * @param grid 原地板
     * @param visited 浏览的点
     * @param x 起始位置
     * @param y
     */
    public void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<>(); // 定义队列
        queue.offer(new int[]{x, y}); // 起始节点加入队列
        visited[x][y] = true; // 只要加入队列，立刻标记为访问过的节点

        while (!queue.isEmpty()) { // 开始遍历队列里的元素
            int[] cur = queue.poll(); // 从队列取元素
            int curx = cur[0];
            int cury = cur[1]; // 当前节点坐标

            for (int i = 0; i < 4; i++) { // 开始想当前节点的四个方向左右上下去遍历
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1]; // 获取周边四个方向的坐标
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue;  // 坐标越界了，直接跳过
                if (!visited[nextx][nexty]) { // 如果节点没被访问过
                    queue.offer(new int[]{nextx, nexty});  // 队列添加该节点为下一轮要遍历的节点
                    visited[nextx][nexty] = true; // 只要加入队列立刻标记，避免重复访问
                }
            }
        }
    }

}


class MyException extends RuntimeException {


    private static ThreadPoolExecutor resetInstanceExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {


        List<String> instance = Arrays.asList("i-12345678", "i-23456789", "i-34567890", "i-45678901", "i-56789012",
                "i-67890123", "i-78901234", "i-89012345", "i-90123456", "i-101234567");
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Long startTimestamp = System.currentTimeMillis();

        Map<String, Future<Boolean>> resetInstanceMap = new HashMap<>();
        boolean exceedRateLimit = false; // 没有被限频
        for (String instanceId : instance) {
            Future<Boolean> result = resetInstanceExecutor.submit(new ResetInstanceCallable(instanceId,countDownLatch));
            resetInstanceMap.put(instanceId, result);
        }

        for (Map.Entry<String, Future<Boolean>> futureEntry : resetInstanceMap.entrySet()) {
            try {
                futureEntry.getValue().get();
            }
            catch (MyException ex){
                System.out.println("my exception"); // Callable内抛出的自定义异常无法被捕获，Callable内抛出的自定义异常无法被捕获
                // get()方法会抛出一个ExecutionException，而这个异常的cause才是原始的异常
                exceedRateLimit = true;
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            } catch (ExecutionException e) {
                System.out.println("exception");
            }
        }

        System.out.println(exceedRateLimit==false? "没有超过限制" : "超过限制");
    }
}

class ResetInstanceCallable implements Callable<Boolean> {

    private String instanceId;
    private CountDownLatch countDownLatch;

    public ResetInstanceCallable(String instanceId, CountDownLatch countDownLatch) {
        this.instanceId = instanceId;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Boolean call() {
        throw new MyException();
    }
}

