package presentation;

import data.model.Patient;
import data.model.Role;
import service.AdminService;
import service.PatientService;

import java.sql.SQLException;
import java.util.Scanner;

public class PharmacyManagementSystem {
    private Scanner scanner = new Scanner(System.in);
    private Role role;
    private PatientService patientService = PatientService.getInstance();
    private AdminService adminService = AdminService.getInstance();
    public void firstMenu() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        role = new Patient(username,password,email,phone,address);
        role = patientService.signUp(role);
    }
}
