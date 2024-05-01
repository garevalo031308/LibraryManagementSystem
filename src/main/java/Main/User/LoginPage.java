package Main.User;

import Main.AboutUsPage;
import Main.HomePage;
import Main.Media.CatalogPage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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
import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;


public class LoginPage{
    public static void loginPage(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(LoginPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
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

        Rectangle middle = new Rectangle();//new rectangle at the middle of the page
        middle.setWidth(351); //set width
        middle.setHeight(504); //set height
        middle.setLayoutX(465);
        middle.setLayoutY(148);
        middle.setFill(Paint.valueOf("#a9ddd6"));

        Label loginTitle = new Label("LibraHub");
        loginTitle.setLayoutX(571);
        loginTitle.setLayoutY(244);
        loginTitle.setFont(Font.font("Bold", 32));

        TextField username = new TextField();
        username.setLayoutX(492);
        username.setLayoutY(313);
        username.setPrefWidth(304);
        username.setPrefHeight(36);
        username.setFont(Font.font(12));
        username.setPromptText("Enter Username Here");

        TextField password = new TextField();
        password.setLayoutX(492);
        password.setLayoutY(382);
        password.setPrefWidth(304);
        password.setPrefHeight(36);
        username.setFont(Font.font(12));
        password.setPromptText("Enter Password Here");

        Button login = new Button("Log in");
        login.setLayoutX(583);
        login.setLayoutY(477);
        login.setPrefWidth(122);
        login.setPrefHeight(36);

        CheckBox remember = new CheckBox("Remember Me");
        remember.setLayoutX(571);
        remember.setLayoutY(555);
        remember.setPrefWidth(Region.USE_COMPUTED_SIZE);
        remember.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Label question = new Label("Don't have an account?");
        question.setLayoutX(585);
        question.setLayoutY(591);

        Label createAccount = new Label("Click here to create one!");
        createAccount.setLayoutX(583);
        createAccount.setLayoutY(608);
        createAccount.setUnderline(true);

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(middle, loginTitle, username, password, login, createAccount, question ); //adds header to the root (children are the modules)
        root.getChildren().addAll(remember);
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);
        stage.setScene(scene);
        stage.show();

        login.setOnAction(e -> {
            if (loginUser(username.getText(), password.getText())) {
                AccountPage.accountPage(stage, username.getText());
            } else {
                System.out.println("Login failed");
            }
        });

        createAccount.setOnMouseClicked(e -> CreateAccountPage.createAccountPage(stage));

    }

    public static boolean loginUser(String id, String password) {
        boolean loginSuccessful = false;

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM customer WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                loginSuccessful = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginSuccessful;
    }
}
