package data.entity;

public class Patient extends Role {
    String phone;
    String address;
    String email;

    public Patient(int ID, String username, String password, String phone, String address, String email) {
        super(ID, username, password);
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Patient(String username, String password, String email, String phone, String address) {
        super(username, password);
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Patient(int patientID) {
        super(patientID);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient :\t Username: " + getUsername() + "\t Phone Number: " + phone + "\t Address: " + address + "\t E-mail: " + email;
    }
}
