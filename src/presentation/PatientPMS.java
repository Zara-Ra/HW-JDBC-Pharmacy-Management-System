package presentation;

import data.entity.Medicine;
import data.entity.Patient;
import data.entity.Prescription;
import data.entity.Role;
import service.PatientService;
import validation.Validate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientPMS implements UserPMS {
    private final Scanner scanner = new Scanner(System.in);
    private Role role;
    private final PatientService patientService = PatientService.getInstance();

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
                    printError("Unable to Sign Up");
                    firstMenu();
                }
                break;
            case 2:
                if (signIn())
                    secondMenu();
                else {
                    printError("Unable to Sign In");
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

    private void printError(String error) {
        System.out.println(error);
    }

    public void secondMenu() throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("Press 1 --> Add a New Prescription");
        System.out.println("Press 2 --> Edit/Delete Prescription");
        System.out.println("Press 3 --> Confirmed Prescriptions");
        System.out.println("Press 4 --> Previous Menu");
        System.out.println("--------------------------------------------------------");

        int secondChoice = Integer.parseInt(scanner.nextLine());
        switch (secondChoice) {
            case 1: {
                addPrescription();
                secondMenu();
            }
            case 2: {
                modifyPrescriptions();
                secondMenu();
            }
            case 3: {
                displayConfirmedPrescription();
                secondMenu();
            }
            case 4:
                firstMenu();
        }
    }

    private void modifyPrescriptions() throws SQLException {
        List<Prescription> prescriptionList = patientService.allUserPrescriptions(role.getID());
        printPrescriptions(prescriptionList);
        System.out.println("Do you want to Edit or Delete any Prescription? Y/N");
        String yesNo = scanner.nextLine();
        if (yesNo.equals("Y") || yesNo.equals("y")) {
            System.out.println("Enter Prescription number to Edit/Delete: ");
            int selectedPrescription = Integer.parseInt(scanner.nextLine());
            if (selectedPrescription > 0 && selectedPrescription <= prescriptionList.size()) {
                Prescription prescription = prescriptionList.get(selectedPrescription - 1);
                System.out.println("--------------------------------------------------------");
                System.out.println("Press 1 --> Edit Prescription");
                System.out.println("Press 2 --> Delete Prescription");
                System.out.println("--------------------------------------------------------");
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
                printError("Invalid Prescription Number Entered");
            }
        }
    }

    private void printPrescriptions(List<Prescription> prescriptionList) {
        for (int i = 0; i < prescriptionList.size(); i++) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println((i + 1) + " : " + prescriptionList.get(i));
        }
    }

    private void displayConfirmedPrescription() throws SQLException {
        List<Prescription> prescriptionList = patientService.confirmedPrescriptions(role.getID());
        printPrescriptions(prescriptionList);
    }

    private void editPrescription(Prescription prescription) throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("Press 1 --> Delete a Medicine From Prescription");
        System.out.println("Press 2 --> Add a Medicine To Prescription");
        System.out.println("--------------------------------------------------------");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                printMedicineInPrescription(prescription);
                System.out.println("Enter Medicine number to Delete from Prescription");
                int deleteMedicineNum = Integer.parseInt(scanner.nextLine());
                patientService.deleteMedicineFromPrescription(deleteMedicineNum, prescription);
                prescription.getMedicineList().remove(deleteMedicineNum - 1);
                System.out.println("Medicine Deleted from Prescription");
                break;
            case 2:
                System.out.println("Enter the Commercial Name of the Medicine: ");
                String commercialName = scanner.nextLine();
                Medicine medicine = patientService.findMedicine(commercialName);
                if(medicine != null) {
                    prescription.getMedicineList().add(medicine);
                    patientService.addMedicineToPrescription(prescription, medicine);
                    System.out.println("Medicine Added to Prescription");
                }
                else
                    printError(commercialName +" is Not a Valid Medicine Name");
                break;
            default:
                printError("Invalid Input Number");
                break;
        }
    }

    private void printMedicineInPrescription(Prescription prescription) {
        for (int i = 0; i < prescription.getMedicineList().size(); i++) {
            System.out.println((i + 1) + " " + prescription.getMedicineList().get(i));
        }
    }

    private void deletePrescription(Prescription prescription) throws SQLException {
        patientService.deletePrescription(prescription);
    }

    private void addPrescription() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        Prescription prescription = new Prescription((Patient) role, medicineList, false);
        System.out.println("How many Medicines do you need?(1 - 10)");
        int numOfMeds = Integer.parseInt(scanner.nextLine());
        if (numOfMeds > 0 && numOfMeds <= 10) {
            for (int i = 0; i < numOfMeds; i++) {
                System.out.println("Enter the Commercial Name of the No." + (i + 1) + " Medicine: ");
                String commercialName = scanner.nextLine();
                Medicine medicine = patientService.findMedicine(commercialName);
                if (medicine != null)
                    prescription.getMedicineList().add(medicine);
                else
                    printError(commercialName + " is Not a Valid Medicine Name");
            }
            patientService.addPrescription(prescription);
        } else
            printError("A Prescription must contain  1 - 10 Medicines");
    }

    public boolean signIn() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        role = patientService.signIn(username, password);
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
        if (Validate.isEmailValid(email)) {
            System.out.println("Enter phone number: ");
            String phone = scanner.nextLine();
            if (Validate.isphoneNumberValid(phone)) {
                System.out.println("Enter address: ");
                String address = scanner.nextLine();
                role = new Patient(username, password, email, phone, address);
                role = patientService.signUp(role);
                result = true;
            }
        }
        return result;
    }

    public void signOut() {
        patientService.signOut(role);
    }
}


