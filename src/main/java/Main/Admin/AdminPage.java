package Main.Admin;

import Main.*;
import Main.Media.CatalogPage;
import Main.User.AccountPage;
import Main.User.CheckoutPage;
import Main.User.LoginPage;
import Main.User.Users;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static Main.HomePage.currentLoggedInUser;

public class AdminPage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(AdminPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
        logo.setImage(logoimg); //set image
        logo.setFitHeight(124);
        logo.setFitWidth(122);
        logo.setLayoutX(8);
        logo.setLayoutY(6);

        Label title = new Label("LibraHub");
        title.setLayoutX(175);
        title.setLayoutY(15);
        title.setFont(Font.font(80));

        Button account = new Button("Account");
        account.setLayoutX(553);
        account.setLayoutY(41);
        account.setPrefWidth(109);
        account.setPrefHeight(67);
        account.setTextFill(Paint.valueOf("white"));
        account.setStyle("-fx-background-color:  #363732");
        account.setOnAction(e->{
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                AccountPage.accountPage(stage, currentLoggedInUser);
            }
        });

        Button catalog = new Button("Catalog");
        catalog.setStyle("-fx-background-color: #363732");
        catalog.setTextFill(Paint.valueOf("white"));
        catalog.setPrefWidth(109);
        catalog.setPrefHeight(67);
        catalog.setLayoutX(708);
        catalog.setLayoutY(41);
        catalog.setOnAction(e-> CatalogPage.catalogPage(stage, ""));

        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(863);
        aboutus.setLayoutY(41);
        aboutus.setOnAction(e-> AboutUsPage.aboutUsPage(stage));

        Label loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);
        loginLabel.setOnMouseClicked(e-> LoginPage.loginPage(stage));

        ImageView cartimage = new ImageView();
        Image cart = new Image(Objects.requireNonNull(AccountPage.class.getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> {
            CheckoutPage.checkoutPage(stage, "4440486");
        });

        TextField searchBar = new TextField();
        searchBar.setPromptText("Type a title, author, etc. here");
        searchBar.setPrefSize(226, 26);
        searchBar.setLayoutX(991);
        searchBar.setLayoutY(83);

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(1217);
        searchButton.setLayoutY(83);
        searchButton.setOnAction(e -> {
            String searchQuery = searchBar.getText();
            CatalogPage.catalogPage(stage, searchQuery);
        });

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
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);
        root.getChildren().addAll(users, addUser, editUser, removeUser);
        stage.setScene(scene);
        stage.show();

        addUser.setOnAction(e -> AddUserPage.addUserPage(stage));

        users.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editUser.setDisable(false);
                removeUser.setDisable(false);
                String userid = newSelection.getId();
                String usertype = newSelection.getType();

                editUser.setOnAction(e -> EditUserPage.editUserPage(stage, userid, usertype));
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

    private ArrayList<Users> getAllUsers(){
        ArrayList<Users> users = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()){

            String customerSQL = "SELECT * FROM customer";
            String adminSQL = "SELECT * FROM admin";
            String librarianSQL = "SELECT * FROM librarian";

            PreparedStatement statement1 = connection.prepareStatement(customerSQL);
            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()){
                StringProperty id = new SimpleStringProperty(resultSet1.getString("id"));
                StringProperty firstname = new SimpleStringProperty(resultSet1.getString("first_name"));
                StringProperty lastname = new SimpleStringProperty(resultSet1.getString("last_name"));
                StringProperty email = new SimpleStringProperty(resultSet1.getString("email"));
                StringProperty password = new SimpleStringProperty(resultSet1.getString("password"));
                StringProperty type = new SimpleStringProperty("Customer");

                users.add(new Users(id, firstname, lastname, email, password, type));
            }

            PreparedStatement statement2 = connection.prepareStatement(adminSQL);
            ResultSet resultSet2 = statement2.executeQuery();

            while (resultSet2.next()){
                StringProperty id = new SimpleStringProperty(resultSet2.getString("id"));
                StringProperty firstname = new SimpleStringProperty(resultSet2.getString("first_name"));
                StringProperty lastname = new SimpleStringProperty(resultSet2.getString("last_name"));
                StringProperty email = new SimpleStringProperty(resultSet2.getString("email"));
                StringProperty password = new SimpleStringProperty(resultSet2.getString("password"));
                StringProperty type = new SimpleStringProperty("Admin");

                users.add(new Users(id, firstname, lastname, email, password, type));
            }

            PreparedStatement statement3 = connection.prepareStatement(librarianSQL);
            ResultSet resultSet3 = statement3.executeQuery();

            while (resultSet3.next()){
                StringProperty id = new SimpleStringProperty(resultSet3.getString("id"));
                StringProperty firstname = new SimpleStringProperty(resultSet3.getString("first_name"));
                StringProperty lastname = new SimpleStringProperty(resultSet3.getString("last_name"));
                StringProperty email = new SimpleStringProperty(resultSet3.getString("email"));
                StringProperty password = new SimpleStringProperty(resultSet3.getString("password"));
                StringProperty type = new SimpleStringProperty("Librarian");

                users.add(new Users(id, firstname, lastname, email, password, type));
            }


        } catch (SQLException e){
            e.printStackTrace();
        }


        return users;
    }

    private void removeUserFromDatabase(String userId, String userType) {
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
