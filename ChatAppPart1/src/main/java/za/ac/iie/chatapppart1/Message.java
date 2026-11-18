/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package za.ac.iie.chatapppart1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a QuickChat message and handles message operations.
 */
public class Message {

    // Stores the unique ID, recipient, message text and hash for this message.
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private String sendChoice;
    private int messageNumber;

    // Counts messages that were successfully sent.
    private static int totalMessages = 0;

    // Lists temporarily hold messages before they are copied into the required arrays.
    private static final List<Message> sentList = new ArrayList<>();
    private static final List<Message> disregardedList = new ArrayList<>();
    private static final List<Message> storedList = new ArrayList<>();

    // Arrays required for the Part 3 message categories and message details.
    public static Message[] sentMessages = new Message[0];
    public static Message[] disregardedMessages = new Message[0];
    public static Message[] storedMessages = new Message[0];
    public static String[] messageHashes = new String[0];
    public static String[] messageIDs = new String[0];

    private static final String FILE_NAME = "messages.json";

    // Creates an empty message object.
    public Message() {
        this(generateMessageID(), "", "", 0);
    }

    // Creates a message using the supplied message details.
    public Message(String messageID, String recipient,
                   String message, int messageNumber) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    // Generates a random ten-digit message ID.
    public static String generateMessageID() {
        long number = ThreadLocalRandom.current()
                .nextLong(1000000000L, 10000000000L);
        return String.valueOf(number);
    }

