package cn.whaifree.springdemo.controller;

import cn.whaifree.springdemo.aspect.annotation.RateLimiter;
import cn.whaifree.springdemo.constant.LimitType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:40
 * @注释
 */
@RestController
public class TestController {
    @PostMapping("/test")
    @RateLimiter(key = "test:", time = 10, count = 1) // 10s只能1次
    public String test() {
        return "test";
    }

    /**
     *
     * BG：重复导入多个容器，有一定几率触发15s响应超时
     * 1. 限频率，如果重复导入直接返回已经在导入中
     * 2. 控制导入的数量，对导入的数量分片+线程池处理
     *
     * @return
     */
    @PostMapping("addContainer")
    @RateLimiter(key = "addContainer:", limitType = LimitType.USER, time = 5, count = 1) // 10s只能1次
    @Transactional(rollbackFor = Exception.class)
    public String addContainerInstanceToCluster(@RequestBody List<String> instances, int userId) {
        // 导入容器节点到集群中
        return addToCluster(instances, "clusterId", userId);
    }



    /**
     *
     * @param instances 导入实例的ID
     * @param userID
     * @return
     */
    public String addToCluster(List<String> instances,String clusterId, int userID) {
        return new MockK8sAPI().loadTo(instances, clusterId).toString();
    }

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


    class CallableRun implements Callable {

        String id;

        public CallableRun(String id, CountDownLatch countDownLatch) {
            this.id = id;
        }

        @Override
        public Object call() throws Exception {

            return null;
        }
    }

    class MockK8sAPI{

        /**
         *
         * @param instances
         * @param clusterId
         * @return
         */
        public List<String> loadTo(List<String> instances, String clusterId) {

            CountDownLatch countDownLatch = new CountDownLatch(instances.size());

            for (String instance : instances) {
                executor.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        try {
                            System.out.println(instance);
                        }finally {
                            countDownLatch.countDown();
                        }
                        return instance;
                    }
                });
            }
            try {
                countDownLatch.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("超时");
                throw new RuntimeException(e);
            }

            return instances;
        }
    }



}
