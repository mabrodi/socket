package org.dimchik.stream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6002)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream())) {

                    byte[] buffer = new byte[100];

                    int count;
                    while ((count = bufferedInputStream.read(buffer)) != -1) {
                        String text = "echo: " + new String(buffer, 0, count);
                        bufferedOutputStream.write(text.getBytes());
                        bufferedOutputStream.flush();
                    }

                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Server error", e);
        }
    }
}
