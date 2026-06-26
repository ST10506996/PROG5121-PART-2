/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;
/**
 *
 * @author lab_services_student
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testUsernameFormattingSuccess() {
        Login login = new Login("kyl_1", "Password123!", "Kyle", "Smith");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameFormattingFailure() {
        Login login = new Login("kyle!!!!!!!", "Password123!", "Kyle", "Smith");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordComplexitySuccess() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordComplexityFailure() {
        Login login = new Login("kyl_1", "password", "Kyle", "Smith");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27838884567", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message("+27838968976", "Test message body");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message("08966553", "Test message body");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }
}
