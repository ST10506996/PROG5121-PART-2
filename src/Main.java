/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;
/**
 *
 * @author lab_services_student
 */
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Login authSystem = new Login();

    // Parallel Arrays
    private static String[] sentMessages;
    private static String[] disregardedMessages;
    private static String[] storedMessages;
    private static String[] messageHashes;
    private static String[] messageIDs;
    private static String[] recipients;
   
    private static int internalCounter = 0;

    public static void main(String[] args) {
        System.out.println(">> QuickChat Registration <<");
        System.out.print("Enter first name: ");
        String fName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lName = scanner.nextLine();
        System.out.print("Enter phone (+27...): ");
        String phone = scanner.nextLine();
        System.out.print("Enter desired username: ");
        String user = scanner.nextLine();
        System.out.print("Enter secure password: ");
        String pass = scanner.nextLine();

        String regStatus = authSystem.registerUser(user, pass, phone, fName, lName);
        System.out.println(regStatus);

        if (!regStatus.contains("successfully")) return;

        System.out.println("\n>> QuickChat Login <<");
        System.out.print("Username: ");
        String logUser = scanner.nextLine();
        System.out.print("Password: ");
        String logPass = scanner.nextLine();

        boolean isSuccess = authSystem.loginUser(logUser, logPass);
        System.out.println(authSystem.returnLoginStatus(isSuccess));

        if (!isSuccess) return;

        // Dynamic parallel array allocation initialization
        System.out.print("\nHow many messages do you wish to define for this session? ");
        int dynamicLimit = scanner.nextInt();
        scanner.nextLine();

        sentMessages = new String[dynamicLimit];
        disregardedMessages = new String[dynamicLimit];
        storedMessages = new String[dynamicLimit];
        messageHashes = new String[dynamicLimit];
        messageIDs = new String[dynamicLimit];
        recipients = new String[dynamicLimit];

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages (Coming Soon)");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages Menu");
            System.out.print("Select choice: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    if (internalCounter >= dynamicLimit) {
                        System.out.println("Session array bounds overflow reached.");
                        break;
                    }
                    executeSendMessageSequence();
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                case 4:
                    executeDataMenuLayer();
                    break;
                default:
                    System.out.println("Unknown command structural choice.");
            }
        }
    }

    private static void executeSendMessageSequence() {
        System.out.print("Enter recipient cellphone: ");
        String targetCell = scanner.nextLine();
        System.out.print("Enter payload text: ");
        String messageBody = scanner.nextLine();

        Message msgObj = new Message(internalCounter, targetCell, messageBody);

        System.out.println(msgObj.checkRecipientCell());
        System.out.println(msgObj.validateMessage());

        if (messageBody.length() > 250) return;

        System.out.println("Choose processing route:\n1. Send Message\n2. Disregard Message\n3. Store Message");
        System.out.print("Your Option: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        // Bind data across all parallel array fields
        recipients[internalCounter] = msgObj.getRecipient();
        messageIDs[internalCounter] = msgObj.getMessageID();
        messageHashes[internalCounter] = msgObj.createMessageHash();

        System.out.println(msgObj.processActionChoice(action));

        if (action == 1) {
            sentMessages[internalCounter] = msgObj.getMessage();
        } else if (action == 2 || action == 0) {
            disregardedMessages[internalCounter] = msgObj.getMessage();
        } else if (action == 3) {
            storedMessages[internalCounter] = msgObj.getMessage();
        }

        // Print Transaction details matching layout reports
        System.out.println("Captured ID: " + msgObj.getMessageID() + " | Hash Code Generated: " + msgObj.createMessageHash());
        internalCounter++;
    }

    private static void executeDataMenuLayer() {
        System.out.println("\n--- POE Array Metrics Option Sub-Menu ---");
        System.out.println("a) Display Sender & Recipient of stored messages");
        System.out.println("b) Display the longest message payload");
        System.out.println("c) Search for a specific Message ID");
        System.out.println("d) Filter all messages matching Recipient");
        System.out.println("e) Delete message using unique structural hash");
        System.out.println("f) View all active tracking reports");
        System.out.print("Selection: ");
        String filterMode = scanner.nextLine().toLowerCase();

        switch (filterMode) {
            case "a":
                for (int i = 0; i < storedMessages.length; i++) {
                    if (storedMessages[i] != null) {
                        System.out.println("Recipient Match: " + recipients[i] + " -> Content: " + storedMessages[i]);
                    }
                }
                break;
            case "b":
                String currentLongest = "";
                for (String text : sentMessages) {
                    if (text != null && text.length() > currentLongest.length()) currentLongest = text;
                }
                for (String text : storedMessages) {
                    if (text != null && text.length() > currentLongest.length()) currentLongest = text;
                }
                System.out.println("Longest String found: " + (currentLongest.isEmpty() ? "No data found." : currentLongest));
                break;
            case "c":
                System.out.print("Target Message ID: ");
                String keyId = scanner.nextLine();
                boolean matchFound = false;
                for (int i = 0; i < messageIDs.length; i++) {
                    if (keyId.equals(messageIDs[i])) {
                        String bodyText = sentMessages[i] != null ? sentMessages[i] : storedMessages[i];
                        System.out.println("Match found! To: " + recipients[i] + " | Content: " + bodyText);
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) System.out.println("ID entry not found.");
                break;
            case "d":
                System.out.print("Target Recipient Contact: ");
                String cellKey = scanner.nextLine();
                for (int i = 0; i < recipients.length; i++) {
                    if (cellKey.equals(recipients[i])) {
                        String textVal = sentMessages[i] != null ? sentMessages[i] : storedMessages[i];
                        if (textVal != null) System.out.println("Found entry message: " + textVal);
                    }
                }
                break;
            case "e":
                System.out.print("Target Unique Hash: ");
                String targetHash = scanner.nextLine().toUpperCase();
                for (int i = 0; i < messageHashes.length; i++) {
                    if (targetHash.equals(messageHashes[i])) {
                        String deletedBody = sentMessages[i] != null ? sentMessages[i] : disregardedMessages[i];
                        if (deletedBody == null) deletedBody = storedMessages[i];
                       
                        sentMessages[i] = null;
                        disregardedMessages[i] = null;
                        storedMessages[i] = null;
                        messageHashes[i] = null;
                        messageIDs[i] = null;
                        recipients[i] = null;
                        System.out.println("Message: \"" + deletedBody + "\" successfully deleted.");
                        break;
                    }
                }
                break;
            case "f":
                System.out.println("\n--- Complete QuickChat Report System ---");
                for (int i = 0; i < internalCounter; i++) {
                    if (messageHashes[i] != null) {
                        String finalBody = sentMessages[i] != null ? sentMessages[i] : storedMessages[i];
                        System.out.println("Hash: " + messageHashes[i] + " | Phone: " + recipients[i] + " | Data: " + finalBody);
                    }
                }
                break;
            default:
                System.out.println("Invalid structural sub-option choice.");
        }
    }
}
