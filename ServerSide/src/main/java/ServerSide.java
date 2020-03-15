import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 *
 * class thực hiện các chức năng bên server
 */
public class ServerSide {

    public final static int SERVER_PORT = 5000; // cổng server
    public final static byte[] BUFFER = new byte[4096];

    /**
     * hàm main thực hiện các chức năng của server
     * @param args
     */
    public static void main(String[] args) {
        
        //tạo danh sách sinh viên
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student person = new Student("mssv" + i, "name" + i, "email" + i, "class" + i, "date" + i, "country" + i, "phone" + i);
            students.add(person);
        }
        
        //tạo socket kiểu UDP
        DatagramSocket dataSocket = null;
        try {
            System.out.println("Binding... " + SERVER_PORT);
            dataSocket = new DatagramSocket(SERVER_PORT);
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");

            while (true) {
                //tạo gói tin nhận
                DatagramPacket packet = new DatagramPacket(BUFFER, BUFFER.length);
                dataSocket.receive(packet); // Chờ nhận gói tin

                // Lấy dữ liệu khỏi gói tin nhận
                String message = new String(packet.getData(), 0, packet.getLength());
                
                //Hiển thị thông tin nhận được từ client
                System.out.println("Received from client: " + message);
                
                //đóng kết nối khi nhận được thông tin từ client
                if(message.equals("over")){
                    dataSocket.close();
                }
                
                Student data = null; //khai báo sinh viên cần lấy dữ liệu
                
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).id.equals(message)) {
                        data = new Student(students.get(i)); // lưu dữ liệu sinh viên tìm được
                        break;
                    }
                }
                
                //tạo nội dung gói tin gửi về client
                if(data == null){
                    //khong tim thay sinh vien
                    message = "Khong ton tai";
                } else {
                    //thong tin sinh vien tim duoc
                     message = "ho ten: " + data.name + " MSSV: " + data.id + " lop: " + data._class + " so dien thoai: " + data.phone + " email: " + data.email + " dia chi: " + data.country;
                }
                
                // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                DatagramPacket dataPacket = new DatagramPacket(message.getBytes(), message.length(),
                        packet.getAddress(), packet.getPort());
                //Gửi về client
                dataSocket.send(dataPacket);
            }
        } catch (IOException e) {
            //in ra lỗi
            System.out.println(e);
        } finally {
            //đóng kết nối
            if (dataSocket != null) {
                dataSocket.close();
            }
        }
    }
}
