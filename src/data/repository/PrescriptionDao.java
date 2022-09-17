package data.repository;

import data.model.Medicine;
import data.model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDao {
    private PrescriptionDao() {
    }

    private static PrescriptionDao instance = new PrescriptionDao();

    public static PrescriptionDao getInstance() {
        return instance;
    }

    private MedicineDao medicineDao = MedicineDao.getInstance();
    private Connection connection = DBHelper.getConnection();

    public List<Prescription> allPrescription() throws SQLException {
        List<Prescription> prescriptionList = null;
        String sql = "SELECT id,patient_id,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10 FROM prescription";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int prescriptionID = resultSet.getInt(1);
            int patientID = resultSet.getInt(2);
            double totalPrice = resultSet.getDouble(3);
            List<Medicine> medicineList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int medicineID = resultSet.getInt(i + 4);
                Medicine medicine = medicineDao.findMedicineByID(medicineID);
                medicineList.add(medicine);
            }
            Prescription prescription = new Prescription(prescriptionID, patientID, medicineList);
            prescriptionList.add(prescription);
        }

        return prescriptionList;
    }

    public void addPrescription(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO prescription(patient_id,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,prescription.getPatientID());
        int numOfMedicines = prescription.getMedicineList().size();
        for (int i = 0; i < 10 ; i++) {
            if(i < numOfMedicines)
                preparedStatement.setInt(i+2,prescription.getMedicineList().get(i).getID());
            else
                preparedStatement.setInt(i+2,0);
        }
        preparedStatement.executeUpdate();
    }
}
