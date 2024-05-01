package Main.Admin;

import Main.*;
import Main.Media.CatalogPage;
import Main.User.AccountPage;
import Main.User.CheckoutPage;
import Main.User.LoginPage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import static Main.HomePage.currentLoggedInUser;

public class AddUserPage {

    public static void addUserPage(Stage stage){
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
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);
        root.getChildren().addAll(addTitle, clickText, idField, firstNameField, lastNameField, passwordField, emailField, customerRadioButton, adminRadioButton, librarianRadioButton, addButton, backButton, idText, firstNameText, lastNameText, passwordText, emailText, typeText);
        stage.show();


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

    private static boolean checkUserID(String id){
        try (Connection connection = HomePage.getConnection()){
            String[] tables = {"customer", "admin", "librarian"};
            for (String table : tables) {
                String SQL = "SELECT COUNT(*) FROM " + table + " WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
