package data.repository;

public class RoleDao {
    private RoleDao(){}
    private static RoleDao instance = new RoleDao();
    public static RoleDao getInstance(){
        return instance;
    }
}
