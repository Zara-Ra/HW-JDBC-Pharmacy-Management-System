package service;

import data.model.Prescription;
import data.model.Role;

import java.util.List;

public class PatientService implements RoleService{
    private PatientService(){}
    private static PatientService instance = new PatientService();
    public static PatientService getInstance(){
        return instance;
    }
    public void addPrescription(){}
    public void editPrescription(){}
    public void deletePrescription(){}
    public List<Prescription> displayConfirmedPrescriptions(){return null;}

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
