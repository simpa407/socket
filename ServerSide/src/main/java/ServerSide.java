
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerSide {

    public static void main(String[] args) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student person = new Student("mssv" + i, "name" + i, "email" + i, "class" + i, "date" + i, "country" + i);
            students.add(person);
        }
        try {
            System.out.println("Binding to port 5001, please wait  ...");
            ServerSocket serverSocket = new ServerSocket(5020);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    try (Socket socket = serverSocket.accept()) {
                        System.out.println("Client accepted: " + socket);

                        InputStream is = socket.getInputStream();
                        Scanner s = new Scanner(is);

                        OutputStream os = socket.getOutputStream();
                        PrintWriter pw = new PrintWriter(os, true);

                        while (true) {
                            String strReceive = s.nextLine();
                            System.out.println("Receive from client: " + strReceive);
                            while (!strReceive.equals("Over")) {
                                for (int i = 0; i < students.size(); i++) {
                                    if (students.get(i).id == null ? strReceive == null : students.get(i).id.equals(strReceive)) {
                                        pw.println(students.get(i).id + students.get(i).name);
                                        break;
                                    }
                                }
                            }
                            System.out.println("Closing connection");
                            serverSocket.close();
                            is.close();
                        }
                    }
                } catch (IOException e) {
                    System.err.println(" Connection Error: " + e);
                }
            }
        } catch (IOException e) {
        }
    }
}
