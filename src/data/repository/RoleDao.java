package data.repository;

import data.model.Admin;
import data.model.Patient;
import data.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
    private RoleDao(){}
    private static RoleDao instance = new RoleDao();
    public static RoleDao getInstance(){
        return instance;
    }
    private Connection connection = DBHelper.getConnection();

    public boolean signUp(Role newRole) throws SQLException {
        String sql = "INSERT INTO roles(username,password,email,phone,address) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newRole.getUsername());
        preparedStatement.setString(2,newRole.getPassword());
        if(newRole instanceof Admin) {
            preparedStatement.setString(3, ((Admin) newRole).getEmail());
        } else if (newRole instanceof Patient) {
            preparedStatement.setString(3, ((Patient)newRole).getEmail());
            preparedStatement.setString(4,((Patient)newRole).getPhone());
            preparedStatement.setString(5,((Patient)newRole).getAddress());
        }
        return preparedStatement.executeUpdate() > 0;
    }

    public int findRoleID(String username) throws SQLException {
        int result = 0;
        String sql = "SELECT id FROM roles WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            result = resultSet.getInt(1);
        }
        return result;
    }
}
