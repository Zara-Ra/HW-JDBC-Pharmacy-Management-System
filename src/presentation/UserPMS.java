package presentation;

import java.sql.SQLException;

public interface UserPMS {
    void firstMenu() throws SQLException;

    boolean signUp() throws SQLException;

    boolean signIn() throws SQLException;
}
