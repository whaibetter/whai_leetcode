package cn.whaifree.springdemo.controller.cacheComparator;


import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.ForkJoinPool;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/24 22:27
 * @注释
 */
@RestController
public class CacheComparatorDemo {

    private com.google.common.cache.LoadingCache<Object, Object> guavaCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofDays(1))
            .initialCapacity(10)
            .build(new com.google.common.cache.CacheLoader<Object, Object>() {
                @Override
                public Object load(Object key) throws Exception {
                    return null;
                }
            });


    private com.github.benmanes.caffeine.cache.LoadingCache<Object, Object> caffeineCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofDays(1))
            .build(new com.github.benmanes.caffeine.cache.CacheLoader<Object, Object>() {
                @Override
                public @Nullable Object load(Object key) throws Exception {
                    return null;
                }
            });



    @PostMapping("/guavaPut")
    public String guava(String key, String value) {
        guavaCache.put(key, value);
        return "guava put success";
    }
    @GetMapping("/guavaGet")
    public String guavaGet(String key) {
        return (String) guavaCache.getIfPresent(key);
    }

    @PostMapping("/caffeinePut")
    public String caffeine(String key, String value) {
        caffeineCache.put(key, value);
        return "caffeine put success";
    }
    @GetMapping("/caffeineGet")
    public String caffeineGet(String key) {
        return (String) caffeineCache.getIfPresent(key);
    }

    /**
     * AsyncLoadingCache是继承自LoadingCache类的，
     * 异步加载使用Executor去调用方法并返回一个CompletableFuture，
     * 相比于同步填充模式，在load数据时，
     * 使用异步线程来执行load方法，
     * 默认使用ForkJoinPool.commonPool()来执行异步线程，
     * 我们可以通过Caffeine.executor(Executor) 方法来替换线程池。
     */
    private AsyncLoadingCache<Object, Object> cache = Caffeine.newBuilder()
            .recordStats()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofDays(1)) // 缓存有效期1天,write后1天过期
            .expireAfterAccess(Duration.ofDays(1)) // 缓存有效期1天,访问后1天过期
            .executor(ForkJoinPool.commonPool()) // 默认使用ForkJoinPool.commonPool()来执行异步线程
            .buildAsync(
                    new com.github.benmanes.caffeine.cache.CacheLoader<Object, Object>() {
                        @Override
                        public @Nullable Object load(Object key) throws Exception {
                            return null;
                        }
                    }
            );


    /**
     * <a href="https://blog.csdn.net/zhangyunfeihhhh/article/details/108105928">...</a>
     *
     * guava提供了两种回收策略
     * - 但Guava室友在获取值的时候进行回收，如果一直没get，会导致缓存一直在占用内存
     *        并不会导致数据直接失效，而是在get时，去load新的值
     *     - 一旦一个kv写入缓存，设置过期时间无法去除（没有get就不会对其清除，但如果过期了，去load新的值如果不是null，就会一直占用内存）
     *     - 所以最好设置MaxSize，设置缓存大小，不然可能内存泄露
     *
     * caffine和guava相同的两种过期策略也是惰性删除（在get时去进行过期判断过期），和guava基本一致
     *
     *
     * caffeine chache通过W-TinyLFU算法进行数据驱逐：
     *
     Caffeine 使用 W-TinyLFU 作为其默认的频率计数器，以支持高效的缓存淘汰策略。以下是一些关键点：
     频率计数器: Caffeine 使用一个小型的高频计数器数组来记录每个键的访问频率。
     权重因子: 通过权重因子调整频率计数，使得频繁访问的键更容易保留。
     淘汰策略: 当缓存达到容量上限时，根据频率计数和权重因子决定哪些键应该被淘汰。
     *
     *
     * 通过对guava cache 和caffeine 从性能到算法及使用的对比中，可以发现Caffeine基本是在Guava的基础上进行优化而来的，提供的功能基本一致，但是通过对算法和部分逻辑的优化，完成了对性能极大的提升，而且我们可以发现，两者切换几乎没有成本，毕竟caffeine就是以替换guava cache为目的而来的。
     *
     * @param key
     * @return
     */

//    @Override
//    default CompletableFuture<V> get(K key, Function<? super K, ? extends V> mappingFunction) {
//        requireNonNull(mappingFunction);
//        return get(key, (k1, executor) -> CompletableFuture.supplyAsync(
//                () -> mappingFunction.apply(key), executor));
//    }

    @RequestMapping("/asyncGet")
    public String asyncGet(String key) {

        return "";
    }

}
