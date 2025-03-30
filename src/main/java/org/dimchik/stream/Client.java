package org.dimchik.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 6002);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
             Scanner console = new Scanner(System.in)) {

            byte[] buffer = new byte[100];
            while (true) {
                if (console.hasNextLine()) {
                    String message = console.nextLine();
                    bufferedOutputStream.write(message.getBytes());
                    bufferedOutputStream.flush();
                }

                int count = bufferedInputStream.read(buffer);
                if (count == -1) {
                    break;
                }

                System.out.println(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            throw new RuntimeException("Client error", e);
        }
    }
}
