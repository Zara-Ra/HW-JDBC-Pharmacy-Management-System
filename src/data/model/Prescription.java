package data.model;

import java.util.List;

public class Prescription {
    private int ID;
    private int patientID;
    private List<Medicine> medicineList;
    private double totalPrice;
    private boolean isConfirmed;

    public Prescription(int ID, int patientID, List<Medicine> medicineList) {
        this.ID = ID;
        this.patientID = patientID;
        this.medicineList = medicineList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
