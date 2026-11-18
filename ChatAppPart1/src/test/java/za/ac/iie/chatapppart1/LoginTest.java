/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package za.ac.iie.chatapppart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void validUsernameIsAccepted() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void invalidUsernameIsRejected() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void validPasswordIsAccepted() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void invalidPasswordIsRejected() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void validCellNumberIsAccepted() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void invalidCellNumberIsRejected() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void validRegistrationSucceeds() {
        Login login = new Login();

        assertEquals(
                "User registered successfully.",
                login.registerUser(
                        "kyl_1",
                        "Ch&&sec@ke99!",
                        "+27838968976"));
    }

    @Test
    public void loginWorksAfterRegistration() {
        Login login = new Login();
        login.setName("Kyle", "Smith");

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void successfulLoginStatusIsCorrect() {
        Login login = new Login();
        login.setName("Kyle", "Smith");

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(true));
    }

    @Test
    public void failedLoginStatusIsCorrect() {
        Login login = new Login();

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false));
    }
}
