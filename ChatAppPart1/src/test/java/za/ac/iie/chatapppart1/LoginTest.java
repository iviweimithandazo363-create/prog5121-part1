/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.ac.iie.chatapppart1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * This class contains unit tests for the Login class.
 * The tests check username, password, cellphone,
 * registration and login functionality
 */
public class LoginTest {
    
    //Tests whether the username validation works correctly.
    @Test
    public void testCheckUserName() {
        
        Login login = new Login();
        
        //A valid username must contain an underscore and must not be more than 5 characters long.
        assertTrue(login.checkUserName("ab_cd"));
        
        //This username is invalid beacause it does not contain an underscore.
        assertFalse(login.checkUserName("abcd"));
    }
    //Tests whether the password validation works correctly.
    @Test
    public void testCheckPasswordComplexity() {
        
        Login login = new Login();
        
        //This password is valid beacuse it has atleast 8 characters, a capital letter, a number and a special character
        assertTrue(login.checkPasswordComplexity("Password1!"));
        
        //This password is invalid because it does not meet the password requirements
        assertFalse(login.checkPasswordComplexity("password"));
        
    }
    //Tests whether the cellphone number validation works correctly.
    @Test
    public void testCheckCellPhoneNumber() {
        
        Login login = new Login();
        
        //Valid South African cellphone number
        assertTrue(login.checkCellPhoneNumber("+27748747816"));
        
        //Invalid because it does not start with +27
        assertFalse(login.checkCellPhoneNumber("0748747816"));
        
    }
    //Tests whether the user registration works correctly
    @Test
    public void testRegisterUser() {
        
        Login login = new Login();
        
        //Register a user using valid details.
        String result = login.registerUser("ab_cd", "Password1!", "+27748747816");
        
        //Check that the correct success message is returned.
        assertEquals("User registered successfully.", result);
    }
    //Tests whether the login method accepts the correct details and rejects incorrect details.
    @Test
    public void testLoginUser() {
        Login login = new Login();
        
        //First register the user.
        login.registerUser("ab_cd", "Password1!", "+2774874716");
        
        //Correct username and password should successfully login.
        assertTrue(login.loginUser("ab_cd", "Password1!"));
        
        //Incorrect password should not successfully login
        assertFalse(login.loginUser("abcd","password"));   
       
    }
    //Test whether the correct login status messages are returned
    @Test
    public void testReturnLoginStatus() {
        Login login = new Login(); 
        
        //Test the successful login message.
        assertEquals("Login successful. Welcome", login.returnLoginStatus(true));
        
        //Test the failed login message
        assertEquals("Login failed. Please check your username and password.", login.returnLoginStatus(false));
    
    }
      
    }
