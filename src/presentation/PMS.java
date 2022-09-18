package presentation;

import data.model.Admin;
import data.model.Patient;
import data.model.enums.RoleType;

import java.sql.SQLException;

public class PMS {
    public static void main(String[] args) throws SQLException {
        System.out.println("~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~");
        System.out.println();
        System.out.println("*~*~*    Welcome to Pharmacy Management System     *~*~*");
        System.out.println("                                                    ZaRa");
        System.out.println("~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~~*~*~*~");
        PatientPMS pms = new PatientPMS();
        pms.firstMenu();
    }
    }
