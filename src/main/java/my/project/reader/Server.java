package my.project.reader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6002)) {

            while (true) {
                Socket socket = serverSocket.accept();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        bufferedWriter.write("echo: " + line);
                        bufferedWriter.flush();
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
