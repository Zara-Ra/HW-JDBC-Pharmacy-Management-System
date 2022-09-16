package service;

public class PatientService {
    private PatientService(){}
    private static PatientService instance = new PatientService();
    public static PatientService getInstance(){
        return instance;
    }
}
