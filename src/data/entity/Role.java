package data.entity;

public abstract class Role {
    private int ID;
    private String username;
    private String password;

    public Role(int ID) {
        this.ID = ID;
    }

    public Role(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public Role(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
