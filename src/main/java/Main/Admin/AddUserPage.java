package Main.Admin;

import Main.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class AddUserPage {

    public static void addUserPage(Stage stage){
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
        idField.setDisable(true);
        idField.setText(createRandomID());

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

        ToggleGroup userType = new ToggleGroup();

        RadioButton customerRadioButton = new RadioButton("Customer");
        customerRadioButton.setLayoutX(584);
        customerRadioButton.setLayoutY(621);
        customerRadioButton.setFont(Font.font(30));
        customerRadioButton.setToggleGroup(userType);

        RadioButton adminRadioButton = new RadioButton("Admin");
        adminRadioButton.setLayoutX(827);
        adminRadioButton.setLayoutY(621);
        adminRadioButton.setFont(Font.font(30));
        adminRadioButton.setToggleGroup(userType);

        RadioButton librarianRadioButton = new RadioButton("Librarian");
        librarianRadioButton.setLayoutX(1055);
        librarianRadioButton.setLayoutY(621);
        librarianRadioButton.setFont(Font.font(30));
        librarianRadioButton.setToggleGroup(userType);

        userType.selectToggle(customerRadioButton);

        Button addButton = new Button("Add User");
        addButton.setPrefSize(277, 75);
        addButton.setLayoutX(446);
        addButton.setLayoutY(754);
        addButton.setStyle("-fx-background-color: #6DA6C5;");
        addButton.setFont(Font.font(35));

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

        Text typeText = new Text(584, 586, "Type");
        typeText.setFont(Font.font(35));

        stage.setScene(scene);
        stage.setTitle("Library Management System - Add User Page");
        stage.getIcons().add(new Image(Objects.requireNonNull(AddUserPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))));
        root.getChildren().addAll(addTitle, clickText, idField, firstNameField, lastNameField, passwordField, emailField, customerRadioButton, adminRadioButton, librarianRadioButton, addButton, backButton, idText, firstNameText, lastNameText, passwordText, emailText, typeText);
        stage.show();

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            RadioButton selectedRadioButton = (RadioButton) userType.getSelectedToggle();
            String type = selectedRadioButton.getText();

            addUserToDatabase(id, firstName, lastName, password, email, type);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User was successfully added!");
            alert.showAndWait();
            AdminPage.adminPage(stage);
        });
    }

    private static String createRandomID(){
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            Random rand = new Random();
            id.append(rand.nextInt(0, 9));
        }
        if (checkUserID(id.toString())){
            return createRandomID();
        }
        return id.toString();
    }

    public static void addUserToDatabase(String id, String firstName, String lastName, String password, String email, String type) {
        try (Connection connection = HomePage.getConnection()){
            String SQL = "INSERT INTO user (id, first_name, last_name, password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.setString(6, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkUserID(String id){
        try (Connection connection = HomePage.getConnection()){

            String SQL = "SELECT COUNT(*) FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int count = resultSet.getInt(1);
                if (count > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
