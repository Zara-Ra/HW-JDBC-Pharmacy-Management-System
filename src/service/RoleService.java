package service;

import data.model.Role;

public interface RoleService {
    boolean signIn();
    boolean signOut();
    Role signUp();
}
