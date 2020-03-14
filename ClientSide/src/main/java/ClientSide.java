
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author giasutinhoc.vn
 */
public class ClientSide {

    public static void main(String[] args) {
        try {
            //Tạo đối tượng Socket đại diện cho cs
            Socket cs = new Socket("localhost", 5020);

            //Tạo đối tượng dùng để nhận dữ liệu từ server
            InputStream is = cs.getInputStream();
            Scanner s = new Scanner(is);
            //Tạo đối tượng dùng để gửi dữ liệu đến server
            OutputStream os = cs.getOutputStream();
            PrintWriter ps = new PrintWriter(os, true);
            //Gửi dữ liệu đến server
            while (true) {
                InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                BufferedReader br = new BufferedReader(isr); // một chuỗi
                String theString = br.readLine();
                ps.println(theString);
                System.out.println("CLient send to server " + theString);
                //Nhận dữ liệu từ server
                String strReceive = s.nextLine();
                System.out.println("Receive from server: " + strReceive);
            }
        } catch (IOException ex) {
        }

    }
}
