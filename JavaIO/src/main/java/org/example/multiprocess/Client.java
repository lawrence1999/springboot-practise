package org.example.multiprocess;

/**
 * Description: Socket客户端
 * Date: 2024/4/24
 * Author: lawrence
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 6789;
        // 创建一个socket客户端，指定服务器的主机名和端口号
        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Sending a message to the server
            writer.println("Hello Server");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Reading the response from the server
            String response = reader.readLine();
            System.out.println("Server response: " + response);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

