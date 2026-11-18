/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package za.ac.iie.chatapppart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the QuickChat Part 3 message functions.
 */
public class MessageTest {

    // Reset the message arrays and JSON file before each test.
    @Before
    public void resetMessages() {
        Message.resetForTests();
    }

    // Tests whether the sent messages array is populated correctly.
    @Test
    public void sentMessagesArrayIsPopulated() {
        Message message = new Message(
                "1234567890", "+27834557896",
                "Did you get the cake?", 1);

        message.SentMessage("send");

        assertEquals(1, Message.sentMessages.length);
        assertEquals("Did you get the cake?",
                Message.sentMessages[0].getMessage());
    }

    // Tests whether the disregarded messages array is populated correctly.
    @Test
    public void disregardedMessagesArrayIsPopulated() {
        Message message = new Message(
                "1234567891", "+27834484567",
                "Yohoooo, I am at your gate.", 2);

        message.SentMessage("disregard");

        assertEquals(1, Message.disregardedMessages.length);
    }

    // Tests whether the stored messages array is populated correctly.
    @Test
    public void storedMessagesArrayIsPopulated() {
        Message message = new Message(
                "1234567892", "+27838884567",
                "Where are you? You are late!", 3);

        message.SentMessage("store");

        assertEquals(1, Message.storedMessages.length);
    }

    // Tests whether the longest stored message is returned.
    @Test
    public void longestStoredMessageIsReturned() {
        Message first = new Message(
                "1234567892", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.", 1);

        Message second = new Message(
                "1234567893", "+27838884567",
                "Ok, I am leaving without you.", 2);

        first.SentMessage("store");
        second.SentMessage("store");

        assertEquals(first.getMessage(),
                first.displayLongestStoredMessage());
    }

    // Tests searching for a message using its message ID.
    @Test
    public void searchByMessageIDReturnsMessageAndRecipient() {
        Message message = new Message(
                "1234567890", "+27834557896",
                "It is dinner time!", 4);

        message.SentMessage("store");

        assertTrue(message.searchMessageByID("1234567890")
                .contains("It is dinner time!"));

        assertTrue(message.searchMessageByID("1234567890")
                .contains("+27834557896"));
    }

    // Tests searching for all stored messages belonging to one recipient.
    @Test
    public void searchByRecipientReturnsMatchingStoredMessages() {
        Message first = new Message(
                "1234567892", "+27838884567",
                "Where are you?", 1);

        Message second = new Message(
                "1234567893", "+27838884567",
                "Ok, I am leaving without you.", 2);

        first.SentMessage("store");
        second.SentMessage("store");

        String results = first.searchMessagesByRecipient("+27838884567");

        assertTrue(results.contains("Where are you?"));
        assertTrue(results.contains("Ok, I am leaving without you."));
    }

    // Tests deleting a stored message using its message hash.
    @Test
    public void messageCanBeDeletedUsingHash() {
        Message message = new Message(
                "1234567892", "+27838884567",
                "Where are you?", 1);

        message.SentMessage("store");

        String result = message.deleteMessageByHash(
                message.getMessageHash());

        assertTrue(result.contains("successfully deleted"));
        assertEquals(0, Message.storedMessages.length);
    }

    // Tests whether the report contains the stored message details.
    @Test
    public void reportContainsStoredMessageDetails() {
        Message message = new Message(
                "1234567892", "+27838884567",
                "Where are you?", 1);

        message.SentMessage("store");

        String report = message.displayReport();

        assertTrue(report.contains(message.getMessageHash()));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Where are you?"));
    }
}
