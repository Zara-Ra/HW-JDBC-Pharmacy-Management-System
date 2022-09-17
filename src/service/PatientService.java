package service;

import data.model.Prescription;
import data.model.Role;
import data.repository.MedicineDao;
import data.repository.PrescriptionDao;
import data.repository.RoleDao;

import java.sql.SQLException;
import java.util.List;

public class PatientService implements RoleService {
    private PatientService() {
    }

    private static PatientService instance = new PatientService();

    public static PatientService getInstance() {
        return instance;
    }

    private PrescriptionDao prescriptionDao = PrescriptionDao.getInstance();
    private RoleDao roleDao = RoleDao.getInstance();

    public void addPrescription() {
    }

    public void editPrescription() {
    }

    public void deletePrescription() {
    }

    public List<Prescription> displayConfirmedPrescriptions() {
        return null;
    }

    @Override
    public boolean signIn(String username, String password) {
        return false;
    }

    @Override
    public boolean signOut(String Role) {
        return false;
    }

    @Override
    public Role signUp(Role newRole) throws SQLException {
        if (roleDao.signUp(newRole)) {
            int roleID = roleDao.findRoleID(newRole.getUsername());
            newRole.setID(roleID);
            return newRole;
        }
        return null;
    }
}
