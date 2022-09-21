package data.repository;

import data.entity.Admin;
import data.entity.Patient;
import data.entity.Role;
import data.enums.RoleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
    private RoleDao() {
    }

    private static final RoleDao instance = new RoleDao();

    public static RoleDao getInstance() {
        return instance;
    }

    private final Connection connection = DBHelper.getConnection();

    public boolean signUp(Role newRole) throws SQLException {
        String sql = "INSERT INTO roles(username,password,email,phone,address,role_type) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newRole.getUsername());
        preparedStatement.setString(2, newRole.getPassword());
        if (newRole instanceof Admin) {
            preparedStatement.setString(3, ((Admin) newRole).getEmail());
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, ((Admin) newRole).getRoleType().toString());

        } else if (newRole instanceof Patient) {
            preparedStatement.setString(3, ((Patient) newRole).getEmail());
            preparedStatement.setString(4, ((Patient) newRole).getPhone());
            preparedStatement.setString(5, ((Patient) newRole).getAddress());
            preparedStatement.setString(6, null);
        }
        return preparedStatement.executeUpdate() > 0;
    }

    public int findRoleID(String username) throws SQLException {
        int result = 0;
        String sql = "SELECT id FROM roles WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;
    }

    public Role signIn(String username, String password, RoleType roleType) throws SQLException {
        Role newSignIn = null;
        String sql = "";
        if (roleType == RoleType.ADMIN) {
            sql = "SELECT * FROM roles WHERE username =? AND password = ? AND role_type = 'ADMIN'";
        } else {
            sql = "SELECT * FROM roles WHERE username = ? AND password = ?";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String email = resultSet.getString(4);
            String phone = resultSet.getString(5);
            String adrs = resultSet.getString(6);
            if (roleType == RoleType.ADMIN) {
                newSignIn = new Admin(id, name, password, email);
            } else if (roleType == RoleType.PATIENT) {
                newSignIn = new Patient(id, name, password, phone, adrs, email);
            }
        }
        return newSignIn;
    }

    public Patient findPatientByID(int patientID) throws SQLException {
        Patient result = null;
        String sql = "SELECT * FROM roles WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            String phone = resultSet.getString(5);
            String adrs = resultSet.getString(6);
            result = new Patient(id, username, password, phone, adrs, email);
        }
        return result;
    }
}
