package data.entity;

import data.enums.MedType;
import data.enums.UsageType;

public class Medicine {
    private int ID;
    private boolean isAvailable;
    private String genericName;
    private String commercialName;
    private int dose;
    private UsageType usageType;
    private MedType medType;
    private double price;

    public Medicine(int ID, boolean isAvailable, String genericName, String commercialName, int dose, UsageType usageType, MedType medType, double price) {
        this.ID = ID;
        this.isAvailable = isAvailable;
        this.genericName = genericName;
        this.commercialName = commercialName;
        this.dose = dose;
        this.usageType = usageType;
        this.medType = medType;
        this.price = price;
    }

    public Medicine(boolean isAvailable, String genericName, String commercialName, int dose, UsageType usageType, MedType medType, double price) {
        this.isAvailable = isAvailable;
        this.genericName = genericName;
        this.commercialName = commercialName;
        this.dose = dose;
        this.usageType = usageType;
        this.medType = medType;
        this.price = price;
    }

    public Medicine(boolean isAvailable, String commercialName) {
        this.isAvailable = isAvailable;
        this.commercialName = commercialName;
    }

    public Medicine(String genericName, String commercialName, int dose) {
        this.genericName = genericName;
        this.commercialName = commercialName;
        this.dose = dose;
    }

    public int getID() {
        return ID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public MedType getMedType() {
        return medType;
    }

    public void setMedType(MedType medType) {
        this.medType = medType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String availability = "";
        if(isAvailable){
            availability += "Available";
        }
        else
            availability += "Not Available";
        return "\tGeneric Name: " + genericName +
                "\tCommercial Name: " + commercialName +
                "\tDose: " + dose +
                "\tUsage Type: " + usageType +
                "\tMedicine Type: " + medType +
                "\tPrice: " + price +
                "\t" + availability;
    }
}
