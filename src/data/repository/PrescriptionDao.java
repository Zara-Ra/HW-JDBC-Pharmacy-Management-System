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

    public List<Prescription> allPrescription(boolean confirmed) throws SQLException {
        List<Prescription> prescriptionList = null;
        String sql = "SELECT id,patient_id,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10 FROM prescription WHERE is_confirmed = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setBoolean(1,confirmed);
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

    public List<Prescription> allConfirmedPrescriptions(int patientID) throws SQLException {
        List<Prescription> prescriptionList = new ArrayList<>();
        String sql = "SELECT id,patient_id,total_price,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10 FROM prescription WHERE is_confirmed = true AND patient_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,patientID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int prescriptionID = resultSet.getInt(1);
            int pID = resultSet.getInt(2);
            double totalPrice = resultSet.getDouble(3);
            List<Medicine> medicineList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int medicineID = resultSet.getInt(i + 4);
                if(medicineID !=0) {
                    Medicine medicine = medicineDao.findMedicineByID(medicineID);
                    medicineList.add(medicine);
                }
            }
            Prescription prescription = new Prescription(prescriptionID, patientID, medicineList);
            prescriptionList.add(prescription);
        }

        return prescriptionList;
    }

    public void addPrescription(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO prescription(patient_id,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10,is_confirmed) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,prescription.getPatientID());
        int numOfMedicines = prescription.getMedicineList().size();
        for (int i = 0; i < 10 ; i++) {
            if(i < numOfMedicines)
                preparedStatement.setInt(i+2,prescription.getMedicineList().get(i).getID());
            else
                preparedStatement.setInt(i+2,0);
        }
        preparedStatement.setBoolean(12,prescription.isConfirmed());
        preparedStatement.executeUpdate();
    }

    public void deletePrescription(Prescription prescription) {
        String sql = "DELETE FROM prescription WHERE ";
    }

    public void deleteMedicineFromPrescription(int deleteMedicineNum, Prescription prescription) throws SQLException {
        String deleteMed = "med_id_"+String.valueOf(deleteMedicineNum);
        String sql = "UPDATE prescription SET "+ deleteMed + " = 0 WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,prescription.getID());
        preparedStatement.executeUpdate();
    }

    public void addMedicineToPrescription(Prescription prescription, Medicine medicine) throws SQLException {
        int emptyPosition = findEmptyMedicine(prescription);
        String emptyColumn = "med_id_"+String.valueOf(emptyPosition);
        String sql ="UPDATE prescription SET "+emptyColumn+" = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,medicine.getID());
        preparedStatement.setInt(2,prescription.getID());
        preparedStatement.executeUpdate();
    }
    public int findEmptyMedicine(Prescription prescription) throws SQLException {
        int result = 0;
        String sql = "SELECT med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10 FROM prescription WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,prescription.getID());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int[] medIDs = new int[10];
            for (int i = 0; i < 10; i++) {
                medIDs[i] = resultSet.getInt(i+1);
                if(medIDs[i] == 0) {
                    result = i + 1;
                    break;
                }
            }
        }
        return result;
    }

    public List<Prescription> allUserPrescriptions(int patientID) throws SQLException {
        List<Prescription> prescriptionList = new ArrayList<>();
        String sql = "SELECT id,patient_id,total_price,med_id_1,med_id_2,med_id_3,med_id_4,med_id_5,med_id_6,med_id_7,med_id_8,med_id_9,med_id_10 FROM prescription WHERE patient_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,patientID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int prescriptionID = resultSet.getInt(1);
            int pID = resultSet.getInt(2);
            double totalPrice = resultSet.getDouble(3);
            List<Medicine> medicineList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int medicineID = resultSet.getInt(i + 4);
                if(medicineID !=0) {
                    Medicine medicine = medicineDao.findMedicineByID(medicineID);
                    medicineList.add(medicine);
                }
            }
            Prescription prescription = new Prescription(prescriptionID, patientID, medicineList);
            prescriptionList.add(prescription);
        }

        return prescriptionList;
    }
}
