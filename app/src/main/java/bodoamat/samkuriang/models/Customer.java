package bodoamat.samkuriang.models;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone_number;

    public Customer(String name, String email, String password, String address, String phone_number){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
    }

    public Customer(int id, String name, String email, String password, String address, String phone_number){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
