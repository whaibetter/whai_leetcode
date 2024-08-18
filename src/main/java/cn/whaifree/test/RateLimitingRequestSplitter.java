package cn.whaifree.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RateLimitingRequestSplitter {

    private int rateLimit;
    private BlockingQueue<String> requestQueue = new LinkedBlockingQueue<>();

    public RateLimitingRequestSplitter(int rateLimit) {
        this.rateLimit = rateLimit;
    }

    public void addRequest(String request) {
        requestQueue.add(request);
    }

    public void processRequests() throws InterruptedException {
        while (!requestQueue.isEmpty()) {
            List<String> requests = new ArrayList<>();
            for (int i = 0; i < rateLimit; i++) {
                String poll = requestQueue.poll();
                if (poll == null) {
                    break;
                }
                requests.add(poll);
            }
            System.out.println("send requests: " + requests);
            simulateRequestAndResponse(requests);
            Thread.sleep(1000);
        }
    }

    private boolean simulateRequestAndResponse(List<String> requests) {
        // 模拟请求处理和响应，这里简单假设都成功
        try {
            Thread.sleep(500);
            for (int i = 0; i < requests.size(); i++) {
                System.out.println("Processed request: " + requests.get(i));
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 假设从配置中心获取的限频为 3
        int rateLimitFromConfig = 3;
        RateLimitingRequestSplitter splitter = new RateLimitingRequestSplitter(rateLimitFromConfig);

        // 添加一些示例请求
        for (int i = 0; i < 100; i++) {
            splitter.addRequest("Request " + i);
        }

        splitter.processRequests();
    }
}



class CvmResetHandler {

    private static final int MAX_CONCURRENT_REQUESTS = 5; // 最大并发请求数
    private static final int QUEUE_CAPACITY = 100; // 队列最大容量

    private static final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            MAX_CONCURRENT_REQUESTS, MAX_CONCURRENT_REQUESTS,
            60L, TimeUnit.SECONDS, queue);

    public void resetCvm(String serverId) {
        Runnable task = () -> {
            try {
                System.out.println("Starting to reset CVM: " + serverId);
                resetCvmServer(serverId);
                System.out.println("Finished resetting CVM: " + serverId);
            } catch (Exception e) {
                System.err.println("Error resetting CVM: " + serverId);
                e.printStackTrace();
            }
        };

        // 提交任务到线程池
        executor.submit(task);
    }

    private void resetCvmServer(String serverId) throws InterruptedException {
        // 模拟重置操作
        Thread.sleep(new Random().nextInt(5000)); // 模拟耗时操作
        System.out.println("Resetting CVM: " + serverId);
    }

    public static void main(String[] args) {
        CvmResetHandler handler = new CvmResetHandler();

        // 创建多个重置请求
        for (int i = 0; i < 50; i++) {
            String serverId = "server-" + i;
            handler.resetCvm(serverId);
        }

        // 等待所有任务完成
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Some tasks did not complete in time.");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for tasks to complete.");
        }
    }
}
