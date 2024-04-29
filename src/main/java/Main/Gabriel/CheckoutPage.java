package Main.Gabriel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class CheckoutPage extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 531,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set
        stage.setResizable(false);
        stage.setFullScreen(false);

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(531); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
        logo.setImage(img); //set image
        logo.setFitHeight(124);
        logo.setFitWidth(122);
        logo.setLayoutX(8);
        logo.setLayoutY(6);

        Label title = new Label("LibraHub");
        title.setLayoutX(175);
        title.setLayoutY(15);
        title.setFont(Font.font(80));

        Label shoppingCartLabel = new Label("Shopping Cart:");
        shoppingCartLabel.setLayoutX(18.0);
        shoppingCartLabel.setLayoutY(132.0);
        shoppingCartLabel.setFont(Font.font(34.0));

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setLayoutX(42.0);
        checkoutButton.setLayoutY(778.0);
        checkoutButton.setPrefHeight(50.0);
        checkoutButton.setPrefWidth(176.0);

        ImageView book1 = new ImageView();
        Image book1Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/Dune.jpg")));
        book1.setImage(book1Img);
        book1.setFitHeight(150);
        book1.setFitWidth(200);
        book1.setLayoutX(57);
        book1.setLayoutY(235);
        book1.setPreserveRatio(true);

        ImageView book2 = new ImageView();
        Image book2Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/HarryPotterandtheCursedChild.jpg")));
        book2.setImage(book2Img);
        book2.setFitHeight(150);
        book2.setFitWidth(200);
        book2.setLayoutX(56);
        book2.setLayoutY(559);
        book2.setPreserveRatio(true);

        ImageView book3 = new ImageView();
        Image book3Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/ABriefHistoryofTime.jpg")));
        book3.setImage(book3Img);
        book3.setFitHeight(150);
        book3.setFitWidth(200);
        book3.setLayoutX(56);
        book3.setLayoutY(396);
        book3.setPreserveRatio(true);

        Label forUser = new Label("For User:");
        forUser.setLayoutX(55.0);
        forUser.setLayoutY(191.0);

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(467.0);
        exitButton.setLayoutY(828.0);


        stage.setTitle("Cart");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, forUser, exitButton); //adds all the elements to the root
        root.getChildren().addAll(shoppingCartLabel, checkoutButton, book1, book2, book3);
        stage.setScene(scene); //sets the scene
        stage.show(); //shows the stage

        createCart(root);
    }

    public void createCart(Group root){
        Label title1 = new Label("Title");
        title1.setLayoutX(175.0);
        title1.setLayoutY(242.0);
        title1.setFont(Font.font("System Bold", 28.0));

        Label title2 = new Label("Title");
        title2.setLayoutX(175.0);
        title2.setLayoutY(396.0);
        title2.setFont(Font.font("System Bold", 28.0));

        Label title3 = new Label("Title");
        title3.setLayoutX(175.0);
        title3.setLayoutY(559.0);
        title3.setFont(Font.font("System Bold", 28.0));

        Label author1 = new Label("Author");
        author1.setLayoutX(175.0);
        author1.setLayoutY(282.0);
        author1.setFont(Font.font(19.0));

        Label author2 = new Label("Author");
        author2.setLayoutX(175.0);
        author2.setLayoutY(436.0);
        author2.setFont(Font.font(19.0));

        Label author3 = new Label("Author");
        author3.setLayoutX(175.0);
        author3.setLayoutY(599.0);
        author3.setFont(Font.font(19.0));

        Label returnDate1 = new Label("Return Date:");
        returnDate1.setLayoutX(175.0);
        returnDate1.setLayoutY(324.0);

        Label returnDate2 = new Label("Return Date:");
        returnDate2.setLayoutX(175.0);
        returnDate2.setLayoutY(471.0);

        Label returnDate3 = new Label("Return Date:");
        returnDate3.setLayoutX(175.0);
        returnDate3.setLayoutY(634.0);

        Button removeButton1 = new Button("Remove");
        removeButton1.setLayoutX(178.0);
        removeButton1.setLayoutY(354.0);

        Button removeButton2 = new Button("Remove");
        removeButton2.setLayoutX(174.0);
        removeButton2.setLayoutY(511.0);

        Button removeButton3 = new Button("Remove");
        removeButton3.setLayoutX(178.0);
        removeButton3.setLayoutY(671.0);

        root.getChildren().addAll(title1, title2, title3, author1, author2, author3, returnDate1, returnDate2, returnDate3);
        root.getChildren().addAll(removeButton1, removeButton2, removeButton3);
    }
}
