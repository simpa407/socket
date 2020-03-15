import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
/**
 *class thực hiện các chức năng bên phía client
 */
public class ClientSide {
 
    public final static String SERVER_IP = "127.0.0.1"; //địa chỉ IP của server
    public final static int SERVER_PORT = 5000; // cổng server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
 
    public static void main(String[] args) {
        
        //tạo socket kiểu UDP
        DatagramSocket dataSocket = null;
        try {
            dataSocket = new DatagramSocket();
            System.out.println("Client started ");
 
            InetAddress server = InetAddress.getByName(SERVER_IP);
            while (true) {
                
                //Nhập thông tin mã sinh viên cần tìm kiếm từ bàn phím
                System.out.println("Nhap MSSV: ");
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(input);
                //tạo biến lưu mã số vừa nhập
                String mssv = br.readLine();
                
                //chuyển mã số sang kiểu byte
                byte[] data = mssv.getBytes();
 
                // Tạo gói tin gửi đến server
                DatagramPacket packet = new DatagramPacket(data, data.length, server, SERVER_PORT);
                //gửi gói tin sang server
                dataSocket.send(packet);
                
                //nhập Over để đóng kết nối với server
                if(mssv.equals("over")){
                    dataSocket.close();
                }
 
                // tạo gói tin nhận
                DatagramPacket packetReceive = new DatagramPacket(BUFFER, BUFFER.length);
                //nhận gói tin gửi về từ server
                dataSocket.receive(packetReceive);
 
                // hiển thị thông tin nhận được
                System.out.println("Thong tin sinh vien: " + new String(packetReceive.getData(), 0, packetReceive.getLength()));
            }
        } catch (IOException e) {
            //hiển thị lỗi
            System.err.println(e);
        } finally {
            //đóng kết nối
            if (dataSocket != null) {
                dataSocket.close();
            }
        }
    }
}