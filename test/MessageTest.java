/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // =========================
    // MESSAGE LENGTH TEST
    // =========================

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                msg.validateMessage()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        Message msg = new Message(
                1,
                "+27718693002",
                longMessage
        );

        assertTrue(
                msg.validateMessage().contains("Message exceeds 250 characters")
        );
    }

    // =========================
    // RECIPIENT NUMBER TESTS
    // =========================

    @Test
    public void testRecipientSuccess() {

        Message msg = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        Message msg = new Message(
                1,
                "0812345678",
                "Hello"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell()
        );
    }

    // =========================
    // HASH TEST
    // =========================

    @Test
    public void testMessageHash() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi tonight"
        );

        String hash = msg.createMessageHash();

        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    // =========================
    // MESSAGE ID TEST
    // =========================

    @Test
    public void testMessageIDCreated() {

        Message msg = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        assertTrue(msg.checkMessageID());
    }

    // =========================
    // SEND MESSAGE TESTS
    // =========================

    @Test
    public void testTotalMessages() {

        Message msg1 = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        Message msg2 = new Message(
                2,
                "+27718693002",
                "Hi again"
        );

        assertNotNull(msg1.getMessageID());
        assertNotNull(msg2.getMessageID());
    }
}
