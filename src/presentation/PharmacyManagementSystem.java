package presentation;

import data.model.Admin;
import data.model.Patient;
import data.model.Role;
import data.model.enums.RoleType;
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
        //signUp(RoleType.ADMIN);
        signIn(RoleType.ADMIN);
        //signIn(RoleType.PATIENT);

    }

    private boolean signIn(RoleType roleType) throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if(roleType == RoleType.PATIENT)
            role = patientService.signIn(username,password);
        else if (roleType == RoleType.ADMIN) {
            role = adminService.signIn(username,password);
        }
        if(role != null)
            return true;
        else
            return false;
    }

    private void signUp(RoleType roleType) throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        if(roleType == RoleType.PATIENT) {
            System.out.println("Enter phone number: ");
            String phone = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            role = new Patient(username, password, email, phone, address);
            role = patientService.signUp(role);
        } else if (roleType == RoleType.ADMIN) {
            role = new Admin(username,password,email);
            role = adminService.signUp(role);
        }
    }
}
