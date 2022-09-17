package data.model;

public class Admin extends Role {
    String email;//TODO Validate .*@pharmacy.com

    public Admin(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }

    public Admin(int ID, String username, String password, String email) {
        super(ID, username, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin :\t Username: "+getUsername()+"\t E-mail: "+email;
    }
}
