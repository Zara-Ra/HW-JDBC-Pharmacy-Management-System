package presentation;

import data.entity.Medicine;
import data.entity.Patient;
import data.entity.Prescription;
import data.entity.Role;
import service.PatientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientPMS implements UserPMS {
    private Scanner scanner = new Scanner(System.in);
    private Role role;
    private PatientService patientService = PatientService.getInstance();
    public void firstMenu() throws SQLException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Press 1 --> Sign up ");
        System.out.println("Press 2 --> Sign in ");
        System.out.println("Press 3 --> Sign out ");
        System.out.println("Press 4 --> Exit ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        int firstChoice = Integer.parseInt(scanner.nextLine());
        switch (firstChoice) {
            case 1:
                signUp();
                secondMenu();
                break;
            case 2:
                if(signIn())
                    secondMenu();
                else
                    firstMenu();
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

    public void secondMenu() throws SQLException{
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Press 1 --> Add a New Prescription");
        System.out.println("Press 2 --> Edit/Delete Prescription");
        System.out.println("Press 3 --> Confirmed Prescriptions");
        System.out.println("Press 4 --> Previous Menu");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        int secondChoice = Integer.parseInt(scanner.nextLine());
        switch (secondChoice){
            case 1:
                addPrescription();
                secondMenu();
                break;
            case 2:
                displayAllUserPrescriptions();
                secondMenu();
                break;
            case 3:
                displayConfirmedPrescription();
                secondMenu();
                break;
            case 4:
                firstMenu();
                break;
        }
    }

    private void displayAllUserPrescriptions() throws SQLException {
        List<Prescription> prescriptionList = patientService.displayAllUserPrescriptions(role.getID());
        for (int i = 0; i < prescriptionList.size(); i++) {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println((i+1) + " : " + prescriptionList.get(i));
        }
        System.out.println("Do you want to Edit or Delete any Prescription? Y/N");
        String yesno = scanner.nextLine();
        if (yesno.equals("Y") || yesno.equals("y")) {
            System.out.println("Enter Prescription number to Edit/Delete: ");
            int PrescNum = Integer.parseInt(scanner.nextLine());
            if (PrescNum <= prescriptionList.size()) {
                Prescription prescription = prescriptionList.get(PrescNum-1);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Press 1 --> Edit Prescription");
                System.out.println("Press 2 --> Delete Prescription");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int editDelete = Integer.parseInt(scanner.nextLine());
                switch (editDelete) {
                    case 1:
                        editPrescription(prescription);
                        break;
                    case 2:
                        deletePrescription(prescription);
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Invalid Number Entered");
                secondMenu();
            }
        } else
            secondMenu();
    }

    private void displayConfirmedPrescription() throws SQLException {
        List<Prescription> prescriptionList = patientService.displayConfirmedPrescriptions(role.getID());
        for (int i = 0; i < prescriptionList.size(); i++) {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println((i+1) + " : " + prescriptionList.get(i));
        }
    }

    private void editPrescription(Prescription prescription) throws SQLException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Press 1 --> Delete a Medicine From Prescription");
        System.out.println("Press 2 --> Add a Medicine To Prescription");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                showMedicineInPrescription(prescription);
                System.out.println("Enter Medicine number to Delete from Prescription");
                int deleteMedicineNum = Integer.parseInt(scanner.nextLine());
                patientService.deleteMedicineFromPrescription(deleteMedicineNum , prescription);
                prescription.getMedicineList().remove(deleteMedicineNum-1);
                break;
            case 2:
                System.out.println("Enter the Commercial Name of the Medicine: ");
                String commercialName = scanner.nextLine();
                Medicine medicine = patientService.findMedicine(commercialName);
                prescription.getMedicineList().add(medicine);
                patientService.addMedicineToPrescription(prescription, medicine);
                break;
            default:
                System.out.println("Invalid number");
                firstMenu();
                break;
        }
    }

    private void showMedicineInPrescription(Prescription prescription) {
        for (int i = 0; i < prescription.getMedicineList().size(); i++) {
            System.out.println((i+1) + " " + prescription.getMedicineList().get(i));
        }
    }

    private void deletePrescription(Prescription prescription) throws SQLException {
        patientService.deletePrescription(prescription);
    }

    private void addPrescription() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        Prescription prescription = new Prescription(role.getID(), medicineList, false);
        System.out.println("How many Medicines do you need?(1 - 10)");
        int numOfMeds = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numOfMeds; i++) {
            System.out.println("Enter the Commercial Name of the No." + (i + 1) + " Medicine: ");
            String commercialName = scanner.nextLine();
            Medicine medicine = patientService.findMedicine(commercialName);
            prescription.getMedicineList().add(medicine);
        }
        patientService.addPrescription(prescription);
    }

    public boolean signIn() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        role = patientService.signIn(username, password);
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
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        role = new Patient(username, password, email, phone, address);
        role = patientService.signUp(role);
    }
    public void signOut(){
        patientService.signOut(role);
    }
}


