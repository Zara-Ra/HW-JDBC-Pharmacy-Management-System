package presentation;

import data.model.*;
import data.model.enums.MedType;
import data.model.enums.RoleType;
import data.model.enums.UsageType;
import service.AdminService;
import service.PatientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PharmacyManagementSystem {
    private Scanner scanner = new Scanner(System.in);
    private Role role;
    private PatientService patientService = PatientService.getInstance();
    private AdminService adminService = AdminService.getInstance();

    public void firstMenu() throws SQLException {
        //signUp(RoleType.ADMIN);
        //signIn(RoleType.ADMIN);
        signIn(RoleType.PATIENT);
        //addMedicine();
        //addPrescription();
        displayConfirmedPrescription();
    }

    private void displayConfirmedPrescription() throws SQLException {
        List<Prescription> prescriptionList = patientService.displayConfirmedPrescriptions(role.getID());
        for (int i = 0; i < prescriptionList.size(); i++) {
            System.out.println(i + " : " + prescriptionList.get(i));
        }
        System.out.println("Do you want to Edit or Delete any Prescription? Y/N");
        String yesno = scanner.nextLine();
        if (yesno.equals("Y") || yesno.equals("y")) {
            System.out.println("Enter Prescription number to Edit/Delete: ");
            int PrescNum = Integer.parseInt(scanner.nextLine());
            if (PrescNum < prescriptionList.size()) {
                Prescription prescription = prescriptionList.get(PrescNum);
                System.out.println();
                System.out.println("Press 1 --> Edit Prescription");
                System.out.println("Press 2 --> Delete Prescription");
                int editDelete = Integer.parseInt(scanner.nextLine());
                if (editDelete == 1)
                    editPrescription(prescription);
                else if (editDelete == 2)
                    deletePrescription(prescription);
            }
            else {
                System.out.println("Invalid Number Entered");
                firstMenu();
            }
        } else
            firstMenu();
    }
    private void editPrescription(Prescription prescription){
        patientService.editPrescription(prescription);
    }
    private void deletePrescription(Prescription prescription){
        patientService.deletePrescription(prescription);
    }

    private void addPrescription() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        Prescription prescription = new Prescription(role.getID(), medicineList, false);
        System.out.println("How many Medicines do need?(1 - 10)");
        int numOfMeds = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numOfMeds; i++) {
            System.out.println("Enter the Commercial Name of the No." + (i + 1) + " Medicine: ");
            String commercialName = scanner.nextLine();
            Medicine medicine = patientService.findMedicine(commercialName);
            prescription.getMedicineList().add(medicine);
        }
        patientService.addPrescription(prescription);
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

    private boolean signIn(RoleType roleType) throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (roleType == RoleType.PATIENT)
            role = patientService.signIn(username, password);
        else if (roleType == RoleType.ADMIN) {
            role = adminService.signIn(username, password);
        }
        if (role != null)
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
        if (roleType == RoleType.PATIENT) {
            System.out.println("Enter phone number: ");
            String phone = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            role = new Patient(username, password, email, phone, address);
            role = patientService.signUp(role);
        } else if (roleType == RoleType.ADMIN) {
            role = new Admin(username, password, email);
            role = adminService.signUp(role);
        }
    }
}
