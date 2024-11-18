package cn.whaifree.tmp.project;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/17 14:24
 * @注释
 */
interface TimeInterface {
    Instance k8sDescribe(Integer instanceId) throws InterruptedException;
}

@Slf4j
public class K8s查询串行 {

    @Test
    public void K8s查询串行改并行() throws Exception {

        long start = System.currentTimeMillis();
        System.out.println("=================开始时间：" + start);
        List<Integer> instanceIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Instance> instances = K8s查询串行.describeInstance(instanceIds);
        System.out.println(instances);
        long end = System.currentTimeMillis();
        System.out.println("=================结束时间：" + end);
        log.info("耗时：" + (end - start));

//        List<Instance> instances = o.describeInstance(instanceIds);
//        System.out.println(instances);

    }

    static TimeInterface k8sProxy = null;
    static {
        Object o= Proxy.newProxyInstance(TimeInterface.class.getClassLoader(), new Class[]{TimeInterface.class},
                (proxy, method, args) -> {
                    long start = System.currentTimeMillis();
                    Object invoke = method.invoke(new K8sServer(), args);
                    long end = System.currentTimeMillis();
                    log.info("当前时间{},耗时:{}", new Date(end), end - start);
                    return invoke;
                });
        k8sProxy = (TimeInterface) o;
    }

    public static List<Instance> describeInstance(List<Integer> instanceIds) throws InterruptedException {
        List<Instance> instances = new ArrayList<>();
        for (Integer instanceId : instanceIds) {
            Instance instance = k8sProxy.k8sDescribe(instanceId);
            System.out.println(StrUtil.format("instanceId:{},instanceInfo:{}", instanceId, instance.getInstanceInfo()));
            instances.add(instance);
        }
        return instances;
    }

}



class K8sServer implements TimeInterface{
    static Map<Integer, Instance> map = new HashMap<>();
    static {
        for (int i = 0; i < 100; i++) {
            map.put(i, new Instance(i,
                    StrUtil.format(
                            "{instanceId:{},memoryUse:{},cpuUse:{}}", i, "80%", "90%")));
        }

    }
    public Instance k8sDescribe(Integer instanceId) throws InterruptedException {
        Thread.sleep(RandomUtil.randomInt(500, 1100));
        return map.get(instanceId);
    }
}

@Data
@AllArgsConstructor
class Instance {
    Integer instaceId;
    String instanceInfo;
}



@Slf4j
class K8s查询并行 {


    public static void main(String[] args) throws Exception {


        long start = System.currentTimeMillis();
        System.out.println("=================开始时间：" + start);
        List<Integer> instanceIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Instance> instances = K8s查询并行.describeInstance2(instanceIds);
        System.out.println(instances);
        long end = System.currentTimeMillis();
        System.out.println("=================结束时间：" + end);
        log.info("耗时：" + (end - start));

//        List<Instance> instances = o.describeInstance(instanceIds);
//        System.out.println(instances);

    }

    static TimeInterface k8sProxy = null;
    static ExecutorService executor = null;
    static {
        Object o= Proxy.newProxyInstance(TimeInterface.class.getClassLoader(), new Class[]{TimeInterface.class},
                (proxy, method, args) -> {
                    long start = System.currentTimeMillis();
                    Object invoke = method.invoke(new K8sServer(), args);
                    long end = System.currentTimeMillis();
                    log.info("当前时间{},耗时:{}", new Date(end), end - start);
                    return invoke;
                });
        k8sProxy = (TimeInterface) o;
        executor = Executors.newFixedThreadPool(10);
    }

    public static List<Instance> describeInstance(List<Integer> instanceIds) throws InterruptedException, ExecutionException, TimeoutException {
        List<Instance> instances = new ArrayList<>();

        List<Future<Instance>> futures = new ArrayList<>();
        for (Integer instanceId : instanceIds) {
            Future<Instance> submit = executor.submit(() -> k8sProxy.k8sDescribe(instanceId));
            futures.add(submit);
        }
        for (Future<Instance> future : futures) {
            instances.add(future.get(10, TimeUnit.SECONDS));
        }
        return instances;
    }

    public static List<Instance> describeInstance2(List<Integer> instanceIds) throws InterruptedException, ExecutionException {
        List<Instance> instances = new ArrayList<>();

        int size = instanceIds.size();
        CountDownLatch latch = new CountDownLatch(size);
        for (Integer instanceId : instanceIds) {
            executor.submit(new MyCallable(latch, instanceId, instances));
        }
        latch.await(10, TimeUnit.SECONDS);
        return instances;
    }

    static class MyCallable implements Callable<Instance> {

        CountDownLatch latch;
        Integer instanceId;
        List<Instance> instances;


        public MyCallable(CountDownLatch latch, Integer instanceId, List<Instance> instances) {
            this.latch = latch;
            this.instanceId = instanceId;
            this.instances = instances;
        }

        @Override
        public Instance call() throws Exception {
            try {
                Instance instance = k8sProxy.k8sDescribe(instanceId);
                instances.add(instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                latch.countDown();
            }
        }
    }


}
