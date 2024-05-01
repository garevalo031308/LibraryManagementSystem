package Main.Chris;

import Main.AccountPage;
import Main.Gabriel.CatalogPage;
import Main.Gabriel.CheckoutPage;
import Main.HomePage;
import Main.Sukeer.LoginPage;
import Main.Transaction;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;

// STRIKE - Look up a user in the library system
// STRIKE - have a table that lists out all users in system
// STRIKE - when double click on user, opens up their account page (maybe as a popup or have a back button)
// STRIKE - when click on user, have another table that shows their transactions
// TODO - Fix sizing

public class UserLookupPage extends Application {
    public static void main(String[] args){
        Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(AccountPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
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
        Image cart = new Image(Objects.requireNonNull(UserLookupPage.class.getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> CheckoutPage.checkoutPage(stage, "4440486"));

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

        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);

        Label userLookup = new Label("LibraHub User Lookup");
        userLookup.setLayoutX(14);
        userLookup.setLayoutY(143);
        userLookup.setFont(Font.font(41));
        userLookup.setUnderline(true);

        Label searchBy = new Label("Search by:");
        searchBy.setLayoutX(28);
        searchBy.setLayoutY(219);
        searchBy.setFont(Font.font(24));

        TextField emailField = new TextField();
        emailField.setPromptText("Enter email address here");
        emailField.setPrefSize(270, 35);
        emailField.setLayoutX(72);
        emailField.setLayoutY(297);

        Label emailLabel = new Label("Email Address:");
        emailLabel.setLayoutX(72);
        emailLabel.setLayoutY(265);
        emailLabel.setFont(Font.font(17));

        TextField idField = new TextField();
        idField.setPromptText("Enter ID here");
        idField.setPrefSize(270, 35);
        idField.setLayoutX(72);
        idField.setLayoutY(384);

        Label idLabel = new Label("ID:");
        idLabel.setLayoutX(72);
        idLabel.setLayoutY(348);
        idLabel.setFont(Font.font(17));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter name here: (First Last)");
        nameField.setPrefSize(270, 35);
        nameField.setLayoutX(72);
        nameField.setLayoutY(483);

        Label nameLabel = new Label("Name:");
        nameLabel.setLayoutX(72);
        nameLabel.setLayoutY(450);
        nameLabel.setFont(Font.font(17));

        Button searchButton2 = new Button("Search");
        searchButton2.setLayoutX(110);
        searchButton2.setLayoutY(545);

        Button resetButton = new Button("Reset");
        resetButton.setLayoutX(236);
        resetButton.setLayoutY(545);

        Label usersLabel = new Label("Users:");
        usersLabel.setLayoutX(486);
        usersLabel.setLayoutY(155);
        usersLabel.setFont(Font.font(24));

        Label transactionsLabel = new Label("User Transactions:");
        transactionsLabel.setLayoutX(72);
        transactionsLabel.setLayoutY(579);
        transactionsLabel.setFont(Font.font(24));

        // ---- User Table ---- //
        TableView<Customer> users = new TableView<>();
        users.setPrefSize(689, 292);
        users.setLayoutX(486);
        users.setLayoutY(202);

        TableColumn<Customer, String> userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());

        TableColumn<Customer, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Customer, String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        ArrayList<Customer> customerData = getAllCustomers("", "", "");
        users.getItems().addAll(customerData);


        users.getColumns().addAll(userIDColumn, firstName, lastName, emailColumn);

        // ---- Transaction Table ---- //
        TableView<Transaction> transactions = new TableView<>();
        transactions.setPrefSize(1107, 263);
        transactions.setLayoutX(72);
        transactions.setLayoutY(623);

        TableColumn<Transaction, String> transactionID = new TableColumn<>("Transaction ID");
        transactionID.setCellValueFactory(cellData -> cellData.getValue().transactionIDProperty());

        TableColumn<Transaction, String> bookID = new TableColumn<>("Book ID");
        bookID.setCellValueFactory(cellData -> cellData.getValue().bookIDProperty());

        TableColumn<Transaction, String> bookTitle = new TableColumn<>("Book Title");
        bookTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Transaction, String> borrowDate = new TableColumn<>("Borrow Date");
        borrowDate.setCellValueFactory(cellData -> cellData.getValue().dateCheckedOutProperty());

        TableColumn<Transaction, String> dueDate = new TableColumn<>("Due Date");
        dueDate.setCellValueFactory(cellData -> cellData.getValue().dateDueProperty());

        TableColumn<Transaction, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        transactions.getColumns().addAll(transactionID, bookID, bookTitle, borrowDate, dueDate, status);

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(userLookup, searchBy, emailField, emailLabel, idField, idLabel, nameField, nameLabel, searchButton2, usersLabel, transactionsLabel);
        root.getChildren().addAll(users, transactions, resetButton);
        stage.setScene(scene);
        stage.show();

        searchButton2.setOnAction(e -> {
            String email = emailField.getText();
            String id = idField.getText();
            String name = nameField.getText();
            ArrayList<Customer> customerDataSearch = getAllCustomers(email, id, name);
            users.getItems().clear();
            users.getItems().addAll(customerDataSearch);
        });

        resetButton.setOnAction(e->{
            users.getItems().clear();
            users.getItems().addAll(customerData);
            transactions.getItems().clear();
        });

        users.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String userID = newSelection.getUserID();
                ArrayList<Transaction> transactionData = getTransactionsForUser(userID);
                transactions.getItems().clear();
                transactions.getItems().addAll(transactionData);
            }
        });
    }

    private ArrayList<Transaction> getTransactionsForUser(String userID) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT t.transaction_id, t.book_id, b.title, t.borrow_date, t.return_date, t.status " +
                    "FROM Transactions t " +
                    "JOIN Books b ON t.book_id = b.id " +
                    "WHERE t.user_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SimpleStringProperty transactionID = new SimpleStringProperty(resultSet.getString("transaction_id"));
                SimpleStringProperty bookID = new SimpleStringProperty(resultSet.getString("book_id"));
                SimpleStringProperty title = new SimpleStringProperty(resultSet.getString("title"));
                SimpleStringProperty dateCheckedOut = new SimpleStringProperty(resultSet.getString("borrow_date"));
                SimpleStringProperty dateDue = new SimpleStringProperty(resultSet.getString("return_date"));
                SimpleStringProperty status = new SimpleStringProperty(resultSet.getString("status"));
                transactions.add(new Transaction(title.get(), "", dateCheckedOut.get(), dateDue.get(), bookID, transactionID, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private ArrayList<Customer> getAllCustomers(String email, String id, String name) {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM Customer WHERE email LIKE ? AND id LIKE ? AND (first_name LIKE ? OR last_name LIKE ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + email + "%");
            statement.setString(2, "%" + id + "%");
            statement.setString(3, "%" + name + "%");
            statement.setString(4, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SimpleStringProperty userID = new SimpleStringProperty(resultSet.getString("id"));
                SimpleStringProperty firstName = new SimpleStringProperty(resultSet.getString("first_name"));
                SimpleStringProperty lastName = new SimpleStringProperty(resultSet.getString("last_name"));
                SimpleStringProperty emailProperty = new SimpleStringProperty(resultSet.getString("email"));
                customers.add(new Customer(userID, firstName, lastName, emailProperty));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}