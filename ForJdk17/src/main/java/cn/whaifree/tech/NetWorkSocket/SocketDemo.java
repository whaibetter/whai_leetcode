package cn.whaifree.tech.NetWorkSocket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 21:54
 * @注释
 */
public class SocketDemo {


}

class BIOSocketClient{
    /**
     * 主方法，用于启动Socket客户端，连接到指定的服务器，并进行数据传输。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        String serverName = "localhost"; // 服务器地址
        int port = 12130; // 服务器端口
        try(Scanner scanner = new Scanner(System.in)) // 创建扫描器用于读取用户输入
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new java.net.Socket(serverName, port); // 创建Socket连接
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream(); // 获取输出流
            DataOutputStream out = new DataOutputStream(outToServer); // 包装输出流以便发送UTF-8编码的字符串
            while (scanner.hasNext()) { // 循环读取用户输入
                String next = scanner.next();
                out.writeUTF("从" + client.getLocalSocketAddress() + "发送消息: " + next); // 发送消息到服务器
                if (next.equals("end")) { // 如果用户输入end，则结束循环
                    break;
                }
            }

            InputStream inFromServer = client.getInputStream(); // 获取输入流
            DataInputStream in = new DataInputStream(inFromServer); // 包装输入流以便读取UTF-8编码的字符串
            System.out.println("服务器响应： " + in.readUTF()); // 读取并打印服务器响应
            client.close(); // 关闭Socket连接
        }catch(IOException e)
        {
            e.printStackTrace(); // 打印异常信息
        }
    }
}

class BIOSocketServer{
    private static ServerSocket serverSocket; // 服务器Socket

    /**
     * 静态初始化块，用于创建并配置服务器Socket。
     */
    static {
        try
        {
            serverSocket = new ServerSocket(12130); // 创建服务器Socket并绑定端口
            serverSocket.setSoTimeout(10000); // 设置Socket超时时间为10秒
        }catch(IOException e)
        {
            e.printStackTrace(); // 打印异常信息
        }
    }

    /**
     * 主方法，用于启动Socket服务器，监听客户端连接，并处理数据传输。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        while(true) // 无限循环等待客户端连接
        {
            try
            {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                try (Socket server = serverSocket.accept()) { // 接受客户端连接
                    System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                    DataInputStream in = new DataInputStream(server.getInputStream()); // 获取输入流
                    String message = in.readUTF(); // 读取客户端消息
                    while (!"end".equals(message)) { // 循环读取直到客户端发送end
                        System.out.println("客户端发送的消息: " + message);
                        message = in.readUTF();
                    }
                    DataOutputStream out = new DataOutputStream(server.getOutputStream()); // 获取输出流
                    out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!"); // 发送响应给客户端
                }
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!"); // 打印超时信息
                break; // 超时则退出循环
            }catch(IOException e)
            {
                e.printStackTrace(); // 打印异常信息
                break; // 异常则退出循环
            }
        }
    }
}

class NIOServer{


    /**
     * 主函数，用于启动服务器并监听客户端连接及处理消息
     * @param args 命令行参数
     * @throws IOException 如果发生IO异常
     */
    public static void main(String[] args) throws IOException {
        // 定义服务器监听的端口号
        int port = 12140;

        // 创建服务端服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口号到服务器套接字
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置通道为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 打开一个选择器
        Selector selector = Selector.open();
        // 将服务器套接字通道注册到选择器，关注接受事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 持续监听和处理连接及读取事件
        while (true) {
            // 监听并处理就绪事件
            selector.select();

            // 获取发生事件的键集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            // 遍历集合中的每一个事件
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                // 获取当前事件
                SelectionKey key = iterator.next();
                // 移除已处理的事件
                iterator.remove();

                // 如果有新的连接请求
                if (key.isAcceptable()) {
                    // 获取服务器套接字通道
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 接受新的客户端连接
                    SocketChannel socketChannel = server.accept();
                    // 设置通道为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将客户端通道注册到选择器，关注读取事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 输出新连接的客户端地址
                    System.out.println("新客户端连接：" + socketChannel.getRemoteAddress());
                }

                // 如果有可读取的事件
                if (key.isReadable()) {
                    // 获取客户端套接字通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    // 创建缓冲区以读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int read;
                    StringBuilder message = new StringBuilder();
                    // 读取客户端发送的数据
                    while ((read = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        message.append(new String(bytes));
                        buffer.clear();
                    }

                    // 如果读取到-1，表示客户端已关闭连接
                    if (read == -1) {
                        socketChannel.close();
                        continue;
                    }

                    // 如果接收到的消息为"end"，关闭连接
                    if (message.toString().equals("end")) {
                        socketChannel.close();
                        continue;
                    }

                    // 输出客户端发送的消息
                    System.out.println("客户端发送的消息: " + message);
                    // 准备响应数据并写入客户端通道
                    ByteBuffer response = ByteBuffer.wrap(("服务器响应： " + message).getBytes());
                    socketChannel.write(response);
                }
            }
        }
    }

}

class AIOServer{
    // 服务器端口常量
    private static final int PORT = 12140;

    /**
     * 主函数，用于启动AIO服务器
     * @param args 命令行参数
     * @throws InterruptedException 主线程睡眠时可能抛出的异常
     * @throws IOException 网络操作可能抛出的异常
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // 创建并绑定异步服务器Socket通道
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()
                .bind(new java.net.InetSocketAddress(PORT));
        System.out.println("服务器启动在端口 " + PORT);

        // 开始监听客户端连接
        server.accept(null, new AcceptHandler(server));

        // 做其他事情

        // 由于AIO是非阻塞的，这里需要让主线程保持运行状态
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 处理客户端连接请求的完成事件
     */
    static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        private final AsynchronousServerSocketChannel server;

        /**
         * 构造函数，初始化服务器通道
         * @param server 服务器通道
         */
        public AcceptHandler(AsynchronousServerSocketChannel server) {
            this.server = server;
        }

        /**
         * 当连接成功完成时的处理逻辑
         * @param client 成功连接的客户端通道
         * @param attachment 附加数据，通常用于传递状态或进行进一步处理
         */
        @Override
        public void completed(AsynchronousSocketChannel client, Object attachment) {
            System.out.println("客户端连接成功");

            // 读取客户端发送的数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            CompletionHandler<Integer, ByteBuffer> handler = new CompletionHandler<>() {

                /**
                 * 当读取操作成功完成时的处理逻辑
                 * @param result 读取到的字节数
                 * @param buffer 用于读取的缓冲区
                 */
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    buffer.flip(); // 在缓冲区（Buffer）中切换读写模式
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    String received = new String(data);
                    System.out.println("从客户端接收: " + received);

                    buffer.clear();

                    if ("end".equals(received.trim())) {
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ByteBuffer response = ByteBuffer.wrap(("服务器响应: " + received).getBytes());
                        client.write(response, null, new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer result, Object attachment) {
                                System.out.println("数据发送成功");
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                exc.printStackTrace();
                            }
                        });
                    }

                    client.read(buffer, buffer, this);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    // 处理读取失败情况，此处未定义具体行为
                }
            };




            client.read(buffer, buffer, handler);

            server.accept(null, this);
        }

        /**
         * 当连接请求失败时的处理逻辑
         * @param exc 异常对象
         * @param attachment 附加数据
         */
        @Override
        public void failed(Throwable exc, Object attachment) {
            exc.printStackTrace();
        }
    }

}

