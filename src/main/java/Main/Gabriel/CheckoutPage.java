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
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
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

        Button account = new Button("Account");
        account.setLayoutX(553);
        account.setLayoutY(41);
        account.setPrefWidth(109);
        account.setPrefHeight(67);
        account.setTextFill(Paint.valueOf("white"));
        account.setStyle("-fx-background-color:  #363732");

        Button catalog = new Button("Catalog");
        catalog.setStyle("-fx-background-color: #363732");
        catalog.setTextFill(Paint.valueOf("white"));
        catalog.setPrefWidth(109);
        catalog.setPrefHeight(67);
        catalog.setLayoutX(708);
        catalog.setLayoutY(41);

        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(863);
        aboutus.setLayoutY(41);

        Label loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);

        ImageView cartimage = new ImageView();
        Image cart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);

        stage.setTitle("Library Management System");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage); //adds all the elements to the root
        stage.setScene(scene); //sets the scene
        stage.show(); //shows the stage
    }
}
