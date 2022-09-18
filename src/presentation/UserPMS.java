package presentation;

import java.sql.SQLException;

public interface UserPMS {
    void signUp() throws SQLException;
    boolean signIn() throws SQLException;
}
