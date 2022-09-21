package presentation;

import data.entity.Admin;
import data.entity.Medicine;
import data.entity.Prescription;
import data.entity.Role;
import data.enums.MedType;
import data.enums.RoleType;
import data.enums.UsageType;
import service.AdminService;
import validation.Validate;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminPMS implements UserPMS {
    private final AdminService adminService = AdminService.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private Role role;

    public void firstMenu() throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("Press 1 --> Sign up ");
        System.out.println("Press 2 --> Sign in ");
        System.out.println("Press 3 --> Sign out ");
        System.out.println("Press 4 --> Exit ");
        System.out.println("--------------------------------------------------------");
        int firstChoice = Integer.parseInt(scanner.nextLine());
        switch (firstChoice) {
            case 1:
                if (signUp())
                    secondMenu();
                else {
                    printError("Unable to Sign Up as Admin");
                    firstMenu();
                }
                break;
            case 2:
                if (signIn())
                    secondMenu();
                else {
                    printError("Unable to Sign In as Admin");
                    firstMenu();
                }
                break;
            case 3:
                signOut();
                PMS.welcome();
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    public void secondMenu() throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("Press 1 --> Add New Medicine");
        System.out.println("Press 2 --> Delete a Medicine");
        System.out.println("Press 3 --> Edit Medicine Availability");
        System.out.println("Press 4 --> Confirm Patient Prescriptions ");
        System.out.println("Press 5 --> Previous Menu ");
        System.out.println("--------------------------------------------------------");
        int secondChoice = Integer.parseInt(scanner.nextLine());
        switch (secondChoice) {
            case 1:
                addMedicine();
                secondMenu();
                break;
            case 2:
                deleteMedicine();
                secondMenu();
                break;
            case 3:
                editMedicineAvailability();
                secondMenu();
                break;
            case 4:
                displayUnconfirmedPrescription();
                secondMenu();
                break;
            case 5:
                firstMenu();
                break;
        }
    }

    private void displayUnconfirmedPrescription() throws SQLException {
        List<Prescription> prescriptionList = adminService.displayUnconfirmedPrescriptions();
        for (int i = 0; i < prescriptionList.size(); i++) {
            Prescription prescription = prescriptionList.get(i);
            System.out.println("--------------------------------------------------------");
            System.out.println((i + 1) + " " + prescription);
            System.out.println("Confirm Prescription No." + (i + 1) + " ?(Y/N)");
            String yesNo = scanner.nextLine();
            if (yesNo.equals("Y") || yesNo.equals("y")) {
                List<Medicine> medicineList = prescription.getMedicineList();
                double totoalPrice = 0;
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                for (int j = 0; j < medicineList.size(); j++) {
                    System.out.println((j + 1) + " " + medicineList.get(j));
                    if (medicineList.get(j).isAvailable()) {
                        totoalPrice += medicineList.get(j).getPrice();
                    }
                }
                if (totoalPrice != 0)
                    System.out.println("Total Price of Prescription is: " + totoalPrice);
                else
                    printError("Non of the Medicines of the Prescription is Available");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                prescription.setTotalPrice(totoalPrice);
                adminService.confirmPrescription(prescription);
            }
        }
    }

    private void addMedicine() throws SQLException {
        System.out.println("--------------------------------------------------------");
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
        System.out.println("Is this Medicine Available?(Y/N)");
        boolean isAvalable = false;
        String yesNo = scanner.nextLine();
        if (yesNo.equals("Y") || yesNo.equals("y")) {
            isAvalable = true;
        }
        System.out.println("Enter Medicine Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        Medicine medicine = new Medicine(isAvalable, gname, cname, dose, UsageType.values()[usageType], MedType.values()[medType], price);
        if (adminService.addMedicine(medicine) != null)
            System.out.println("Medicine added Successfully!");
        else
            printError("Unable to Add Medicine");

    }

    private void deleteMedicine() throws SQLException {
        System.out.println("Enter Generic Name: ");
        String gname = scanner.nextLine();
        System.out.println("Enter Commercial Name: ");
        String cname = scanner.nextLine();
        System.out.println("Enter Dose: ");
        int dose = Integer.parseInt(scanner.nextLine());
        Medicine deleteMed = new Medicine(gname, cname, dose);
        if (adminService.deleteMedicine(deleteMed))
            System.out.println("Medicine Deleted Successfully");
        else
            printError("Unable to Delete Medicine");
    }

    private void editMedicineAvailability() throws SQLException { //TODO edit medicine price
        System.out.println("Enter Commercial Name: ");
        String cname = scanner.nextLine();
        System.out.println("Is This Medicine Currently Available? (Y/N)");
        String yesNo = scanner.nextLine();
        boolean isAvailable = false;
        if (yesNo.equals("Y") || yesNo.equals("y"))
            isAvailable = false;
        Medicine editMed = new Medicine(isAvailable, cname);
        if (adminService.editMedicineAvailablity(editMed))
            System.out.println("Medicine Edited Successfully");
        else
            printError("Unable to Edit Medicine Availability");
    }

    public boolean signIn() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        role = adminService.signIn(username, password);
        return role != null;
    }

    public boolean signUp() throws SQLException {
        boolean result = false;
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        if (Validate.isAdminEmailValid(email)) {
            role = new Admin(username, password, email, RoleType.ADMIN);
            role = adminService.signUp(role);
            if (role != null)
                result = true;
        } else
            printError("Invalid Admin Email");
        return result;
    }

    public void signOut() {
        adminService.signOut(role);
    }

    private void printError(String error) {
        System.out.println(error);
    }
}
