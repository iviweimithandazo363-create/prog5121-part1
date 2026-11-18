/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package za.ac.iie.chatapppart1;

import java.util.Scanner;

/**
 * Runs the QuickChat registration, login and messaging menus.
 */
public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // Register a new QuickChat user.
        System.out.println("QUICKCHAT REGISTRATION");
        System.out.print("First name: ");
        String firstName = input.nextLine().trim();
        System.out.print("Last name: ");
        String lastName = input.nextLine().trim();
        System.out.print("Username: ");
        String username = input.nextLine().trim();
        System.out.print("Password: ");
        String password = input.nextLine();
        System.out.print("Cellphone number (e.g. +27838968976): ");
        String phone = input.nextLine().trim();

        login.setName(firstName, lastName);
        String registration = login.registerUser(username, password, phone);
        System.out.println(registration);

        if (!registration.equals("User registered successfully.")) {
            input.close();
            return;
        }

        // Log in using the details entered during registration.
        System.out.println("\nQUICKCHAT LOGIN");
        System.out.print("Username: ");
        String loginName = input.nextLine().trim();
        System.out.print("Password: ");
        String loginPassword = input.nextLine();

        boolean success = login.loginUser(loginName, loginPassword);
        System.out.println(login.returnLoginStatus(success));

        if (!success) {
            input.close();
            return;
        }

        System.out.println("Welcome to QuickChat.");

        // Load messages saved during an earlier run.
        Message.readStoredMessages();

        // Ask how many new messages the user wants to enter.
        System.out.print("How many messages would you like to enter? ");
        int messageLimit;

        try {
            messageLimit = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a whole number.");
            input.close();
            return;
        }

        if (messageLimit < 0) {
            System.out.println("Message count cannot be negative.");
            input.close();
            return;
        }

        int entered = 0;

        // Keep displaying the main menu until the user chooses Quit.
        while (true) {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Display messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            String option = input.nextLine().trim();

            if (option.equals("1")) {

                if (entered >= messageLimit) {
                    System.out.println("You have reached the number of messages you selected.");
                    continue;
                }

                // Capture and validate the recipient and message.
                System.out.print("Recipient cellphone number: ");
                String recipient = input.nextLine().trim();

                System.out.print("Enter your message: ");
                String text = input.nextLine();

                Message currentMessage = new Message(
                        Message.generateMessageID(), recipient, text, entered);

                System.out.println("Message ID valid: "
                        + currentMessage.checkMessageID());
                System.out.println(currentMessage.checkRecipientCell());
                System.out.println(currentMessage.checkMessageLength());

                if (!currentMessage.isRecipientValid()
                        || !currentMessage.isMessageLengthValid()) {
                    continue;
                }

                // Let the user choose whether to send, disregard or store the message.
                System.out.println("1. Send");
                System.out.println("2. Disregard");
                System.out.println("3. Store");
                System.out.print("Choose an action: ");

                String action = input.nextLine().trim();
                System.out.println(currentMessage.SentMessage(action));

                if (action.equals("1")) {
                    System.out.println("Message ID: "
                            + currentMessage.getMessageID());
                    System.out.println("Message Hash: "
                            + currentMessage.getMessageHash());
                    System.out.println("Recipient: "
                            + currentMessage.getRecipient());
                    System.out.println("Message: "
                            + currentMessage.getMessage());
                }

                entered++;

            } else if (option.equals("2")) {

                // Display the messages sent during this application run.
                System.out.print(new Message().printMessages());

            } else if (option.equals("3")) {

                // Display the total number of sent messages before exiting.
                Message report = new Message();

                System.out.println("Total messages sent: "
                        + report.returnTotalMessages());
                System.out.print(report.printMessages());
                System.out.println("Goodbye.");
                break;

            } else if (option.equals("4")) {

                // Open the Part 3 stored messages submenu.
                storedMessagesMenu(input);

            } else {

                System.out.println("Invalid option. Please choose 1, 2, 3 or 4.");
            }
        }

        input.close();
    }

    /** Displays the six stored-message operations required in Part 3. */
    private static void storedMessagesMenu(Scanner input) {

        while (true) {

            // Refresh the stored array so that it reflects the JSON file.
            Message.readStoredMessages();
            Message messages = new Message();

            System.out.println("\nSTORED MESSAGES");
            System.out.println("1. Display sender and recipient of all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search for a message ID");
            System.out.println("4. Search for messages by recipient");
            System.out.println("5. Delete a message using the message hash");
            System.out.println("6. Display a report of all stored messages");
            System.out.println("7. Return to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {

                case "1":
                    System.out.println(messages.displayStoredMessages());
                    break;

                case "2":
                    System.out.println(messages.displayLongestStoredMessage());
                    break;

                case "3":
                    System.out.print("Enter Message ID: ");
                    System.out.println(
                            messages.searchMessageByID(input.nextLine().trim()));
                    break;

                case "4":
                    System.out.print("Enter recipient cellphone number: ");
                    System.out.println(
                            messages.searchMessagesByRecipient(input.nextLine().trim()));
                    break;

                case "5":
                    System.out.print("Enter Message Hash: ");
                    System.out.println(
                            messages.deleteMessageByHash(input.nextLine().trim()));
                    break;

                case "6":
                    System.out.println(messages.displayReport());
                    break;

                case "7":
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
