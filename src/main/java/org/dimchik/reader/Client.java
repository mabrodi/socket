package org.dimchik.reader;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6002);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner console = new Scanner(System.in)) {

            while (console.hasNextLine()) {
                String message = console.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.flush();

                String response = bufferedReader.readLine();
                if (response == null) {
                    break;
                }
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException("Client error", e);
        }
    }
}
