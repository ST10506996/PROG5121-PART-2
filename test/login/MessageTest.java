/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;
/**
 *
 * @author lab_services_student
 */
public class MessageTest {

    // A temporary main method that manually runs your test cases sequentially
    public static void main(String[] args) {
        System.out.println("====== RUNNING QUICKCHAT MANUAL UNIT TESTS ======");
        MessageTest runner = new MessageTest();
       
        try {
            runner.testCheckMessageIDLength();
            System.out.println("[PASS] -> testCheckMessageIDLength");
        } catch (Throwable e) {
            System.out.println("[FAIL] -> testCheckMessageIDLength: " + e.getMessage());
        }

        try {
            runner.testCheckRecipientCellSuccess();
            System.out.println("[PASS] -> testCheckRecipientCellSuccess");
        } catch (Throwable e) {
            System.out.println("[FAIL] -> testCheckRecipientCellSuccess: " + e.getMessage());
        }

        try {
            runner.testCheckRecipientCellFailure();
            System.out.println("[PASS] -> testCheckRecipientCellFailure");
        } catch (Throwable e) {
            System.out.println("[FAIL] -> testCheckRecipientCellFailure: " + e.getMessage());
        }

        try {
            runner.testValidateMessageLengthSuccess();
            System.out.println("[PASS] -> testValidateMessageLengthSuccess");
        } catch (Throwable e) {
            System.out.println("[FAIL] -> testValidateMessageLengthSuccess: " + e.getMessage());
        }

        try {
            runner.testCreateMessageHashFormatting();
            System.out.println("[PASS] -> testCreateMessageHashFormatting");
        } catch (Throwable e) {
            System.out.println("[FAIL] -> testCreateMessageHashFormatting: " + e.getMessage());
        }
        System.out.println("================================================");
    }

    public void testCheckMessageIDLength() {
        Message msg = new Message(0, "+27831234567", "Hello World");
        if (!msg.checkMessageID()) {
            throw new RuntimeException("Message ID length check failed!");
        }
    }

    public void testCheckRecipientCellSuccess() {
        Message msg = new Message(0, "+27831234567", "Valid test message body");
        if (!"Cellphone number successfully captured.".equals(msg.checkRecipientCell())) {
            throw new RuntimeException("Expected successful capture string mismatch!");
        }
    }

    public void testCheckRecipientCellFailure() {
        Message msg = new Message(1, "0831234", "Valid body text");
        String expected = "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        if (!expected.equals(msg.checkRecipientCell())) {
            throw new RuntimeException("Failure validation text mismatch!");
        }
    }

    public void testValidateMessageLengthSuccess() {
        Message msg = new Message(2, "+27831234567", "Short message.");
        if (!"Message ready to send".equals(msg.validateMessage())) {
            throw new RuntimeException("Message length check failed!");
        }
    }

    public void testCreateMessageHashFormatting() {
        Message msg = new Message(0, "+27831234567", "Did you get the cake?");
        String generatedHash = msg.createMessageHash();
        String expectedEnd = ":0:DIDCAKE?";
       
        if (!generatedHash.endsWith(expectedEnd)) {
            throw new RuntimeException("Hash formatting mismatch. Got: " + generatedHash);
        }
    }
}
