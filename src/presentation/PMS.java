package presentation;

import java.sql.SQLException;
import java.util.Scanner;

public class PMS {
    public static void main(String[] args) throws SQLException {
        welcome();
    }

    public static void welcome() throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("-----    Welcome to Pharmacy Management System     -----");
        System.out.println("---------------------------------------------------zara-");
        System.out.println("  Press 1 --> Admin");
        System.out.println("  Press 2 --> Patient");
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int role = Integer.parseInt(scanner.nextLine());
        switch (role) {
            case 1:
                AdminPMS adminPMS = new AdminPMS();
                adminPMS.firstMenu();
                break;
            case 2:
                PatientPMS patientPMS = new PatientPMS();
                patientPMS.firstMenu();
                break;
        }
    }
}
