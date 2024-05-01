package Main.Admin;

import Main.*;
import Main.User.Users;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminPage {

    public static void adminPage(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Header.getHeader(stage, root);

        TableView<Users> users = new TableView<>();
        users.setLayoutX(21);
        users.setLayoutY(142);
        users.setPrefSize(1238, 630);

        TableColumn<Users, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Users, String> firstname = new TableColumn<>("First Name");
        firstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());

        TableColumn<Users, String> lastname = new TableColumn<>("Last Name");
        lastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());

        TableColumn<Users, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<Users, String> password = new TableColumn<>("Password");
        password.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        TableColumn<Users, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        users.getItems().addAll(getAllUsers());

        users.getColumns().addAll(id, firstname, lastname, email, password, type);

        Button addUser = new Button("Add User");
        addUser.setPrefSize(277, 75);
        addUser.setLayoutX(21);
        addUser.setLayoutY(800);
        addUser.setFont(Font.font(35));
        addUser.setStyle("-fx-background-color:  #6DA6C5");

        Button editUser = new Button("Edit User");
        editUser.setPrefSize(277, 75);
        editUser.setLayoutX(524);
        editUser.setLayoutY(800);
        editUser.setFont(Font.font(35));
        editUser.setStyle("-fx-background-color:  #A9DDD6");
        editUser.setDisable(true);

        Button removeUser = new Button("Remove User");
        removeUser.setPrefSize(277.2, 75);
        removeUser.setLayoutX(1000);
        removeUser.setLayoutY(800);
        removeUser.setFont(Font.font(35));
        removeUser.setStyle("-fx-background-color:  #FF5A5F");
        removeUser.setDisable(true);

        

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(users, addUser, editUser, removeUser);
        stage.setScene(scene);
        stage.show();

        addUser.setOnAction(e -> AddUserPage.addUserPage(stage));

        users.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editUser.setDisable(false);
                removeUser.setDisable(false);
                String userid = newSelection.getId();

                editUser.setOnAction(e -> EditUserPage.editUserPage(stage, userid));
                removeUser.setOnAction(e -> {
                    Users selectedUser = users.getSelectionModel().getSelectedItem();
                    if (selectedUser != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to remove this user? This action is irreversible.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK){
                            String userId = selectedUser.getId();
                            String userType = selectedUser.getType();
                            removeUserFromDatabase(userId, userType);
                            users.getItems().remove(selectedUser);
                        }
                    }
                });
            } else {
                editUser.setDisable(true);
                removeUser.setDisable(true);
            }
        });
    }

    private static ArrayList<Users> getAllUsers(){
        ArrayList<Users> users = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()){

            String query = "SELECT * FROM user";

            PreparedStatement statement1 = connection.prepareStatement(query);
            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()){
                StringProperty id = new SimpleStringProperty(resultSet1.getString("id"));
                StringProperty firstname = new SimpleStringProperty(resultSet1.getString("first_name"));
                StringProperty lastname = new SimpleStringProperty(resultSet1.getString("last_name"));
                StringProperty email = new SimpleStringProperty(resultSet1.getString("email"));
                StringProperty password = new SimpleStringProperty(resultSet1.getString("password"));
                StringProperty type = new SimpleStringProperty(resultSet1.getString("role"));

                users.add(new Users(id, firstname, lastname, email, password, type));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return users;
    }

    private static void removeUserFromDatabase(String userId, String userType) {
        try (Connection connection = HomePage.getConnection()){
            String SQL = "DELETE FROM " + userType.toLowerCase() + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
