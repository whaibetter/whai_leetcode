package cn.whaifree.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 20:29
 * @注释
 */





public class AioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建一个单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 创建一个异步通道组，使用线程池
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executorService);
        // 打开一个异步服务器套接字通道，并绑定到8080端口
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(8080));

        // 创建一个计数器，用于等待服务器接受连接
        CountDownLatch latch = new CountDownLatch(1);
        // 服务器通道接受连接，并指定一个完成处理器
        serverChannel.accept(null, new AcceptCompletionHandler(latch));

        // 等待服务器接受连接
        latch.await();
        // 关闭通道组
        group.shutdown();
    }

    // 完成处理器，用于处理服务器接受连接的事件
    static class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {
        private final CountDownLatch latch;

        public AcceptCompletionHandler(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, Void attachment) {
              try {
                  Thread.sleep(5000);
                  // 向客户端发送消息
                  result.write(ByteBuffer.wrap("Hello from server".getBytes()), null, new WriteCompletionHandler());
                  // 关闭客户端通道
                result.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
            // 计数器减一
            latch.countDown();
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            // 打印异常信息
            exc.printStackTrace();
        }
    }

    // 完成处理器，用于处理服务器发送消息的事件
    static class WriteCompletionHandler implements CompletionHandler<Integer, Void> {
        @Override
        public void completed(Integer result, Void attachment) {
            // 打印消息发送成功
            System.out.println("Message sent");
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            // 打印异常信息
            exc.printStackTrace();
        }
    }
}



class BioServer {
    public static void main(String[] args) throws IOException {
        // 创建一个服务器套接字，并绑定到8080端口
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started");

        // 无限循环，等待客户端连接
        while (true) {
            // 接受客户端连接
            Socket socket = serverSocket.accept();
            // 创建一个新的线程处理客户端连接
            new Thread(new ClientHandler(socket)).start();
        }
    }

    // 客户端处理器，用于处理客户端连接
    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String inputLine;
                // 循环读取客户端发送的消息
                while ((inputLine = in.readLine()) != null) {
                    // 打印接收到的消息
                    System.out.println("Received: " + inputLine);
                    // 向客户端发送消息
                    out.println("Echo: " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



class NioServer {
    public static void main(String[] args) throws IOException {
        // 打开一个选择器
        Selector selector = Selector.open();
        // 打开一个服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定到8080端口
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 注册选择器，监听接受事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 无限循环，等待事件发生
        while (true) {
            // 选择器选择准备好的通道
            selector.select();
            // 遍历所有准备好的通道
            for (SelectionKey key : selector.selectedKeys()) {
                // 如果是接受事件
                if (key.isAcceptable()) {
                    // 处理接受事件
                    handleAccept(serverSocketChannel, selector);
                // 如果是读取事件
                } else if (key.isReadable()) {
                    // 处理读取事件
                    handleRead(key);
                }
            }
            // 清空选择器中的事件
            selector.selectedKeys().clear();
        }
    }

    // 处理接受事件
    private static void handleAccept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        // 接受客户端连接
        SocketChannel clientChannel = serverSocketChannel.accept();
        // 设置通道为非阻塞模式
        clientChannel.configureBlocking(false);
        // 注册选择器，监听读取事件
        clientChannel.register(selector, SelectionKey.OP_READ);
    }

    // 处理读取事件
    private static void handleRead(SelectionKey key) throws IOException {
        // 获取客户端通道
        SocketChannel clientChannel = (SocketChannel) key.channel();
        // 创建一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取客户端发送的消息
        int read = clientChannel.read(buffer);
        // 如果读取到了消息
        if (read > 0) {
            // 将缓冲区切换到读模式
            buffer.flip();
            // 创建一个字节数组，用于存储读取到的消息
            byte[] data = new byte[read];
            // 将缓冲区中的数据读取到字节数组中
            buffer.get(data);
            // 将字节数组转换为字符串
            String message = new String(data);
            // 打印接收到的消息
            System.out.println("Received: " + message);
            // 向客户端发送消息
            clientChannel.write(ByteBuffer.wrap(("Echo: " + message).getBytes()));
        }
    }
}
