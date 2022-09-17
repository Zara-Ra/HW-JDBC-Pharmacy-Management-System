package service;

import data.model.Prescription;
import data.model.Role;
import data.repository.MedicineDao;
import data.repository.PrescriptionDao;
import data.repository.RoleDao;

import java.util.List;

public class AdminService implements RoleService {
    private AdminService(){}
    private static AdminService instance = new AdminService();
    public static AdminService getInstance(){
        return instance;
    }
    private PrescriptionDao prescriptionDao = PrescriptionDao.getInstance();
    private MedicineDao medicineDao = MedicineDao.getInstance();
    private RoleDao roleDao = RoleDao.getInstance();
    public List<Prescription> displayAllPrescriptions(){return null;}
    public boolean confirmPrescription(){return false;}
    public boolean confirmMedicineAvailability(){return false;}
    public boolean addMedicine(){return false;}
    public boolean deleteMedicine(){return false;}

    @Override
    public boolean signIn(String username, String password) {
        return false;
    }

    @Override
    public boolean signOut(String Role) {
        return false;
    }

    @Override
    public Role signUp(Role newRole) {
        return null;
    }
}
