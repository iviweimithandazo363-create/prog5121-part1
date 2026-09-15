/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.chatapppart1;

/**
 * This class handles user registration, validation and login.
 * 
 */
public class Login {
    
    //Variables used to stored user deatails 
    private String username;
    private String password;
    private String phoneNumber;
    
    
     //Checks whether the username is correctly formatted.
     // The username must contain an underscore and must not be more than 5 characters long.
   
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
        
    }
    //Password validation to check if the password complexity is working perfectly.
    // The password must have a capital letter, atleast 8 characters, a number, a special character
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        //Check each character in the password
        for (char c : password.toCharArray()) {
        
        if (Character.isUpperCase(c)) {
            hasCapital = true;
        }
        if (Character.isDigit(c)) {
            hasNumber = true;
        }
        if (!Character.isLetterOrDigit(c)) {
            hasSpecial = true;
        }
    }
    return password.length() >= 8
            && hasCapital
            && hasNumber
            && hasSpecial;
    }
    //Checks whether the cellphone number is correctly formatted,
    //The number must start with +27 and not be more than 12 characters
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+27")
                && phoneNumber.length() <= 12;
    }
    //Register user after checking all the required details
    public String registerUser(String username, String password, String phoneNumber) {
        
        //Check username
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains and underscore and is not more than five characters in length.";
        }
        //Check password
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains atleast eight characters, a capital letter, a number, and a special character.";
        }
        //Check cellphone number
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cellphone numberis incorrectly formatted or does not contain international code.";
        }
        //Store the users details if  all validation checks pass
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
        return "User registered successfully.";
    }
    //Checks whether the login details match the details used during registration
    public boolean loginUser(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }
    //Returns a message based on whether the login was successful or unsuccessful
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Login successful. Welcome";
        } else {
            return "Login failed. Please check your username and password.";
        }
    }
}
