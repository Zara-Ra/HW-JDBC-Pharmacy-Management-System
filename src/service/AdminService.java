package service;

public class AdminService {
    private AdminService(){}
    private static AdminService instance = new AdminService();
    public static AdminService getInstance(){
        return instance;
    }
}
