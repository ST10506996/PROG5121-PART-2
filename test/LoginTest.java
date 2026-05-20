/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



/**
 *
 * @author lab_services_student
 */
import login.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

    


public class LoginTest {

    Login login = new Login();

    // -------- USERNAME TESTS --------
    @Test
    public void testUsernameCorrect() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        assertFalse(login.checkUserName("kyle!!!!"));
    }

    // -------- PASSWORD TESTS --------
    @Test
    public void testPasswordCorrect() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrect() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // -------- PHONE NUMBER TESTS --------
    @Test
    public void testCellPhoneCorrect() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrect() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // -------- REGISTER USER TESTS (assertEquals) --------
    @Test
    public void testRegisterSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testRegisterUsernameFail() {
        String result = login.registerUser("wrong", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterPasswordFail() {
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterPhoneFail() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    // -------- LOGIN TESTS --------
    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFail() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "1234"));
    }
}