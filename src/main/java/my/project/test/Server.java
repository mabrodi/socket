package my.project.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6001);
        Socket socket = serverSocket.accept();

        while (true) {
            byte[] buffer = new byte[100];

            int count = socket.getInputStream().read(buffer);
            String text = "echo: " + new String(buffer, 0, count);

            socket.getOutputStream().write(text.getBytes());
        }
    }
}
