package service;

import data.model.Role;

import java.sql.SQLException;

public interface RoleService {
    boolean signIn(String username, String password);
    boolean signOut(String Role);
    Role signUp(Role newRole) throws SQLException;
}
