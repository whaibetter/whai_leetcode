```java
    public List<Instance> findInstanceList(List<Instance> instanceList, TsfBaseEntity entity) {
    if (CollectionUtils.isEmpty(instanceList)) {
        return new ArrayList<>();
    }
    List<Instance> instanceRunList = new ArrayList<>();
    //虚机节点
    long start, end;
    start = System.currentTimeMillis();
    List<Instance> instanceCVMList = instanceList.stream().filter(item ->
            ClusterConstant.CLUSTER_TYPE.CVM.equals(item.getClusterType())).collect(Collectors.toList());
    List<String> instanceIdList = instanceCVMList.stream().map(Instance::getInstanceId).collect(Collectors.toList());
    List<InstanceAgent> instanceAgentList = instanceAgentJpaRepository.findByInstanceIdIn(instanceIdList);
    Map<String, InstanceAgent> instanceAgentMap = TsfMapUtil.list2HashMap(instanceAgentList, "getInstanceId");
    if (!CollectionUtils.isEmpty(instanceAgentMap)) {
        for (Instance ins : instanceCVMList) {
            InstanceAgent instanceAgent = instanceAgentMap.get(ins.getInstanceId());
            if (instanceAgent != null) {
                Long currentTimestamp = System.currentTimeMillis();
                Long heartbeatTimestamp = instanceAgent.getUpdateTime() != null ? instanceAgent.getUpdateTime().getTime() : 0L;
                if (heartbeatTimestamp + 18 * 60 * 1000 > currentTimestamp) {
                    instanceRunList.add(ins);
                }
            }
        }
    }
    end = System.currentTimeMillis();
    logger.info("call cvm clusterInstances cost {}ms", (end - start));

    //容器节点
    start = System.currentTimeMillis();
    List<Instance> instanceCCSList = instanceList.stream().filter(item ->
            ClusterConstant.CLUSTER_TYPE.CCS.equals(item.getClusterType())).collect(Collectors.toList());
    Map<String, List<Instance>> instanceCcsMap = instanceCCSList.stream().collect(Collectors.groupingBy(Instance::getClusterId));
    instanceCcsMap.forEach((clusterId,instanceCcsList) -> {
        Cluster cluster = new Cluster();
        cluster.transferBase(entity);
        cluster.setClusterId(clusterId);
        List<Instance> ccsRunInstanceList = commonContainerInstanceService.findRunInstanceList(cluster, instanceCcsList);
        instanceRunList.addAll(ccsRunInstanceList);
    });
    end = System.currentTimeMillis();
    logger.info("call ccs clusterInstances cost {}ms", (end - start));
    return instanceRunList;
}

```



117789438


CountDownLatch 的使用

```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int numberOfTasks = 3; // 假设有3个任务需要完成
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(() -> {
                System.out.println("任务 " + Thread.currentThread().getName() + " 开始执行...");
                // 模拟任务执行需要一些时间
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务 " + Thread.currentThread().getName() + " 执行完成");
                latch.countDown(); // 任务完成，计数减1
            }).start();
        }

        System.out.println("主线程等待所有任务完成...");
        latch.await(); // 主线程等待所有任务完成
        System.out.println("所有任务已完成，主线程继续执行...");
    }
}

```


**使用AtomicInteger、CountDownLatch、线程池的并发方式优化查询集群数量接口和实例数量资源接口的性能，解决超时问题。在查询实例或集群数量过多时，查询DB，还需要查询容器平台、会导致查询时延达到15s秒**
使用redis封装，避免短时间缓存击穿

线程工厂
```java
package com.tencent.tsf.resource.common.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public CustomThreadFactory(String prefix) {
		 SecurityManager s = System.getSecurityManager();
         group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
         namePrefix = prefix + "-thread-";
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		if (t.isDaemon())
			t.setDaemon(false);
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}

}
```

线程池

