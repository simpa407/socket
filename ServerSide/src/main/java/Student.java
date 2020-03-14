public class Student {

    String id;
    String birthday;
    String country;
    String name;
    String email;
    String _class;

    /**
     *
     * @param id
     * @param name
     * @param email
     * @param _class
     * @param birthday
     * @param country
     */
    public Student(String id, String name, String email, String _class, String birthday, String country) {
        this.id = id;
        this._class = _class;
        this.birthday = birthday;
        this.country = country;
        this.name = name;
        this.email = email;
    }
}
