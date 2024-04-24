package org.example.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: Socket服务端
 * Date: 2024/4/24
 * Author: lawrence
 */
public class SocketServer {
    public static void main(String[] args) {
        int port = 6000;
        // 创建一个socket服务端，在构造函数中，listener就已经准备好
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                System.out.println("等待客户端连接...");
                // accept() 方法会一直阻塞到有客户端连接
                Socket socket = serverSocket.accept();

                // 获取socket客户端的输入流
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                // 获取socket客户端的输出流
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // 读取客户端发送的信息
                String clientMessage = reader.readLine();
                System.out.println("Received from client: " + clientMessage);
                // 向客户端返回信息
                writer.println("Echo: " + clientMessage);
                //关闭socket连接
                socket.close();
            }
        }catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
