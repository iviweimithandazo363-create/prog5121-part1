# QuickChat Application

## 1. Project Description

QuickChat is a Java-based messaging application developed using Apache NetBeans and Maven. The application allows users to register, log in, send messages, disregard messages and store messages.

The project was developed to demonstrate programming concepts such as object-oriented programming, string manipulation, arrays, file handling and unit testing.

## 2. Main Features

The QuickChat application includes the following features:

- User registration and login.
- Username and password validation.
- Cellphone number validation.
- Automatic generation of a unique 10-digit message ID.
- Message length validation (maximum of 250 characters).
- Automatic generation of message hashes.
- Sending messages.
- Disregarding messages.
- Storing messages in a JSON file.
- Displaying sent messages.
- Displaying stored messages.
- Searching for messages using a message ID.
- Searching for messages using a recipient cellphone number.
- Finding the longest stored message.
- Deleting stored messages using their message hash.
- Generating a report of stored messages.

## 3. Technologies Used

- Java
- Apache NetBeans IDE
- Maven
- JUnit
- JSON file storage

## 4. System Requirements

To run the QuickChat application, you need:

- Java Development Kit (JDK).
- Apache NetBeans IDE.
- Maven support.
- JUnit dependencies for running unit tests.

## 5. How to Run the Application

1. Open Apache NetBeans.
2. Select **File → Open Project**.
3. Locate and open the QuickChat Maven project.
4. Allow Maven to load the project dependencies.
5. Right-click the project.
6. Select **Clean and Build**.
7. Right-click the project again and select **Run**.
8. Follow the instructions displayed in the console.

## 6. How to Use QuickChat

### Registration and Login
- Enter your first name and last name.
- Create a username and password.
- Enter your cellphone number.
- Log in using your registered details.

### Main Menu

After successfully logging in, the user can select from the following options:

1. Send Messages
2. Display Messages
3. Quit
4. Stored Messages

### Stored Messages Menu

The Stored Messages menu provides the following options:

1. Display the sender and recipient of stored messages.
2. Display the longest stored message.
3. Search for a message using its message ID.
4. Search for messages using a recipient cellphone number.
5. Delete a message using its message hash.
6. Display a report of stored messages.
7. Return to the main menu.

## 7. Data Storage

Stored messages are saved in a JSON file named `messages.json`.

The file contains message information, including:

- Message ID
- Recipient cellphone number
- Message text
- Message hash

The application reads the JSON file to retrieve stored messages.

## 8. Unit Testing

JUnit is used to test the functionality of the messaging application.

The tests cover:

- Populating the sent messages array.
- Populating the disregarded messages array.
- Populating the stored messages array.
- Finding the longest stored message.
- Searching for a message using its ID.
- Searching for messages using a recipient number.
- Deleting messages using a message hash.
- Displaying the stored messages report.

To run the tests, right-click the project in NetBeans and select **Test**.

## 9. Conclusion

QuickChat demonstrates the development of a basic messaging application using Java. The project incorporates user authentication, message validation, message management, arrays, JSON file handling and unit testing. These features demonstrate the practical application of programming concepts in developing a functional messaging system.

**Project:** QuickChat  
**Programming Language:** Java  
**IDE:** Apache NetBeans  
**Build Tool:** Maven
