/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package za.ac.iie.chatapppart1;

import java.util.Scanner;

/**
 * Main application for QuickChat.
 */
public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // Register the user.
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

        String registration = login.registerUser(
                username, password, phone);

        System.out.println(registration);

        if (!registration.equals("User registered successfully.")) {
            input.close();
            return;
        }

        // Log in using the registered details.
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

        // Ask how many messages the user wants to enter.
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

        // Display the main menu until the user quits.
        while (true) {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Display messages");
            System.out.println("3. Quit");

            System.out.print("Choose an option: ");
            String option = input.nextLine().trim();

            if (option.equals("1")) {
                if (entered >= messageLimit) {
                    System.out.println(
                            "You have reached the number of messages you selected.");
                    continue;
                }

                System.out.print("Recipient cellphone number: ");
                String recipient = input.nextLine().trim();

                System.out.print("Enter your message: ");
                String text = input.nextLine();

                Message currentMessage = new Message(
                        Message.generateMessageID(),
                        recipient,
                        text,
                        entered);

                System.out.println("Message ID valid: "
                        + currentMessage.checkMessageID());

                System.out.println(currentMessage.checkRecipientCell());
                System.out.println(currentMessage.checkMessageLength());

                // Continue only when the message details are valid.
                if (!currentMessage.isRecipientValid()
                        || !currentMessage.isMessageLengthValid()) {
                    continue;
                }

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
                System.out.println("Coming Soon.");

            } else if (option.equals("3")) {
                Message report = new Message();

                System.out.println("Total messages sent: "
                        + report.returnTotalMessages());

                System.out.print(report.printMessages());
                System.out.println("Goodbye.");
                break;

            } else {
                System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        input.close();
    }
}
