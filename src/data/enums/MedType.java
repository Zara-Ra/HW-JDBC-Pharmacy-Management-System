package data.enums;

public enum MedType {
    DIABETIS(1, "Diabetis"),
    ANTIBIOTICS(2, "Anit-Biotics"),
    BLODPRESSURE(3, "Blood Pressure"),
    PAINRELIEF(4, "Pain Relief"),
    ANTIVIRUS(5, "Anti-Virus"),
    ALLERGIC(6, "Allergic"),
    SKINCARE(7, "Skin Care"),
    HORMONIC(8, "Hormonic");
    private final int num;
    private final String name;

    MedType(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public String stringValue() {
        return num + " -- > " + name;
    }
}
