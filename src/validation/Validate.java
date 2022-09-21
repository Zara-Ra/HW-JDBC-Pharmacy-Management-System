package validation;

public class Validate {
    public static boolean phoneNumberValid(String phoneNumber) {
        return !phoneNumber.equals("") && phoneNumber.matches("^[\\d]{3}-[\\d]{7,9}$");
    }

    public static boolean isEmailValid(String email) {
        return !email.equals("") && email.matches("^[\\w\\.\s]{3,}@[a-zA-Z]{2,}.[a-zA-Z]{2,}$");
    }

    public static boolean isAdminEmailValid(String email) {
        return !email.equals("") && email.matches("^[\\w\\.\s]{3,}@pharmacy.com");
    }
}
