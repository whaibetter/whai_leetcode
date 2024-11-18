package cn.whaifree.tmp.project;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.whaifree.leetCode.utils.MapUtils;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/18 14:07
 * @注释
 */
@Slf4j
public class 导入容器集群_请求连接超时导致报错_采用异步中间状态处理方式先异步响应 {

    @Test
    public void test() throws ExecutionException, InterruptedException, TimeoutException {
        List<Integer> instanceId = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            instanceId.add(i);
        }

        Map map = PaaS.requestAdvance(instanceId);
        System.out.println(map);
    }

    class K8sServer{

        @SneakyThrows
        public static Map<String, List<Integer>> addToCluster(List<Integer> instanceId) {
            Map<String, List<Integer>> build =
                    MapUtil.builder("", instanceId)
                            .put("success", new ArrayList<>())
                            .put("fail", new ArrayList<>())
                            .build();

            for (int i = 0; i < instanceId.size(); i++) {
                build.get("success").add(instanceId.get(i));
                log.info("成功添加实例:{}", instanceId.get(i));
//                if (RandomUtil.randomInt(1000) % 10 != 0) {
//                    build.get("success").add(instanceId.get(i));
//                    log.info("成功添加实例:{}", instanceId.get(i));
//                }else {
//                    build.get("fail").add(instanceId.get(i));
//                    log.warn("添加实例失败:{}", instanceId.get(i));
//                }
                Thread.sleep(1000);
            }
            return build;
        }

    }

    public static void main(String[] args) {
        List<Integer> instanceId = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            instanceId.add(i);
        }

        Map map = PaaS.requestAdvance(instanceId);
        System.out.println(map);
    }

    class PaaS{

        public static Map requestOrigin(List<Integer> instanceId) {
            CompletableFuture<Map<String, List<Integer>>> future = CompletableFuture.supplyAsync(() -> K8sServer.addToCluster(instanceId));
            try {
                return future.get(10, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.error("请求连接超时");
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static ExecutorService executor = Executors.newFixedThreadPool(10);

        public static Map requestAdvance(List<Integer> instanceId) {

            CompletableFuture<Map<String, List<Integer>>> future =
                    CompletableFuture.supplyAsync(
                            () -> K8sServer.addToCluster(instanceId)
                            , executor);

            Map<String, List<Integer>> res = future.orTimeout(10, TimeUnit.SECONDS)
                    .handle((result, ex) -> {
                        if (ex != null) {
                            if (ex instanceof TimeoutException) {
                                log.error("请求连接超时");
                                return null; // 返回null表示临时中间状态，为在处理中
                            }
                        }
                        return result;
                    }).join();


            return res;
        }
    }

}
