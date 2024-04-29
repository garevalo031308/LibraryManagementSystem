package Main.Chris;

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
import javafx.stage.Stage;

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
        Image image = new Image("file:libgenlogo.png");
        logo.setImage(image);
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

        // ImageView cartimage = new ImageView();
        //Image cart = new Image("C://Users//minig//IntelliJ//LibraryMangementSystem//src//main//resources//Images//Main//cart.png");
        //cartimage.setImage(cart);
        //cartimage.setFitWidth(90);
        //cartimage.setFitHeight(59);
        //cartimage.setLayoutX(1206);

        //ImageView books = new ImageView();
        //Image bookImage = new Image("//C://Users//wccaw//Downloads//cricut photos//R.jpeg");
        //books.setImage(bookImage);
        //books.setFitWidth(1308);
        //books.setFitHeight(413);
        //books.setLayoutX(100);
        //books.setLayoutY(100);

        Label helpLabel = new Label("Library User Lookup");
        helpLabel.setUnderline(true);
        helpLabel.setTextFill(Paint.valueOf("Black"));
        helpLabel.setFont(Font.font("Bold", 50));
        helpLabel.setPrefSize(650.3173828125, 73);
        helpLabel.setLayoutX(375);
        helpLabel.setLayoutY(250);

        TextField searchbar = new TextField();
        searchbar.setPromptText("##########");
        searchbar.setPrefWidth(250);
        searchbar.setPrefHeight(35);
        searchbar.setLayoutX(640);
        searchbar.setLayoutY(400);

        TextField searchbarTwo = new TextField();
        searchbarTwo.setPromptText("Last name, First name");
        searchbarTwo.setPrefWidth(250);
        searchbarTwo.setPrefHeight(35);
        searchbarTwo.setLayoutX(325);
        searchbarTwo.setLayoutY(400);

        Button searchbutton = new Button("Search Number");
        searchbutton.setLayoutX(640);
        searchbutton.setLayoutY(450);
        searchbutton.setPrefHeight(35);
        searchbutton.setPrefWidth(140);

        Button searchbuttonTwo = new Button("Search Name");
        searchbuttonTwo.setLayoutX(325);
        searchbuttonTwo.setLayoutY(450);
        searchbuttonTwo.setPrefHeight(35);
        searchbuttonTwo.setPrefWidth(130);

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, helpLabel, searchbarTwo, searchbar, searchbutton, searchbuttonTwo); //adds header to the root (children are the modules)
        stage.setScene(scene);
        stage.show();
    }
}