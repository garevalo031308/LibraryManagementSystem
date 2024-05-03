CSC 445 Software Engineering Project

# Installation Guide: Library Management System Application

## Prerequisites
- Java Development Kit (JDK) installed on your system
- MySQL Database server installed and running
- Git installed (optional, for cloning the repository)
- Scene Builder for JavaFX (optional, for GUI design)

## Steps to Install and Run the Application

1. **Clone the Repository (Optional)**
    - If you have Git installed and want to clone the repository, use the following command:
      ```
      git clone https://github.com/garevalo031308/LibraryMangementSystem
      ```
    - If you prefer, you can download the repository as a ZIP file from GitHub and extract it to a local folder.

2. **Set Up the Database**
    - Ensure that your MySQL Database server is running.
    - Use a MySQL client (e.g., MySQL Workbench) to create a new database for the library management system.

3. **Configure the Database Connection**
    - Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, VSCode).
    - Locate the database configuration file (e.g., `database.properties`).
    - Modify the configuration settings (e.g., database URL, username, password) to match your MySQL database.

4. **Build the Project**
    - Use your IDE to build the project. This will compile the Java code and generate the necessary class files.

5. **Create the JAR File**
    - In your IDE, find the option to build a JAR file (often found in the "Build" or "Export" menu).
    - Follow the prompts to create a JAR file for your application.

6. **Run the Application**
    - Open a terminal or command prompt.
    - Navigate to the directory containing the JAR file.
    - Run the JAR file using the following command:
      ```
      java -jar LibraHub.jar
      ```
    - The application should start, and you should see the main interface.

7. **Login and Use the Application**
    - Use the login credentials provided in the application or create a new account if necessary.
    - Explore the features of the library management system, such as adding books, managing users, and checking out books.



