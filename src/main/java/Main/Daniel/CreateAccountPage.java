package Main.Daniel;

import javafx.application.Application;
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
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.util.Objects;

public class CreateAccountPage extends Application {
    public static void main(String[] args){
        Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
    }

    public void start(Stage stage){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  1600); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

        //Red rectangle header
        Rectangle header = new Rectangle();
        header.setWidth(1480); //1280 width doesnt take up all the horizontal space when full screen
        header.setHeight(150); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        //LibraHub logo
        ImageView logo = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")));
        logo.setImage(image);
        logo.setFitHeight(120);
        logo.setFitWidth(118);
        logo.setLayoutX(8);
        logo.setLayoutY(6);

        //Label for page header
        Label title = new Label("LibraHub");
        title.setLayoutX(175);
        title.setLayoutY(30);
        title.setFont(Font.font(60));
        title.setTextFill(Paint.valueOf("Black"));

        //Account button
        Button account = new Button("Account");
        account.setLayoutX(660);
        account.setLayoutY(41);
        account.setPrefWidth(109);
        account.setPrefHeight(67);
        account.setTextFill(Paint.valueOf("white"));
        account.setStyle("-fx-background-color:  #363732");

        //Catalog button
        Button catalog = new Button("Catalog");
        catalog.setStyle("-fx-background-color: #363732");
        catalog.setTextFill(Paint.valueOf("white"));
        catalog.setPrefWidth(109);
        catalog.setPrefHeight(67);
        catalog.setLayoutX(815);
        catalog.setLayoutY(41);

        //About Us button
        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(970);
        aboutus.setLayoutY(41);

        //Log In label
        Label loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);



        //---Body content for Create Account Page---//

        //Create Account Label
        Label CreateAccountLabel = new Label("Create Account");
        CreateAccountLabel.setLayoutX(500);
        CreateAccountLabel.setLayoutY(170);
        CreateAccountLabel.setFont(Font.font(60));
        CreateAccountLabel.setTextFill(Paint.valueOf("Black"));

        //Create Account description label
        Label CreateAccountDescription = new Label("Create an account below to checkout media materials from the libary");
        CreateAccountDescription.setLayoutX(400);
        CreateAccountDescription.setLayoutY(245);
        CreateAccountDescription.setFont(Font.font("System", FontPosture.ITALIC, 20));
        CreateAccountDescription.setTextFill(Paint.valueOf("#707070"));

        //Email label
        Label emailLabel = new Label("Email");
        emailLabel.setLayoutX(500);
        emailLabel.setLayoutY(310);
        emailLabel.setFont(Font.font(30));
        emailLabel.setTextFill(Paint.valueOf("Black"));

        //Email text field
        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(500);
        emailTextField.setLayoutY(350);
        emailTextField.setPrefWidth(400);
        emailTextField.setPrefHeight(50);
        emailTextField.setFont(Font.font(20));

        //Username label
        Label usernameLabel = new Label("Username");
        usernameLabel.setLayoutX(500);
        usernameLabel.setLayoutY(435);
        usernameLabel.setFont(Font.font(30));
        usernameLabel.setTextFill(Paint.valueOf("Black"));

        //Username text field
        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(500);
        usernameTextField.setLayoutY(473);
        usernameTextField.setPrefWidth(400);
        usernameTextField.setPrefHeight(50);
        usernameTextField.setFont(Font.font(20));

        //Password label
        Label passwordLabel = new Label("Password");
        passwordLabel.setLayoutX(500);
        passwordLabel.setLayoutY(560);
        passwordLabel.setFont(Font.font(30));
        passwordLabel.setTextFill(Paint.valueOf("Black"));

        //Password text field
        TextField passwordTextField = new TextField();
        passwordTextField.setLayoutX(500);
        passwordTextField.setLayoutY(600);
        passwordTextField.setPrefWidth(400);
        passwordTextField.setPrefHeight(50);
        passwordTextField.setFont(Font.font(20));

        //Confirm password label
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.setLayoutX(500);
        confirmPasswordLabel.setLayoutY(685);
        confirmPasswordLabel.setFont(Font.font(30));
        confirmPasswordLabel.setTextFill(Paint.valueOf("Black"));

        //Confirm password text field
        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setLayoutX(500);
        confirmPasswordTextField.setLayoutY(725);
        confirmPasswordTextField.setPrefWidth(400);
        confirmPasswordTextField.setPrefHeight(50);
        confirmPasswordTextField.setFont(Font.font(20));



        //Create account Buttton
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setLayoutX(500);
        createAccountButton.setLayoutY(820);
        createAccountButton.setPrefWidth(400);
        createAccountButton.setPrefHeight(50);
        createAccountButton.setFont(Font.font(30));
        createAccountButton.setTextFill(Paint.valueOf("black"));

        createAccountButton.setStyle("-fx-background-color: #6DA6C5");

        //---End of Body content for Create Account Page---//


        stage.setTitle("Library Management System");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, account, catalog,
                                  aboutus, loginLabel, CreateAccountLabel,CreateAccountDescription,emailLabel,
                                  emailTextField, usernameLabel,usernameTextField, passwordLabel, passwordTextField,
                                  confirmPasswordLabel, confirmPasswordTextField, createAccountButton); //adds header to the root (children are the modules)
        stage.setScene(scene);
        stage.show();
    }
}
