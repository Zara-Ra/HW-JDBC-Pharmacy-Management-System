package presentation;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~");
        System.out.println();
        System.out.println("*~*~*    Welcome to Pharmacy Management System     *~*~*");
        System.out.println("                                                    ZaRa");
        System.out.println("~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~");
        PharmacyManagementSystem pms = new PharmacyManagementSystem();
        pms.firstMenu();
    }
}
