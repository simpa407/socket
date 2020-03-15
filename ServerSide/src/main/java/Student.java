
/**
 *class đối tượng sinh viên
 */
public class Student {

    String id; //mã sinh viên
    String birthday; //ngày sinh
    String country; // địa chỉ
    String name; // họ và tên sinh viên
    String email; // email
    String _class; // lớp
    String phone;// điện thoại

    /**
     *
     * @param id mã số sinh viên
     * @param name họ và tên
     * @param email email
     * @param _class lớp
     * @param birthday ngày sinh
     * @param country địa chỉ
     * @param phone điện thoại
     */
    public Student(String id, String name, String email, String _class, String birthday, String country, String phone) {
        this.id = id;
        this._class = _class;
        this.birthday = birthday;
        this.country = country;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // hàm khởi tạo copy
    Student(Student get) {
        this._class = get._class;
        this.birthday = get.birthday;
        this.id = get.id;
        this.email = get.email;
        this.country = get.country;
        this.name = get.name;
        this.phone = get.phone;
    }
}