    // Checks that the message ID contains exactly ten digits.
    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    // Checks whether the recipient cellphone number is valid.
    public String checkRecipientCell() {
        if (isRecipientValid()) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Checks that the recipient uses the required South African international format.
    public boolean isRecipientValid() {
        return recipient != null && recipient.matches("^\\+27\\d{9}$");
    }

    // Returns a message explaining whether the message length is acceptable.
    public String checkMessageLength() {
        if (message == null || message.trim().isEmpty()) {
            return "Please enter a message.";
        }
        if (message.length() <= 250) {
            return "Message ready to send.";
        }
        return "Message exceeds 250 characters by "
                + (message.length() - 250)
                + "; please reduce the size.";
    }

    // Checks whether the message is not empty and does not exceed 250 characters.
    public boolean isMessageLengthValid() {
        return message != null
                && !message.trim().isEmpty()
                && message.length() <= 250;
    }

    // Builds the message hash from the ID, message number and first/last words.
    public String createMessageHash() {
        String idStart = "00";

        if (messageID != null && messageID.length() >= 2) {
            idStart = messageID.substring(0, 2);
        }

        String firstWord = "";
        String lastWord = "";

        if (message != null && !message.trim().isEmpty()) {
            String[] words = message.trim().split("\\s+");
            firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
            lastWord = words[words.length - 1]
                    .replaceAll("[^A-Za-z0-9]", "");
        }

        return (idStart + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Sends, disregards or stores the message according to the selected option.
    public String SentMessage(String choice) {
        if (choice == null) {
            return "Invalid message option.";
        }

        String selected = choice.trim().toLowerCase();

        if (selected.equals("1")) selected = "send";
        if (selected.equals("2")) selected = "disregard";
        if (selected.equals("3")) selected = "store";

        if (!isMessageLengthValid()) return checkMessageLength();
        if (!isRecipientValid()) return checkRecipientCell();

        // Adds the message to the sent messages list.
        if (selected.equals("send")) {
            sendChoice = "Sent";
            sentList.add(this);
            totalMessages++;
            updateArrays();
            return "Message sent successfully.";
        }

        // Adds the message to the disregarded messages list.
        if (selected.equals("disregard")) {
            sendChoice = "Disregarded";
            disregardedList.add(this);
            updateArrays();
            return "Message disregarded.";
        }

        // Adds the message to the stored messages list and saves it as JSON.
        if (selected.equals("store")) {
            sendChoice = "Stored";
            storedList.add(this);
            updateArrays();

            try {
                storeMessagesAsJson();
                return "Message stored successfully.";
            } catch (IOException exception) {
                return "Message could not be stored: "
                        + exception.getMessage();
            }
        }

        return "Invalid message option.";
    }

    // Copies the current lists into the arrays required by the assessment.
    private static void updateArrays() {
        sentMessages = sentList.toArray(new Message[0]);
        disregardedMessages = disregardedList.toArray(new Message[0]);
        storedMessages = storedList.toArray(new Message[0]);

        List<String> hashes = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        // Collects message hashes and IDs from sent messages.
        for (Message item : sentList) {
            hashes.add(item.getMessageHash());
            ids.add(item.getMessageID());
        }

        // Collects message hashes and IDs from disregarded messages.
        for (Message item : disregardedList) {
            hashes.add(item.getMessageHash());
            ids.add(item.getMessageID());
        }

        // Collects message hashes and IDs from stored messages.
        for (Message item : storedList) {
            hashes.add(item.getMessageHash());
            ids.add(item.getMessageID());
        }

        messageHashes = hashes.toArray(new String[0]);
        messageIDs = ids.toArray(new String[0]);
    }

    // Escapes special characters before writing message text to JSON.
    private static String escapeJson(String value) {
        if (value == null) return "";

        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Restores escaped characters after reading message text from JSON.
    private static String unescapeJson(String value) {
        StringBuilder result = new StringBuilder();
        boolean escaping = false;

        for (int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);

            if (escaping) {
                switch (current) {
                    case 'n': result.append('\n'); break;
                    case 'r': result.append('\r'); break;
                    case 't': result.append('\t'); break;
                    default: result.append(current); break;
                }
                escaping = false;
            } else if (current == '\\') {
                escaping = true;
            } else {
                result.append(current);
            }
        }

        if (escaping) result.append('\\');
        return result.toString();
    }

    // Saves all stored messages in the messages.json file.
    private static void storeMessagesAsJson() throws IOException {
        StringBuilder json = new StringBuilder("[\n");

        for (int i = 0; i < storedList.size(); i++) {
            Message item = storedList.get(i);

            json.append("  {\n");
            json.append("    \"messageID\": \"")
                    .append(escapeJson(item.messageID)).append("\",\n");
            json.append("    \"recipient\": \"")
                    .append(escapeJson(item.recipient)).append("\",\n");
            json.append("    \"message\": \"")
                    .append(escapeJson(item.message)).append("\",\n");
            json.append("    \"messageHash\": \"")
                    .append(escapeJson(item.messageHash)).append("\"\n");
            json.append("  }");

            if (i < storedList.size() - 1) json.append(",");
            json.append("\n");
        }

        json.append("]");
        Files.write(Paths.get(FILE_NAME),
                json.toString().getBytes(StandardCharsets.UTF_8));
    }

    // Reads stored messages from the JSON file and populates the stored array.
    public static void readStoredMessages() {
        storedList.clear();

        try {
            if (!Files.exists(Paths.get(FILE_NAME))) {
                updateArrays();
                return;
            }

            String json = new String(
                    Files.readAllBytes(Paths.get(FILE_NAME)),
                    StandardCharsets.UTF_8);

            Pattern objectPattern = Pattern.compile("\\{(.*?)\\}",
                    Pattern.DOTALL);
            Matcher objectMatcher = objectPattern.matcher(json);

            while (objectMatcher.find()) {
                String object = objectMatcher.group(1);

                String id = readJsonValue(object, "messageID");
                String recipient = readJsonValue(object, "recipient");
                String text = readJsonValue(object, "message");

                if (id != null && recipient != null && text != null) {
                    Message item = new Message(
                            id, recipient, text, storedList.size());
                    item.sendChoice = "Stored";
                    storedList.add(item);
                }
            }

        } catch (IOException exception) {
            System.out.println("Could not read stored messages: "
                    + exception.getMessage());
        }

        updateArrays();
    }

    // Extracts one named string value from a JSON message object.
    private static String readJsonValue(String object, String key) {
        Pattern pattern = Pattern.compile(
                "\"" + Pattern.quote(key)
                + "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"");

        Matcher matcher = pattern.matcher(object);

        if (matcher.find()) {
            return unescapeJson(matcher.group(1));
        }

        return null;
    }

    // Displays the sender and recipient; sender details are not present in the supplied data.
    public String displayStoredMessages() {
        if (storedMessages.length == 0) {
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder();

        for (Message item : storedMessages) {
            output.append("Sender: Not specified in the supplied data\n");
            output.append("Recipient: ").append(item.recipient).append("\n");
            output.append("--------------------------------\n");
        }

        return output.toString();
    }

    // Finds and returns the longest message in the stored messages array.
    public String displayLongestStoredMessage() {
        if (storedMessages.length == 0) {
            return "No stored messages found.";
        }

        Message longest = storedMessages[0];

        for (Message item : storedMessages) {
            if (item.message.length() > longest.message.length()) {
                longest = item;
            }
        }

        return longest.message;
    }

    // Searches stored messages by ID and returns the matching recipient and text.
    public String searchMessageByID(String id) {
        for (Message item : storedMessages) {
            if (item.messageID.equals(id)) {
                return "Recipient: " + item.recipient
                        + "\nMessage: " + item.message;
            }
        }

        return "Message ID not found.";
    }

    // Displays every stored message belonging to the specified recipient.
    public String searchMessagesByRecipient(String number) {
        StringBuilder output = new StringBuilder();
        boolean found = false;

        for (Message item : storedMessages) {
            if (item.recipient.equals(number)) {
                output.append(item.message).append("\n");
                found = true;
            }
        }

        if (!found) return "No messages found for this recipient.";
        return output.toString();
    }

    // Deletes a stored message using its unique message hash.
    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < storedList.size(); i++) {
            Message item = storedList.get(i);

            if (item.messageHash.equals(hash)) {
                String deletedMessage = item.message;
                storedList.remove(i);
                updateArrays();

                try {
                    storeMessagesAsJson();
                    return "Message: \"" + deletedMessage
                            + "\" successfully deleted.";
                } catch (IOException exception) {
                    return "Message deleted from memory but could not update file.";
                }
            }
        }

        return "Message hash not found.";
    }

    // Produces a report containing the full details of stored messages.
    public String displayReport() {
        StringBuilder output = new StringBuilder();

        if (storedMessages.length == 0) {
            return "No stored messages found.";
        }

        for (Message item : storedMessages) {
            output.append("Message ID: ").append(item.messageID).append("\n");
            output.append("Message Hash: ").append(item.messageHash).append("\n");
            output.append("Recipient: ").append(item.recipient).append("\n");
            output.append("Message: ").append(item.message).append("\n");
            output.append("--------------------------------\n");
        }

        return output.toString();
    }

    // Displays all messages that have been sent.
    public String printMessages() {
        StringBuilder output = new StringBuilder();

        if (sentMessages.length == 0) {
            return "No messages have been sent.\n";
        }

        for (Message item : sentMessages) {
            output.append(item.message).append("\n");
        }

        return output.toString();
    }

    // Returns the total number of messages sent.
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Returns the message ID.
    public String getMessageID() {
        return messageID;
    }

    // Returns the recipient cellphone number.
    public String getRecipient() {
        return recipient;
    }

    // Returns the message text.
    public String getMessage() {
        return message;
    }

    // Returns the message hash.
    public String getMessageHash() {
        return messageHash;
    }

    // Returns the selected message action.
    public String getSendChoice() {
        return sendChoice;
    }

    // Clears message data so that unit tests do not affect one another.
    public static void resetForTests() {
        sentList.clear();
        disregardedList.clear();
        storedList.clear();
        totalMessages = 0;

        try {
            Files.deleteIfExists(Paths.get(FILE_NAME));
        } catch (IOException exception) {
            // The in-memory test data is still cleared if the file cannot be deleted.
        }

        updateArrays();
    }
}
