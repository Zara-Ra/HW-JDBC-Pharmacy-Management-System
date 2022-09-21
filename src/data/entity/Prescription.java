package data.entity;

import java.util.List;

public class Prescription {
    private int ID;
    private Patient patient;
    private List<Medicine> medicineList;
    private double totalPrice;
    private boolean isConfirmed;

    public Prescription(int ID, Patient patient, List<Medicine> medicineList) {
        this.ID = ID;
        this.patient = patient;
        this.medicineList = medicineList;
    }

    public Prescription(Patient patient, List<Medicine> medicineList, boolean isConfirmed) {
        this.patient = patient;
        this.medicineList = medicineList;
        this.isConfirmed = isConfirmed;
    }

    public Prescription(int prescriptionID, Patient patient, List<Medicine> medicineList, double totalPrice) {
        this.ID = prescriptionID;
        this.patient = patient;
        this.medicineList = medicineList;
        this.totalPrice = totalPrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    @Override
    public String toString() {
        String meds = "";
        for (int i = 0; i < medicineList.size(); i++) {
            meds += medicineList.get(i).toString() + "\n";
        }
        return "Prescription Info:" +
                " Total Price = " + totalPrice +
                "\n Medicenes \n" + meds;
    }
}
