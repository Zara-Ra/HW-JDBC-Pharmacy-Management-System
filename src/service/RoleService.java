package service;

import data.model.Role;
import data.model.enums.RoleType;

import java.sql.SQLException;

public interface RoleService {
    Role signIn(String username, String password) throws SQLException;
    boolean signOut(Role role);
    Role signUp(Role newRole) throws SQLException;
}
