/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Message {

    static String returnTotalMessagesStatic() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        }
        
        else {
            
            return "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    // Checkmessage length
    public String validateMessage() {
        
        if (message.length() <= 250) {
            
            return "Message ready to send";
        }
        else {
            
            int extra = message.length() - 250;
            
            return "Message exceeds 250 characters by "
                    + extra
                    + ", please reduce the size.";
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
    
    //Send message options
    public String sendMessage() {
        
        String option = JOptionPane.showInputDialog(
                """
                Choose an option:
                1. Send Message
                2. Disregard Message
                3. Store Message
                """
        );
        
        switch (option) {
            
            case "1":
                
                totalMessages++;
                
                
                return "Message successfully sent.";
                
            case "2":
                
                return "Press 0 to delete message.";
                
            case "3":
                
                return "Message successfully stored.";
                
            default:
                
                return "Invalid option selected.";
                
        }
    }
    
    // Store message
    public void storeMessage() {
        
        System.out.println("Message stored in JSON format.");
    }
    
    // Print all messages
    public String printMessages() {
        
        String output = "";
        
        for (String msg : sentMessages) {
            
            output += msg + "\n";
        }
        
        return output;
    }
    
    // Return total messages
    public int returnTotalMessages() {
        
        return totalMessages;
    }
    
    // Getters
    public String getMessageID() {
        return messageID;
    }
    
    public int getMessageNumber() {
        return messageNumber;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public String getMessage() {
        return message;
    }
    
    public static int returnTotalMessageStatic() {
        
        return totalMessages;
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
}
