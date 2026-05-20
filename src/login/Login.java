/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package login;

/**
 *
 * @author lab_services_student
 */
public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedfirstName;
    private String storedlastName;
    private String firstName;
    private String lastName;
    
    

    // 1. Username check
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // 2. Password check
    public boolean checkPasswordComplexity(String password) {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    // 3. Phone check
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() == 12;
    }

    // 4. Register user
    public String registerUser(String username, String password, String phone, String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // store details
        storedUsername = username;
        storedPassword = password;
        storedfirstName = firstName;
        storedlastName = lastName;

        return "User successfully registered.";
    }

    // 5. Login check
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // 6. Return login message
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            
            return "Welcome " + storedfirstName + " " + storedlastName + " it is great to see you.";
        } else {
            return "Login failed.";
        }
    }

    public String registerUser(String username, String password, String phone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
