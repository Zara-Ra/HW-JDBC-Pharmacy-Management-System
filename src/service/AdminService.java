package service;

import data.model.Prescription;
import data.model.Role;

import java.util.List;

public class AdminService implements RoleService {
    private AdminService(){}
    private static AdminService instance = new AdminService();
    public static AdminService getInstance(){
        return instance;
    }
    public List<Prescription> displayAllPrescriptions(){return null;}
    public boolean confirmPrescription(){return false;}
    public boolean confirmMedicineAvailability(){return false;}
    public boolean addMedicine(){return false;}
    public boolean deleteMedicine(){return false;}

    @Override
    public boolean signIn() {
        return false;
    }

    @Override
    public boolean signOut() {
        return false;
    }

    @Override
    public Role signUp() {
        return null;
    }
}
