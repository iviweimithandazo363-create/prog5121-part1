# PROG5121-POE
## ChatAppPart 1
This project is the first part of a simple a simple Java chat application.
Part 1 focuses on user registration, input validation and login functionality

## Project Features
The application includes:
- User registration
- Username validation
- Password complexity validation
- South African cellphone number validation
- User login
- Login status message
- JUnit testing

## Validation requirements
### Username
The username must:
- Contain an underscore
- Not be more than 5 characters long

### Password
The password must be atleast:
- Be at least 8 characters long
- Contain a capital letter
- Contain a number
- Contain a special character

## Classes
### Login
The Login class handles:
- Username validation
- Password validation
- Cellphone validation
- User registration
# QuickChat Application – PROG5121 POE Part 2

## 1. Project Overview

QuickChat is a Java console-based chat application developed for the PROG5121 Programming 1A Portfolio of Evidence (POE) Part 2. The application builds on the registration and login functionality from Part 1 and introduces basic message management features.

The application allows users to register, log in, enter messages, validate recipient cellphone numbers, generate message IDs and hashes, send messages, disregard messages, and store messages in JSON format.

## 2. Technologies Used

- Java
- Apache NetBeans IDE
- Maven
- JUnit for unit testing
- GitHub for version control
- GitHub Actions for automated testing
- JSON for storing messages

## 3. Application Features

### Registration and Login
- Allows users to register using a username, password and cellphone number.
- Validates the username format.
- Checks password complexity.
- Validates the cellphone number.
- Allows registered users to log in.

### Message Management
- Allows users to specify how many messages they want to enter.
- Generates a ten-digit message ID.
- Creates a message hash using the message ID, message number, first word and last word.
- Validates recipient cellphone numbers.
- Checks that messages do not exceed 250 characters.
- Allows users to send, disregard or store messages.
- Displays details of sent messages.
- Displays the total number of messages sent.

### JSON Storage
Messages selected for storage are written to a JSON file named `messages.json`.

### Unit Testing
JUnit tests are included to test login validation and message functionality.

### Continuous Integration
GitHub Actions is used to automate the execution of Maven tests when code is pushed to GitHub or a pull request is created.

## 4. Project Structure

The project uses a standard Maven directory structure.

ChatAppPart1/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── za/
│   │           └── ac/
│   │               └── iie/
│   │                   └── chatapppart1/
│   │                       ├── MainApp.java
│   │                       ├── Login.java
│   │                       └── Message.java
│   └── test/
│       └── java/
│           └── za/
│               └── ac/
│                   └── iie/
│                       └── chatapppart1/
│                           ├── LoginTest.java
│                           └── MessageTest.java
├── .github/
│   └── workflows/
│       └── maven.yml
├── pom.xml
└── README.md


## 5. How to Run the Application

1. Clone or download the project repository from GitHub.
2. Open Apache NetBeans.
3. Select **File → Open Project** and open the `ChatAppPart1` project.
4. Allow Maven to load the project dependencies.
5. Run `MainApp.java` or run the project using NetBeans.
6. Follow the console prompts to register, log in and access the QuickChat menu.

## 6. How to Use QuickChat

1. Enter your registration details.
2. Log in using your registered username and password.
3. Specify the number of messages you want to enter.
4. Select an option from the main menu.
5. To create a message, enter the recipient cellphone number and message text.
6. Choose whether to send, disregard or store the message.
7. View the message details displayed by the application.
8. Select the quit option to exit and display the total number of messages sent.

## 7. Running Unit Tests

To run the automated tests in NetBeans:

1. Right-click the project.
2. Select **Test**.
3. Check the test results to confirm whether the tests pass.

Alternatively, open a terminal in the project folder and run:

```bash
mvn test
```

## 8. GitHub Actions

The project includes a GitHub Actions workflow located at:

`.github/workflows/maven.yml`

The workflow checks out the repository, sets up Java, and runs the Maven tests automatically when changes are pushed or a pull request is created.

The workflow results can be viewed under the **Actions** tab of the GitHub repository.

## 9. Message Validation

The application checks the following message requirements:

- Message IDs must contain ten digits.
- Recipient cellphone numbers must follow the implemented South African international format.
- Messages must not be empty.
- Messages must contain no more than 250 characters.
- Message hashes are generated using the message ID, message number, first word and last word.

## 10. Conclusion

The QuickChat application demonstrates the use of Java programming, object-oriented principles, input validation, methods, unit testing, JSON storage, Maven and GitHub Actions. It extends the Part 1 registration and login functionality by introducing message handling and automated testing.

---

**Module:** PROG5121 – Programming 1A  
**Assessment:** Portfolio of Evidence (POE) – Part 2  
**Project:** QuickChat Application
- 


### Cellphone number
The cellphone number must:
- Start with +27
- Be no more than 12 characters long

