package org.example.multithread;

/**
 * Description:
 * Date: 2024/4/24
 * Author: lawrence
 */
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class PoolSocketServer {
    private static final int PORT = 6789;
    private static final int N_THREADS = 10;
    private static final ExecutorService pool = Executors.newFixedThreadPool(N_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);

        while (true) {
            try {
                final Socket clientSocket = serverSocket.accept(); // 接受新的客户端连接
                Runnable task = new ClientHandler(clientSocket);
                pool.execute(task); // 将任务提交给线程池
            } catch (IOException e) {
                System.out.println("Accept failed on port " + PORT);
                e.printStackTrace();
                break;
            }
        }

        serverSocket.close();
        pool.shutdown(); // 关闭线程池
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine); // 将接收到的消息发送回客户端
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close(); // 断开连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
