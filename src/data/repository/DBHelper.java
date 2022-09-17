package data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String DB_URL = "jdbc:postgresql://localhost/HW7";
    private static final String USER = "postgres";
    private static final String PASS = "zara12";
    private static Connection connection = makeConnection();
    private static Connection makeConnection() {

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection(){
        return connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
