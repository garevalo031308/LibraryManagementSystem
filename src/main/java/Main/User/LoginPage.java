package Main.User;

import Main.Header;
import Main.HomePage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.util.Objects;


public class LoginPage{
    public static void loginPage(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle middle = new Rectangle();//new rectangle at the middle of the page
        middle.setWidth(351); //set width
        middle.setHeight(504); //set height
        middle.setLayoutX(465);
        middle.setLayoutY(196);
        middle.setFill(Paint.valueOf("#a9ddd6"));
        middle.setStroke(Paint.valueOf("#000000"));

        ImageView logo = new ImageView();
        logo.setImage(new Image(Objects.requireNonNull(LoginPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))));
        logo.setFitWidth(109);
        logo.setFitHeight(101);
        logo.setLayoutX(589);
        logo.setLayoutY(196);
        logo.setPreserveRatio(true);

        ImageView leftbanner = new ImageView();
        leftbanner.setImage(new Image(Objects.requireNonNull(LoginPage.class.getResourceAsStream("/Images/LoginPage/banner.jpg"))));
        leftbanner.setFitWidth(745);
        leftbanner.setFitHeight(307);
        leftbanner.setLayoutX(0);
        leftbanner.setLayoutY(265);
        leftbanner.setPreserveRatio(true);

        ImageView rightbanner = new ImageView();
        rightbanner.setImage(new Image(Objects.requireNonNull(LoginPage.class.getResourceAsStream("/Images/LoginPage/banner.jpg"))));
        rightbanner.setFitWidth(745);
        rightbanner.setFitHeight(307);
        rightbanner.setLayoutX(816);
        rightbanner.setLayoutY(265);
        rightbanner.setPreserveRatio(true);

        Label loginTitle = new Label("LibraHub");
        loginTitle.setLayoutX(571);
        loginTitle.setLayoutY(292);
        loginTitle.setFont(Font.font("Bold", 32));

        TextField username = new TextField();
        username.setLayoutX(492);
        username.setLayoutY(361);
        username.setPrefWidth(304);
        username.setPrefHeight(36);
        username.setFont(Font.font(12));
        username.setPromptText("Enter Username Here");

        TextField password = new TextField();
        password.setLayoutX(492);
        password.setLayoutY(430);
        password.setPrefWidth(304);
        password.setPrefHeight(36);
        username.setFont(Font.font(12));
        password.setPromptText("Enter Password Here");

        Button login = new Button("Log in");
        login.setLayoutX(583);
        login.setLayoutY(525);
        login.setPrefWidth(122);
        login.setPrefHeight(36);

        Label question = new Label("Don't have an account?");
        question.setLayoutX(585);
        question.setLayoutY(639);

        Label createAccount = new Label("Click here to create one!");
        createAccount.setLayoutX(583);
        createAccount.setLayoutY(656);
        createAccount.setUnderline(true);

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(leftbanner, rightbanner, middle, loginTitle, username, password, login, createAccount, question, logo); //adds header to the root (children are the modules)
        Header.getHeader(stage, root);
        stage.setScene(scene);
        stage.show();

        login.setOnAction(e -> {
            String role = loginUser(username.getText(), password.getText());
            if (role != null) {
                Header.currentLoggedInUser = username.getText();
                Header.updateLoginLabel();
                switch (role) {
                    case "Admin":
                        // Navigate to admin page
                        Header.currentLoggedInUserRole = "admin";
                        AccountPage.accountPage(stage, username.getText());
                        break;
                    case "Librarian":
                        // Navigate to librarian page
                        Header.currentLoggedInUserRole = "librarian";
                        AccountPage.accountPage(stage, username.getText());
                        break;
                    case "Customer":
                    default:
                        Header.currentLoggedInUserRole = "customer";
                        // Navigate to account page
                        AccountPage.accountPage(stage, username.getText());
                        break;
                }
            } else {
                System.out.println("Login failed");
            }
        });

        createAccount.setOnMouseClicked(e -> {
            CreateAccountPage.createAccountPage(stage);
            Header.updateLoginLabel();
        });

    }

    public static String loginUser(String id, String password) {
        String role = null;

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT role FROM user WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
