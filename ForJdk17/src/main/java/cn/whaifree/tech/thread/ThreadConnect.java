package cn.whaifree.tech.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 18:51
 * @注释
 */

public class ThreadConnect {
    public static void main(String[] args) throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

        // 创建一个线程用于写入数据
        Thread writerThread = new Thread(() -> {
            try {
                pipedOutputStream.write("Hello, Piped I/O!".getBytes());
                pipedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 创建一个线程用于读取数据
        Thread readerThread = new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = pipedInputStream.read(buffer);
                System.out.println("Received: " + new String(buffer, 0, bytesRead));
                pipedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writerThread.start();
        readerThread.start();
    }
}

