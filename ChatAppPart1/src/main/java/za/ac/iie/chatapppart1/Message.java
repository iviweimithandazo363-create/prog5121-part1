/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.chatapppart1;

/**
 *
 * @author Student
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageNumber;
    private String sendChoice;

    private static int totalMessages = 0;

    private static final List<Message> sentMessages =
            new ArrayList<>();

    private static final List<Message> storedMessages =
            new ArrayList<>();

    public Message(
            String messageID,
            String recipient,
            String message,
            int messageNumber) {

        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    public static String generateMessageID() {
        long id = ThreadLocalRandom.current().nextLong(
                1_000_000_000L,
                10_000_000_000L
        );

        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID != null
                && messageID.length() <= 10
                && messageID.matches("\\d+");
    }

    public String checkRecipientCell() {
        if (recipient != null
                && recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (message == null) {
            return "Please enter a message.";
        }

        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = message.length() - 250;

        return "Message exceeds 250 characters by "
                + excess
                + "; please reduce the size.";
    }

    public String createMessageHash() {
        if (messageID == null || message == null
                || message.trim().isEmpty()) {
            return "";
        }

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""
        );

        String lastWord = words[words.length - 1].replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""
        );

        String firstTwoDigits = messageID.length() >= 2
                ? messageID.substring(0, 2)
                : messageID;

        messageHash = (firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();

        return messageHash;
    }

    public void setSendChoice(String sendChoice) {
        this.sendChoice = sendChoice;
    }

    public String SentMessage() {

        if (message == null || message.length() > 250) {
            return checkMessageLength();
        }

        if (sendChoice == null) {
            return "Please select a message option.";
        }

        switch (sendChoice.toLowerCase()) {

            case "send":

                if (!checkRecipientCell().equals(
                        "Cell phone number successfully captured.")) {
                    return checkRecipientCell();
                }

                sentMessages.add(this);
                totalMessages++;

                return "Message successfully sent.";

            case "disregard":

                return "Press 0 to delete the message.";

            case "store":

                if (!checkRecipientCell().equals(
                        "Cell phone number successfully captured.")) {
                    return checkRecipientCell();
                }

                storedMessages.add(this);

                try {
                    storeMessage();
                    return "Message successfully stored.";
                } catch (IOException e) {
                    return "Message could not be stored.";
                }

            default:

                return "Invalid message option.";
        }
    }

    public void storeMessage() throws IOException {

        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < storedMessages.size(); i++) {

            Message current = storedMessages.get(i);

            json.append("  {\n");
            json.append("    \"messageID\": \"")
                    .append(escapeJson(current.messageID))
                    .append("\",\n");
            json.append("    \"messageHash\": \"")
                    .append(escapeJson(current.messageHash))
                    .append("\",\n");
            json.append("    \"recipient\": \"")
                    .append(escapeJson(current.recipient))
                    .append("\",\n");
            json.append("    \"message\": \"")
                    .append(escapeJson(current.message))
                    .append("\"\n");
            json.append("  }");

            if (i < storedMessages.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]\n");

        Files.writeString(
                Path.of("messages.json"),
                json.toString(),
                StandardCharsets.UTF_8
        );
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public String printMessages() {

        if (sentMessages.isEmpty()) {
            return "No messages have been sent.";
        }

        StringBuilder output = new StringBuilder();

        for (Message current : sentMessages) {
            output.append("Message ID: ")
                    .append(current.messageID)
                    .append("\n");

            output.append("Message Hash: ")
                    .append(current.messageHash)
                    .append("\n");

            output.append("Recipient: ")
                    .append(current.recipient)
                    .append("\n");

            output.append("Message: ")
                    .append(current.message)
                    .append("\n\n");
        }

        return output.toString();
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}