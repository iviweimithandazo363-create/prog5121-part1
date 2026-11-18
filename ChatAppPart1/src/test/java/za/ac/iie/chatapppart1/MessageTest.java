/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package za.ac.iie.chatapppart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void resetMessages() {
        Message.resetForTests();
    }

    @Test
    public void messageIDHasTenDigits() {
        Message message = new Message(
                "1234567890", "+27718693002", "Hello", 0);

        assertTrue(message.checkMessageID());
    }

    @Test
    public void validRecipientIsAccepted() {
        Message message = new Message(
                "1234567890", "+27718693002", "Hello", 0);

        assertTrue(message.isRecipientValid());
    }

    @Test
    public void invalidRecipientIsRejected() {
        Message message = new Message(
                "1234567890", "0718693002", "Hello", 0);

        assertFalse(message.isRecipientValid());
    }

    @Test
    public void messageUnder250CharactersIsAccepted() {
        Message message = new Message(
                "1234567890", "+27718693002", "Hello", 0);

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void messageOver250CharactersIsRejected() {
        StringBuilder longText = new StringBuilder();

        for (int i = 0; i < 251; i++) {
            longText.append('A');
        }

        Message message = new Message(
                "1234567890",
                "+27718693002",
                longText.toString(),
                0);

        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength());
    }

    @Test
    public void messageHashMatchesExample() {
        Message message = new Message(
                "0012345678",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0);

        assertEquals("00:0:HITONIGHT", message.getMessageHash());
    }

    @Test
    public void sendingMessageIncreasesTotal() {
        Message message = new Message(
                "1234567890", "+27718693002", "Hello there", 0);

        assertEquals(
                "Message sent successfully.",
                message.SentMessage("send"));

        assertEquals(1, message.returnTotalMessages());
    }

    @Test
    public void disregardingDoesNotIncreaseTotal() {
        Message message = new Message(
                "1234567890", "+27718693002", "Hello there", 0);

        assertEquals(
                "Message disregarded.",
                message.SentMessage("disregard"));

        assertEquals(0, message.returnTotalMessages());
    }
}

    @BeforeEach
    public void setUp() {
        Message.resetForTests();
    }

    @Test
    public void testSentMessagesArray() {
        Message message1 = new Message(
                "1234567890", "+27834557896",
                "Did you get the cake?", 1);

        message1.SentMessage("send");

        assertEquals(1, Message.sentMessages.length);
        assertEquals("Did you get the cake?",
                Message.sentMessages[0].getMessage());
    }

    @Test
    public void testDisregardedMessagesArray() {
        Message message = new Message(
                "1234567891", "+27834484567",
                "Yohoooo, I am at your gate.", 2);

        message.SentMessage("disregard");

        assertEquals(1, Message.disregardedMessages.length);
    }

    @Test
    public void testStoredMessagesArray() {
        Message message = new Message(
                "1234567892", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                3);

        message.SentMessage("store");

        assertEquals(1, Message.storedMessages.length);
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "1234567890", "+27834557896",
                "Did you get the cake?", 1);

        assertEquals("12:1:DIDCAKE",
                message.getMessageHash());
    }

    @Test
    public void testMessageID() {
        Message message = new Message(
                "1234567890", "+27834557896",
                "Did you get the cake?", 1);

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testMessageLength() {
        Message message = new Message(
                "1234567890", "+27834557896",
                "Did you get the cake?", 1);

        assertTrue(message.isMessageLengthValid());
    }

    @Test
    public void testSearchByRecipient() {
        Message message = new Message(
                "1234567892", "+27838884567",
                "Where are you? You are late!", 1);

        message.SentMessage("store");

        Message.readStoredMessages();

        assertTrue(message.searchMessagesByRecipient("+27838884567")
                .contains("Where are you? You are late!"));
    }

    @Test
    public void testLongestStoredMessage() {
        Message message1 = new Message(
                "1234567892", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                1);

        Message message2 = new Message(
                "1234567893", "+27838884567",
                "Ok, I am leaving without you.", 2);

        message1.SentMessage("store");
        message2.SentMessage("store");

        Message.readStoredMessages();

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                message1.displayLongestStoredMessage());
    }
}
