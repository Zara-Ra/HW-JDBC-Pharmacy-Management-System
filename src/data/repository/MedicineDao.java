package data.repository;

import data.model.Medicine;

public class MedicineDao {
    private MedicineDao(){}
    private static MedicineDao instance = new MedicineDao();
    public static MedicineDao getInstance(){
        return instance;
    }
}
