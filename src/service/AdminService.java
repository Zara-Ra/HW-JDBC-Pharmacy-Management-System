package service;

import data.model.Medicine;
import data.model.Prescription;
import data.model.Role;
import data.model.enums.RoleType;
import data.repository.MedicineDao;
import data.repository.PrescriptionDao;
import data.repository.RoleDao;

import java.sql.SQLException;
import java.util.List;

public class AdminService implements RoleService {
    private AdminService() {
    }

    private static AdminService instance = new AdminService();

    public static AdminService getInstance() {
        return instance;
    }

    private PrescriptionDao prescriptionDao = PrescriptionDao.getInstance();
    private MedicineDao medicineDao = MedicineDao.getInstance();
    private RoleDao roleDao = RoleDao.getInstance();

    public List<Prescription> displayAllPrescriptions() {
        return null;
    }

    public boolean confirmPrescription() {
        return false;
    }

    public boolean confirmMedicineAvailability() {
        return false;
    }

    public Medicine addMedicine(Medicine medicine) throws SQLException {
        if (medicineDao.addMedicine(medicine)) {
            int medicineID = medicineDao.findMedID(medicine.getCommercialName());
            medicine.setID(medicineID);
            return medicine;
        }
        return null;
    }

    public boolean deleteMedicine() {
        return false;
    }

    @Override
    public Role signIn(String username, String password) throws SQLException {
        return roleDao.signIn(username, password, RoleType.ADMIN);
    }

    @Override
    public boolean signOut(Role role) {
        role = null;
        return true;
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
