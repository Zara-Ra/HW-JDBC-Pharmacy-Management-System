package data.repository;

public class PrescriptionDao {
    private PrescriptionDao(){}
    private static PrescriptionDao instance = new PrescriptionDao();
    public static PrescriptionDao getInstance(){
        return instance;
    }
}
