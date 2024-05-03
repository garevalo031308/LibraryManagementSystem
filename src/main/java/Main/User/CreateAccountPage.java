package Main.User;

import Main.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class CreateAccountPage {
    public static void createAccountPage(Stage stage){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

        Header.getHeader(stage, root);



        //---Body content for Create Account Page---//

        //Create Account Label
        Label CreateAccountLabel = new Label("Create Account");
        CreateAccountLabel.setLayoutX(403);
        CreateAccountLabel.setLayoutY(135);
        CreateAccountLabel.setFont(Font.font(60));
        CreateAccountLabel.setTextFill(Paint.valueOf("Black"));

        //Create Account description label
        Label CreateAccountDescription = new Label("Create an account below to checkout media materials from the libary");
        CreateAccountDescription.setLayoutX(135);
        CreateAccountDescription.setLayoutY(212);
        CreateAccountDescription.setFont(Font.font("System", FontPosture.ITALIC, 30));
        CreateAccountDescription.setTextFill(Paint.valueOf("#707070"));

        //Email label
        Label emailLabel = new Label("Email");
        emailLabel.setLayoutX(86);
        emailLabel.setLayoutY(260);
        emailLabel.setFont(Font.font(30));
        emailLabel.setTextFill(Paint.valueOf("Black"));

        //Email text field
        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(86);
        emailTextField.setLayoutY(314);
        emailTextField.setPrefWidth(500);
        emailTextField.setPrefHeight(75);
        emailTextField.setFont(Font.font(35));

        //Username label
        Label usernameLabel = new Label("ID: ");
        usernameLabel.setLayoutX(640);
        usernameLabel.setLayoutY(260);
        usernameLabel.setFont(Font.font(30));
        usernameLabel.setTextFill(Paint.valueOf("Black"));

        //Username text field
        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(640);
        usernameTextField.setLayoutY(314);
        usernameTextField.setPrefWidth(500);
        usernameTextField.setPrefHeight(75);
        usernameTextField.setFont(Font.font(35));
        usernameTextField.setText(createRandomID());
        usernameTextField.setEditable(false);
        usernameTextField.setDisable(true);

        //Password label
        Label passwordLabel = new Label("Password");
        passwordLabel.setLayoutX(86);
        passwordLabel.setLayoutY(411);
        passwordLabel.setFont(Font.font(30));
        passwordLabel.setTextFill(Paint.valueOf("Black"));

        //Password text field
        TextField passwordTextField = new TextField();
        passwordTextField.setLayoutX(86);
        passwordTextField.setLayoutY(465);
        passwordTextField.setPrefWidth(500);
        passwordTextField.setPrefHeight(75);
        passwordTextField.setFont(Font.font(35));

        //Confirm password label
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.setLayoutX(86);
        confirmPasswordLabel.setLayoutY(576);
        confirmPasswordLabel.setFont(Font.font(30));
        confirmPasswordLabel.setTextFill(Paint.valueOf("Black"));

        //Confirm password text field
        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setLayoutX(86);
        confirmPasswordTextField.setLayoutY(630);
        confirmPasswordTextField.setPrefWidth(500);
        confirmPasswordTextField.setPrefHeight(75);
        confirmPasswordTextField.setFont(Font.font(35));

        // First Name Label
        Label firstNameLabel = new Label("First Name");
        firstNameLabel.setLayoutX(640);
        firstNameLabel.setLayoutY(411);
        firstNameLabel.setFont(Font.font(30));
        firstNameLabel.setTextFill(Paint.valueOf("Black"));

        // First Name Text Field
        TextField firstNameTextField = new TextField();
        firstNameTextField.setLayoutX(640);
        firstNameTextField.setLayoutY(465);
        firstNameTextField.setPrefWidth(500);
        firstNameTextField.setPrefHeight(75);
        firstNameTextField.setFont(Font.font(35));

        // Last Name Label
        Label lastNameLabel = new Label("Last Name");
        lastNameLabel.setLayoutX(640);
        lastNameLabel.setLayoutY(576);
        lastNameLabel.setFont(Font.font(30));
        lastNameLabel.setTextFill(Paint.valueOf("Black"));

        // Last Name Text Field
        TextField lastNameTextField = new TextField();
        lastNameTextField.setLayoutX(640);
        lastNameTextField.setLayoutY(630);
        lastNameTextField.setPrefWidth(500);
        lastNameTextField.setPrefHeight(75);
        lastNameTextField.setFont(Font.font(35));


        //Create account Buttton
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setLayoutX(424);
        createAccountButton.setLayoutY(767);
        createAccountButton.setPrefWidth(400);
        createAccountButton.setPrefHeight(50);
        createAccountButton.setFont(Font.font(30));
        createAccountButton.setTextFill(Paint.valueOf("black"));

        createAccountButton.setStyle("-fx-background-color: #6DA6C5");

        //---End of Body content for Create Account Page---//


        stage.setTitle("Library Management System - Create Account Page");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(CreateAccountPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))));
        root.getChildren().addAll(CreateAccountLabel,CreateAccountDescription,emailLabel,
                                  emailTextField, usernameLabel,usernameTextField, passwordLabel, passwordTextField,
                                  confirmPasswordLabel, confirmPasswordTextField, createAccountButton, lastNameTextField); //adds header to the root (children are the modules)
        root.getChildren().addAll(firstNameLabel, firstNameTextField, lastNameLabel);
        stage.setScene(scene);
        stage.show();

        createAccountButton.setOnAction(e -> {
            String email = emailTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();

            if (!password.equals(confirmPassword)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setHeaderText(null);
                alert.setContentText("The passwords you entered do not match. Please try again.");
                alert.showAndWait();
            } else {
                addCustomerToDatabase(email, username, password, firstName, lastName);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Account Created");
                alert.setHeaderText(null);
                alert.setContentText("Your account has been created successfully.");
                alert.showAndWait();
                Header.currentLoggedInUser = username;
                Header.currentLoggedInUserRole = "customer";
                AccountPage.accountPage(stage, username);
            }
        });
    }

    private static void addCustomerToDatabase(String email, String username, String password, String firstName, String lastName){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "INSERT INTO user (email, id, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, "Customer");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String createRandomID(){
        // This will create a random ID for the user
        StringBuilder ID = new StringBuilder();
        for(int i = 0; i < 7; i++){
            Random rand = new Random();
            ID.append(rand.nextInt(0,9));
        }
        if (checkUserID(ID.toString())){
            return createRandomID();
        }
        return ID.toString();
    }

    private static boolean checkUserID(String userID){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT COUNT(*) FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
