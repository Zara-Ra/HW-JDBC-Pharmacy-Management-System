package data.model;

import data.model.enums.MedType;
import data.model.enums.UsageType;

public class Medicine {
    private int ID;
    private boolean isAvailable;
    private String genericName;
    private String commercialName;
    private int dose;
    private UsageType usageType;
    private MedType medType;
    private double price;
}
