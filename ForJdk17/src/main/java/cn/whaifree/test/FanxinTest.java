package cn.whaifree.test;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FanxinTest {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                2,
                3
                ,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );


    }

    static class A{

    }
    interface  B{

    }
    interface C{

    }

    /**
     *  T 继承自 A，实现接口 C B
     * @param item
     * @param <T>
     */
    static <T extends A & C & B> void process(List<? extends Integer> item) {

    }
}
