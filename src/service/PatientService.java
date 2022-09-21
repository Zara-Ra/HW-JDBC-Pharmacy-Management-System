package service;

import data.entity.Medicine;
import data.entity.Patient;
import data.entity.Prescription;
import data.entity.Role;
import data.enums.RoleType;
import data.repository.PrescriptionDao;
import data.repository.RoleDao;

import java.sql.SQLException;
import java.util.List;

public class PatientService implements RoleService {
    private PatientService() {
    }

    private static final PatientService instance = new PatientService();

    public static PatientService getInstance() {
        return instance;
    }

    private final PrescriptionDao prescriptionDao = PrescriptionDao.getInstance();
    private final RoleDao roleDao = RoleDao.getInstance();

    public void addPrescription(Prescription prescription) throws SQLException {
        prescriptionDao.addPrescription(prescription);
    }

    public void deletePrescription(Prescription prescription) throws SQLException {
        prescriptionDao.deletePrescription(prescription);
    }

    public List<Prescription> confirmedPrescriptions(Patient patient) throws SQLException {
        return prescriptionDao.confirmedPrescriptions(patient.getID());
    }

    public List<Prescription> allUserPrescriptions(Patient patient) throws SQLException {
        return prescriptionDao.allUserPrescriptions(patient.getID());
    }

    public void deleteMedicineFromPrescription(int deleteMedicine, Prescription prescription) throws SQLException {
        prescriptionDao.deleteMedicineFromPrescription(deleteMedicine, prescription);
    }

    public void addMedicineToPrescription(Prescription prescription, Medicine medicine) throws SQLException {
        prescriptionDao.addMedicineToPrescription(prescription, medicine);
    }

    @Override
    public Role signIn(String username, String password) throws SQLException {
        return roleDao.signIn(username, password, RoleType.PATIENT);
    }

    @Override
    public boolean signOut(Role role) {
        role = null;
        return true;
    }

    @Override
    public Role signUp(Role newRole) throws SQLException {
        if (roleDao.signUp(newRole)) {
            int roleID = roleDao.findID(newRole.getUsername());
            newRole.setID(roleID);
            return newRole;
        }
        return null;
    }
}
