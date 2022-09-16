package data.model;

import data.model.enums.MedType;
import data.model.enums.UsageType;

public class Medicine {
    private int ID;
    private boolean isAvailable;
    private String genericName;
    private String commercialName;
    private UsageType usageType;
    private MedType medType;
    private int dose;
    private double price;
}
