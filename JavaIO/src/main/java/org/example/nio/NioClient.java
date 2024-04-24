package org.example.nio;

/**
 * Description:
 * Date: 2024/4/24
 * Author: lawrence
 */
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class NioClient {
    public static void main(String[] args) {
        try {
            // 打开通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 7000));
            socketChannel.configureBlocking(false); // 非阻塞模式

            // 缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(256);
            buffer.put("Hello, NIO Server!".getBytes(StandardCharsets.UTF_8));
            buffer.flip();

            // 发送数据
            socketChannel.write(buffer);
            buffer.clear();

            // 接收数据
            while (socketChannel.read(buffer) > 0) {
                buffer.flip();
                System.out.println("Received: " + StandardCharsets.UTF_8.decode(buffer).toString());
                buffer.clear();
            }

            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
