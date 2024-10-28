package cn.whaifree.tech.IODemo;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 20:21
 * @注释
 */
public class IODemo {

    @Test
    public void BIOServer() throws IOException {


    }



}
class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("客户端连接成功！" + socket.getRemoteSocketAddress());
        System.out.println("my地址：" + socket.getLocalAddress() + ":" + socket.getLocalPort());

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writeToServer = new PrintWriter(outputStream, true); // 最后消息是通过PrintWriter发送的。
        // PrintWriter提供了多种方法来方便地写入字符、字符串、数字等数据。

        InputStream inputStream = socket.getInputStream();// 服务端输入流
        BufferedReader getFromServerInput = new BufferedReader(new InputStreamReader(inputStream));

        Scanner scanner = new Scanner(System.in);


        new Thread(() -> {
            while (true) {
                try {
                    String msg = getFromServerInput.readLine();// 阻塞等待服务器消息
                    System.out.println("收到来自服务器的消息：" + msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        String userInput;
        while (true) {
            System.out.print("请输入消息 (输入 'exit' 结束): ");
            userInput = scanner.nextLine();
            writeToServer.println(userInput); // 发送消息到服务器
            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("已断开与服务器的连接");
                break;
            }
        }

        inputStream.close();
        outputStream.close();
        getFromServerInput.close();
        writeToServer.close();
        socket.close();
    }
}

class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功！等待客户端连接...");

        Socket socket = serverSocket.accept(); // 阻塞等待连接, 连接建立后可以获取网络流 输入 和 输出
        System.out.println("客户端连接成功！");

        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len = inputStream.read(bytes);
//        System.out.println("接收到客户端消息：" + new String(bytes, 0, len));

        // 接受消息
        BufferedReader getFromClientInput = new BufferedReader(new InputStreamReader(inputStream));
        // 写客户端
        PrintWriter writeToClient = new PrintWriter(socket.getOutputStream(), true);
        writeToClient.println("欢迎来到服务端！");

        while (true) {
            String getMsg = getFromClientInput.readLine();// 阻塞等待客户端消息
            if (getMsg.equals("exit")) {
                break;
            }
            System.out.println("收到消息：" + getMsg);
            writeToClient.println("收到消息：" + getMsg);
        }


        inputStream.close();
        writeToClient.close();
        socket.close();
        serverSocket.close();
    }
}


class ResVo<T> {
    private T data;
    private Integer code;
    private String message;

    public ResVo(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
