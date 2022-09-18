package data.repository;

import data.entity.Medicine;
import data.enums.MedType;
import data.enums.UsageType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineDao {
    private MedicineDao() {
    }

    private static MedicineDao instance = new MedicineDao();

    public static MedicineDao getInstance() {
        return instance;
    }

    private Connection connection = DBHelper.getConnection();

    public boolean addMedicine(Medicine medicine) throws SQLException {
        boolean result = false;
        String sql = "INSERT INTO medicine (is_available,generic_name,commercial_name,dose,usage_type,med_type,price) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setBoolean(1, medicine.isAvailable());
        preparedStatement.setString(2, medicine.getGenericName());
        preparedStatement.setString(3, medicine.getCommercialName());
        preparedStatement.setInt(4, medicine.getDose());
        preparedStatement.setString(5, medicine.getUsageType().toString());
        preparedStatement.setString(6, medicine.getMedType().toString());
        preparedStatement.setDouble(7, medicine.getPrice());
        return preparedStatement.executeUpdate() > 0;

    }

    public int findMedID(String commercialName) throws SQLException {
        int result = 0;
        String sql = "SELECT id FROM medicine WHERE commercial_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, commercialName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;
    }

    public Medicine findMedicineByID(int id) throws SQLException {
        Medicine medicine = null;
        String sql = "SELECT * FROM medicine WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int medID = resultSet.getInt(1);
            boolean isAvailable = resultSet.getBoolean(2);
            String gname = resultSet.getString(3);
            String cname = resultSet.getString(4);
            int dose = resultSet.getInt(5);
            String usageType = resultSet.getString(6);
            String medType = resultSet.getString(7);
            Double price = resultSet.getDouble(8);
            medicine = new Medicine(medID, isAvailable, gname, cname, dose, UsageType.valueOf(usageType), MedType.valueOf(medType), price);
        }
        return medicine;
    }

    public Medicine findMedicineByCommercialName(String commercialName) throws SQLException {
        Medicine medicine = null;
        String sql = "SELECT * FROM medicine WHERE commercial_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, commercialName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int medID = resultSet.getInt(1);
            boolean isAvailable = resultSet.getBoolean(2);
            String gname = resultSet.getString(3);
            String cname = resultSet.getString(4);
            int dose = resultSet.getInt(5);
            String usageType = resultSet.getString(6);
            String medType = resultSet.getString(7);
            Double price = resultSet.getDouble(8);
            medicine = new Medicine(medID, isAvailable, gname, cname, dose, UsageType.valueOf(usageType), MedType.valueOf(medType), price);
        }
        return medicine;
    }

    public void setMedicineAvailable(Medicine medicine) throws SQLException {
        String sql = "UPDATE medicine SET is_available = true WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,medicine.getID());
        preparedStatement.executeUpdate();
    }
}
