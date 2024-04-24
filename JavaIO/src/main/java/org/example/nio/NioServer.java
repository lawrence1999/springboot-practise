package org.example.nio;

import java.io.IOException;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) {
        try {
            // 创建 ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(7000));
            serverSocketChannel.configureBlocking(false); // 设置为非阻塞模式

            // 打开 Selector 来处理 Channel
            Selector selector = Selector.open();
            // 注册到Selector上，监听连接事件，也可以监听读，写等别的时间
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started...");

            while (true) {
                // 阻塞直到有事件发生
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    //有新的连接就注册到seletor上
                    if (key.isAcceptable()) {
                        // 接受客户端连接请求
                        register(selector, serverSocketChannel);
                    }
                    if (key.isReadable()) {
                        // 读取客户端发送的数据
                        answerWithEcho(key);
                    }
                    iter.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("Connection Accepted: " + client.getLocalAddress());
    }

    private static void answerWithEcho(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        client.read(buffer);
        if (new String(buffer.array()).trim().isEmpty()) {
            client.close();
            System.out.println("Connection closed...");
        } else {
            buffer.flip();
            client.write(buffer);
            buffer.clear();
        }
    }
}
