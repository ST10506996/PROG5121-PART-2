/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
import java.util.Scanner;
import javax.swing.JOptionPane;
import login.Login;

public class Main {

    public static void main(String[] args) {

        Login login = new Login();

        // =========================
        // REGISTRATION
        // =========================

        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String phone = JOptionPane.showInputDialog("Enter phone number:");
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");

        String registerResult = login.registerUser(
                username,
                password,
                phone,
                firstName,
                lastName
        );

        JOptionPane.showMessageDialog(null, registerResult);

        // =========================
        // LOGIN
        // =========================

        String loginUsername = JOptionPane.showInputDialog("Login username:");
        String loginPassword = JOptionPane.showInputDialog("Login password:");

        boolean loginSuccess = login.loginUser(
                loginUsername,
                loginPassword
        );

        JOptionPane.showMessageDialog(
                null,
                login.returnLoginStatus(loginSuccess)
        );

        // STOP if login fails
        if (!loginSuccess) {

            System.exit(0);
        }

        // =========================
        // QUICKCHAT
        // =========================

        JOptionPane.showMessageDialog(
                null,
                "Welcome to QuickChat."
        );

        int totalToSend = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "How many messages would you like to send?"
                )
        );

        int currentMessage = 1;

        while (currentMessage <= totalToSend) {

            String menu = JOptionPane.showInputDialog(
                    """
                    Select an option:
                    
                    1. Send Messages
                    2. Show recently sent messages
                    3. Quit
                    """
            );

            switch (menu) {

                case "1":

                    String recipient = JOptionPane.showInputDialog(
                            "Enter recipient number:"
                    );

                    String messageText = JOptionPane.showInputDialog(
                            "Enter your message:"
                    );

                    Message msg = new Message(
                            currentMessage,
                            recipient,
                            messageText
                    );

                    // Recipient validation
                    JOptionPane.showMessageDialog(
                            null,
                            msg.checkRecipientCell()
                    );

                    // Message validation
                    JOptionPane.showMessageDialog(
                            null,
                            msg.validateMessage()
                    );

                    // Message details
                    String details =
                            "Message ID: " + msg.getMessageID()
                            + "\nMessage Hash: " + msg.createMessageHash()
                            + "\nRecipient: " + msg.getRecipient()
                            + "\nMessage: " + msg.getMessage();

                    JOptionPane.showMessageDialog(
                            null,
                            details
                    );

                    // Send/discard/store
                    JOptionPane.showMessageDialog(
                            null,
                            msg.sendMessage()
                    );

                    currentMessage++;

                    break;

                case "2":

                    JOptionPane.showMessageDialog(
                            null,
                            "Coming Soon."
                    );

                    break;

                case "3":

                    JOptionPane.showMessageDialog(
                            null,
                            "Total messages sent: "
                            + Message.returnTotalMessagesStatic()
                    );

                    System.exit(0);

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid option selected."
                    );
            }
        }

        JOptionPane.showMessageDialog(null,
                "Total messages sent: "
                + Message.returnTotalMessagesStatic()
        );
    }
}
