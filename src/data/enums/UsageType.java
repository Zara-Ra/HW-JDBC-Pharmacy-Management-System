package data.enums;

public enum UsageType {
    ORALPILL(1, "Oral Pill"),
    ORALCAPSUL(2, "Oral Capsul"),
    INJECTION(3, "Injection"),
    DERMAL(4, "Dermal"),
    SYRYP(5, "Syryp");
    private final int num;
    private final String name;

    UsageType(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public String stringValue() {
        return num + " -- > " + name;
    }
}
