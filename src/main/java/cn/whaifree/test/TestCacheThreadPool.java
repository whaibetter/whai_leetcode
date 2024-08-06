package cn.whaifree.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


}