```java
private ExecutorService overviewResourceUsageExecutor =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, Runtime.getRuntime().availableProcessors() * 2,0, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(2048),
                    new CustomThreadFactory("overviewResourceUsageThreadPool"),
                    new ThreadPoolExecutor.AbortPolicy());

```
固定线程数量的线程池，我是8核的cpu，此时能用8个cpu处理16个线程，当线程满了时就进入阻塞队列ArrayBlockingQueue
1. 核心线程数（corePoolSize）：Runtime.getRuntime().availableProcessors() * 2。这意味着线程池会创建一个核心线程数，等于当前系统可用处理器的两倍。这样的设置通常是为了确保线程池能够充分利用系统资源，同时避免线程过多导致上下文切换开销过大。 
2. 最大线程数（maximumPoolSize）：同样设置为Runtime.getRuntime().availableProcessors() * 2。这意味着线程池允许存在的最大线程数也是系统可用处理器的两倍。由于核心线程数和最大线程数相等，这个线程池实际上是一个固定大小的线程池，它不会根据任务的多少动态地增加或减少线程数量。 
3. 线程空闲时间（keepAliveTime）：设置为0秒。这意味着线程一旦空闲下来就会被立即回收，不会等待其他任务的到来。这个设置适用于任务持续不断地到来的场景，可以节省资源。 
4. 时间单位（unit）：设置为TimeUnit.SECONDS，表示线程空闲时间的单位是秒。 
5. 工作队列（workQueue）：使用ArrayBlockingQueue作为工作队列，容量为2048。这意味着当线程池中的线程都在忙碌时，新来的任务会进入这个队列等待执行。队列的容量较大，可以容纳较多的待执行任务，有助于减少线程创建和销毁的频率。
6. 线程工厂（threadFactory）：使用CustomThreadFactory自定义线程工厂来创建线程。这样可以自定义线程的名称、优先级等属性，便于管理和调试。 
7. 拒绝策略（RejectedExecutionHandler）：使用ThreadPoolExecutor.AbortPolicy()作为拒绝策略。当线程池和工作队列都满时，新提交的任务会导致抛出RejectedExecutionException异常。这种策略比较直接，可以让调用者感知到任务无法执行的情况并进行相应处理。


接口超时，超过15s了
【此接口内部有一个循环查询逻辑，循环里面需要查询DB，还需要查询容器平台，故性能会随着用户集群的增多而下降】

两个接口超时的原因基本是一样的：

1、DescribeGroupResourceUsage， 首先会查询出所有的集群，包括虚拟机集群和容器集群，之后再将所有的集群进行遍历：

如果是容器集群，先根据集群ID在DB里面查询出关联的所有group列表，然后就会调用apiserver获取所有的pod信息，然后累计计算部署组中的pod个数和健康状况。

如果是虚拟机集群，先根据集群ID在DB里面查询出关联的所有group列表，然后再去调用masterapi的相关接口获取group的实例信息，最后再统计。

最终再将所有集群计算出来的数据进行累加，这里的耗时体现在，每个集群的查询耗时是串行累加的，不是并行，所以集群数量越多，累加时间肯定越大。



2、DescribeInstanceResourceUsage， 首先会查询出所有的集群，包括虚拟机集群和容器集群，之后再将所有的集群进行遍历：

如果是容器集群，先根据集群ID在DB里面查询出关联的所有group列表，然后就会调用apiserver获取所有的pod信息，然后累计计算部署组中的pod个数。

如果是虚拟机集群，根据clusterID去调用masterapi的相关接口获取集群下面的instance列表，然后再统计各个维度的instance个数。

然后再将所有集群计算出来的数据进行累加，这里的耗时体现在，每个集群的查询耗时是串行累加的，不是并行，所以集群数量越多，累加时间肯定越大。

最后还需要统计所有容器集群的健康状态，此时需要调用TKE的接口，去批量查询容器集群健康状态。



- 优化策略：将串行查询优化为并行查询

- 问题： 在查询实例或集群数量过多时，查询DB，还需要查询容器平台、会导致查询时延达到15s秒

- 原因：查询出所有的集群，包括虚拟机集群和容器集群，之后再将所有的集群进行遍历；如果是容器集群，先根据集群ID在DB里面查询出关联的所有group列表，然后就会调用apiserver获取所有的pod信息，然后累计计算部署组中的pod个数和健康状况；
如果是虚拟机集群，先根据集群ID在DB里面查询出关联的所有group列表，然后再去调用masterapi的相关接口获取group的实例信息，最后再统计。
最终再将所有集群计算出来的数据进行累加，这里的耗时体现在，每个集群的查询耗时是串行累加的，不是并行，所以集群数量越多，累加时间肯定越大。 
- 解决：引入线程池来并发处理查询请求，使用AtomicInteger来安全地统计实例数量，并利用CountDownLatch确保所有并发任务完成后才返回结果，从而提高了接口的并发处理能力并解决了超时问题。
