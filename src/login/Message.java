/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;
/**
 *
 * @author lab_services_student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    public static int returnTotalMessagesStatic() {
        return totalMessages;
    }
   
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
   
    private static int totalMessages = 0;
   
    ArrayList<String> sentMessages = new ArrayList<>();
   
    // Constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        generateMessageID();
    }
   
    // Generate random message ID
    public void generateMessageID() {
        Random random = new Random();
        Long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        messageID = String.valueOf(number);
    }
   
    // Check message ID
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
   
    // Check recipient cell number
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cellphone number successfully captured.";
        } else {
            return "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
   
    // Check message length
    public String validateMessage() {
        if (message.length() <= 250) {
            return "Message ready to send";
        } else {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra + ", please reduce the size.";
        }
    }

    // Create message hash
    public String createMessageHash() {
        String[] words = message.split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
       
        String hash = messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
       
        return hash;
    }
   
    // Programmatic routing 
    public String processActionChoice(int choice) {
        switch (choice) {
            case 1:
                totalMessages++;
                return "Message successfully sent.";
            case 2:
            case 0:
                return "Press 0 to delete message.";
            case 3:
                return storeMessages();
            default:
                return "Invalid option selected.";
        }
    }
   
    public String storeMessages() {
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write(
                    "{\n"
                    + "\"MessageID\": \"" + messageID + "\",\n"
                    + "\"Recipient\": \"" + recipient + "\",\n"
                    + "\"Message\": \"" + message + "\",\n"
                    + "\"Hash\": \"" + createMessageHash() + "\"\n"
                    + "}\n\n"
            );
            writer.close();
            return "Message successfully stored.";
        } catch (IOException e) {
            return "Error storing message.";
        }
    }

    // Getters & Legacy compatibility methods
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public int returnTotalMessages() { return totalMessages; }
}
