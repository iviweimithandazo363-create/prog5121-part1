/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.chatapppart1;

import java.util.Scanner;

//Main Application class
//This class interacts with the user by collecting registration and login information
public class MainApp {
    
    public static void main (String[] args ) {
        //Scanner allows the program to recieve information typed 
        Scanner scanner = new Scanner(System.in);
        //Create an object of the Login class
        Login login = new Login();
        
        //Useraname registartion
        System.out.println("===USER REGISTRATION===");
        
        //Ask the user for a user name
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        
        //Ask for a password
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        
        //Ask for the cellphone Number
        System.out.println("Enter your cellphone number: ");
        String phoneNumber = scanner.nextLine();
        
        //Takes the information the user entered and sends it to registerUser() method in Login.java
        String registrationMessage = login.registerUser(username, password, phoneNumber);
        
        //Display the registration result
        System.out.println(registrationMessage);
        
        System.out.println("\n===LOGIN===");
        
        //Ask the user to enter the username
        System.out.print("Enter your username to login: ");
        String loginUsername = scanner.nextLine();
        
        //Enter password
        System.out.print("Enter your password to login: ");
        String loginPassword = scanner.nextLine();
        
        //Check whether the login details are correct
        boolean loginSuccessful = login.loginUser (
                loginUsername,
                loginPassword
        );
        //Display the login result
        System.out.println(login.returnLoginStatus(loginSuccessful));
        
        //Close scanner
        scanner.close();
        
         
    }
    
    
 
    
    
}
