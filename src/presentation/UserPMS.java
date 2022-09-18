package presentation;

import java.sql.SQLException;

public interface UserPMS {
    void firstMenu() throws SQLException;
    void signUp() throws SQLException;
    boolean signIn() throws SQLException;
}
