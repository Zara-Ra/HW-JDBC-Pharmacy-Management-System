package presentation;

import data.model.*;
import data.model.enums.MedType;
import data.model.enums.RoleType;
import data.model.enums.UsageType;
import service.AdminService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminPMS implements UserPMS {
    private AdminService adminService = AdminService.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private Role role;

    private void displayUnconfirmedPrescription() throws SQLException {
        List<Prescription> prescriptionList = adminService.displayUnconfirmedPrescriptions();
        for (int i = 0; i < prescriptionList.size(); i++) {
            Prescription prescription = prescriptionList.get(i);
            System.out.println(i + " " + prescription);
            System.out.println("Confirm Prescription No." + i + " ?(Y?N)");
            String yesno = scanner.nextLine();
            if (yesno.equals("Y") || yesno.equals("y")) {
                adminService.confirmPrescription(prescription);
                List<Medicine> medicineList = prescription.getMedicineList();
                double totoalPrice = 0;
                for (int j = 0; j < medicineList.size(); j++) {
                    System.out.println(j + " " + medicineList.get(j));
                    System.out.println("Is Medicine No." + j + " Available?");
                    String yesNo = scanner.nextLine();
                    if (yesNo.equals("Y") || yesNo.equals("y")) {
                        System.out.println("Enter the Medicine Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        medicineList.get(j).isAvailable();
                        medicineList.get(j).setPrice(price);
                        adminService.setMedicineAvailable(medicineList.get(j));
                        totoalPrice += price;
                    }
                }
                prescription.setTotalPrice(totoalPrice);
                adminService.setTotalPrescriptonPrice(prescription);
            }
        }
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
        if (yesno.equals("Y") || yesno.equals("y")) {
            isAvalable = true;
            System.out.println("Enter Price: ");
            price = Double.parseDouble(scanner.nextLine());
        }
        Medicine medicine = new Medicine(isAvalable, gname, cname, dose, UsageType.values()[usageType], MedType.values()[medType], price);
        medicine = adminService.addMedicine(medicine);
    }

    public boolean signIn() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        role = adminService.signIn(username, password);
        if (role != null)
            return true;
        return false;
    }

    public void signUp() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        role = new Admin(username, password, email);
        role = adminService.signUp(role);
    }

}
