/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package za.ac.iie.chatapppart1;

/**
 * Handles user registration and login for QuickChat.
 */
public class Login {

    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    // Checks that the username contains an underscore and is at most 5 characters.
    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Checks the password requirements.
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean capital = false;
        boolean number = false;
        boolean special = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                capital = true;
            } else if (Character.isDigit(character)) {
                number = true;
            } else if (!Character.isLetterOrDigit(character)) {
                special = true;
            }
        }

        return capital && number && special;
    }

    // Checks a South African cellphone number with the +27 country code.
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("^\\+27\\d{9}$");
    }

    // Registers a user if all details meet the requirements.
    public String registerUser(String username, String password,
                               String phoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;

        return "User registered successfully.";
    }

    // Stores the user's names for the login welcome message.
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Checks the entered login details against the registered details.
    public boolean loginUser(String username, String password) {
        return this.username != null
                && this.password != null
                && this.username.equals(username)
                && this.password.equals(password);
    }

    // Returns the required login status message.
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            String first = firstName == null ? "" : firstName;
            String last = lastName == null ? "" : lastName;

            return "Welcome " + first + ", " + last
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}
