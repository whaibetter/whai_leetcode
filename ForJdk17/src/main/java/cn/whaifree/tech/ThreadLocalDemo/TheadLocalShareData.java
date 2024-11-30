package cn.whaifree.tech.ThreadLocalDemo;

import org.junit.Test;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/28 10:16
 * @注释
 */
public class TheadLocalShareData{


    public class GlobalThreadLocal {
        private static final ThreadLocal<String> threadLocalData = ThreadLocal.withInitial(() -> "defaultValue");

        public static String getData() {
            return threadLocalData.get();
        }

        public static void setData(String data) {
            threadLocalData.set(data);
        }
    }


    @Test
    public void test1() throws InterruptedException {
        GlobalThreadLocal.threadLocalData.set("hello world");
        System.out.println(GlobalThreadLocal.threadLocalData.get());
        Thread.sleep(10_0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(GlobalThreadLocal.threadLocalData.get());
            }
        }).start();
    }


    /**
     * threadlocal的原理，如何实现不同包之间的线程之间共享数据
     */
    @Test
    public void test() throws InterruptedException {
        ThreadLocal<HashMap> threadLocal = ThreadLocal.withInitial(new Supplier<HashMap<Object,Object>>() {
            @Override
            public HashMap<Object, Object> get() {
                return new HashMap<>();
            }
        });

//        threadLocal.get().put()

        Thread.sleep(10_0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        }).start();

    }



}
