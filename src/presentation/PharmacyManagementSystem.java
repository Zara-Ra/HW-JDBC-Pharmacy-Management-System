package presentation;

import data.model.Admin;
import data.model.Medicine;
import data.model.Patient;
import data.model.Role;
import data.model.enums.MedType;
import data.model.enums.RoleType;
import data.model.enums.UsageType;
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
        //signIn(RoleType.ADMIN);
        //signIn(RoleType.PATIENT);
        addMedicine();
    }

    private void addMedicine() throws SQLException {
        System.out.println("Enter Generic Name: ");
        String gname = scanner.nextLine();
        System.out.println("Enter Commercial Name: ");
        String cname = scanner.nextLine();
        System.out.println("Enter Dose: ");
        int dose = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Usage Type: ");
        for (int i = 0; i < UsageType.values().length; i++) {
            System.out.println(UsageType.values()[i].stringValue());
        }
        int usageType = Integer.parseInt(scanner.nextLine());
        usageType--;
        System.out.println("Enter Medicine Type: ");
        for (int i = 0; i < MedType.values().length; i++) {
            System.out.println(MedType.values()[i].stringValue());
        }
        int medType = Integer.parseInt(scanner.nextLine());
        medType--;
        System.out.println("Is this Medicine Available? Y/N");
        String yesno = scanner.nextLine();
        boolean isAvalable = false;
        double price = 0;
        if(yesno.equals("Y") || yesno.equals("y") ) {
            isAvalable = true;
            System.out.println("Enter Price: ");
            price = Double.parseDouble(scanner.nextLine());
        }
        Medicine medicine = new Medicine(isAvalable,gname,cname,dose,UsageType.values()[usageType],MedType.values()[medType],price);
        medicine = adminService.addMedicine(medicine);
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
