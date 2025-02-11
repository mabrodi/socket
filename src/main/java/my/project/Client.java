package my.project;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6001);

        Scanner console = new Scanner(System.in);
        while(console.hasNextLine()) {
            socket.getOutputStream().write(console.nextLine().getBytes());

            byte[] buffer = new byte[100];
            int count = socket.getInputStream().read(buffer);

            System.out.println(new String(buffer, 0, count));
        }
    }
}
