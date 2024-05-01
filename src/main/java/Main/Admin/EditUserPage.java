package Main.Admin;

import Main.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditUserPage {

    public static void editUserPage(Stage stage, String ID){
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Header.getHeader(stage, root);

        Text addTitle = new Text(482, 186, "Add User");
        addTitle.setFont(Font.font(60));

        Text clickText = new Text(350, 233, "Click the button below to add the user");
        clickText.setFont(Font.font(30));

        TextField idField = new TextField();
        idField.setPrefSize(291, 75);
        idField.setLayoutX(47);
        idField.setLayoutY(317);
        idField.setFont(Font.font(35));
        idField.setText(ID);
        idField.setDisable(true);

        TextField firstNameField = new TextField();
        firstNameField.setPrefSize(500, 67);
        firstNameField.setLayoutX(47);
        firstNameField.setLayoutY(464);
        firstNameField.setFont(Font.font(35));

        TextField lastNameField = new TextField();
        lastNameField.setPrefSize(500, 67);
        lastNameField.setLayoutX(47);
        lastNameField.setLayoutY(606);
        lastNameField.setFont(Font.font(35));

        TextField passwordField = new TextField();
        passwordField.setPrefSize(653, 75);
        passwordField.setLayoutX(581);
        passwordField.setLayoutY(317);
        passwordField.setFont(Font.font(35));

        TextField emailField = new TextField();
        emailField.setPrefSize(653, 75);
        emailField.setLayoutX(581);
        emailField.setLayoutY(464);
        emailField.setFont(Font.font(35));

        Button editButton = new Button("Edit User");
        editButton.setPrefSize(277, 75);
        editButton.setLayoutX(446);
        editButton.setLayoutY(754);
        editButton.setStyle("-fx-background-color:  #A9DDD6;");
        editButton.setFont(Font.font(35));

        Button backButton = new Button("Back");
        backButton.setLayoutX(14);
        backButton.setLayoutY(137);

        Text idText = new Text(47, 297, "ID");
        idText.setFont(Font.font(35));

        Text firstNameText = new Text(47, 444, "First Name");
        firstNameText.setFont(Font.font(35));

        Text lastNameText = new Text(47, 586, "Last Name");
        lastNameText.setFont(Font.font(35));

        Text passwordText = new Text(581, 297, "Password");
        passwordText.setFont(Font.font(35));

        Text emailText = new Text(584, 444, "Email");
        emailText.setFont(Font.font(35));

        populateFields(ID, firstNameField, lastNameField, passwordField, emailField);


        stage.setScene(scene);
        root.getChildren().addAll(addTitle, clickText, idField, firstNameField, lastNameField, passwordField, emailField, editButton, backButton, idText, firstNameText, lastNameText, passwordText, emailText);
        stage.show();

        editButton.setOnAction(e -> {
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newPassword = passwordField.getText();
            String newEmail = emailField.getText();

            updateUserInDatabase(ID, newFirstName, newLastName, newPassword, newEmail);

        });
    }

    public static void populateFields(String id, TextField firstNameField, TextField lastNameField, TextField passwordField, TextField emailField) {
        try (Connection connection = HomePage.getConnection()){
            String SQL = "SELECT * FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                firstNameField.setText(firstName);
                lastNameField.setText(lastName);
                passwordField.setText(password);
                emailField.setText(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateUserInDatabase(String id, String firstName, String lastName, String password, String email) {
        try (Connection connection = HomePage.getConnection()){
            String SQL = "UPDATE user SET first_name = ?, last_name = ?, password = ?, email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
