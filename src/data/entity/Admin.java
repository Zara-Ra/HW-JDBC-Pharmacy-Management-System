package data.entity;

import data.enums.RoleType;

public class Admin extends Role {
    String email;
    RoleType roleType;

    public Admin(String username, String password, String email, RoleType roleType) {
        super(username, password);
        this.email = email;
        this.roleType = roleType;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
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
        return "Admin :\t Username: " + getUsername() + "\t E-mail: " + email;
    }
}
