package data.model.enums;

public enum UsageType {
    ORALPILL(1,"Oral Pill"),
    ORALCAPSUL(2, "Oral Capsul"),
    INJECTION(3,"Injection"),
    DERMAL(4, "Dermal"),
    SYRYP(5,"Syryp");
    private int num;
    private String name;
    UsageType(int num, String name){
        this.num = num;
        this.name = name;
    }
    public String stringValue(){
        return num + " -- > "+ name;
    }
}
