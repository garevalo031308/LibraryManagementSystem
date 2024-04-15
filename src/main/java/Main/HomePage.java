package Main;

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

public class HomePage extends Application {
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

        ImageView cartimage = new ImageView();
        Image cart = new Image("C://Users//minig//IntelliJ//LibraryMangementSystem//src//main//resources//Images//Main//cart.png");
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);

        ImageView banner = new ImageView();
        Image imgbanner = new Image("C://Users//minig//IntelliJ//LibraryMangementSystem//src//main//resources//Images//HomePage//banner.jpg");
        banner.setImage(imgbanner);
        banner.setFitWidth(1308);
        banner.setFitHeight(413);
        banner.setLayoutX(-18);
        banner.setLayoutY(132);

        Label helpLabel = new Label("What can we help you find?");
        helpLabel.setUnderline(true);
        helpLabel.setTextFill(Paint.valueOf("White"));
        helpLabel.setFont(Font.font("Bold", 50));
        helpLabel.setPrefSize(650.3173828125, 73);
        helpLabel.setLayoutX(31);
        helpLabel.setLayoutY(210);

        TextField searchbar = new TextField();
        searchbar.setPromptText("Type a title, author, etc. here");
        searchbar.setPrefWidth(567);
        searchbar.setPrefHeight(35);
        searchbar.setLayoutX(31);
        searchbar.setLayoutY(304);

        Button searchbutton = new Button("Search");
        searchbutton.setLayoutX(598);
        searchbutton.setLayoutY(304);
        searchbutton.setPrefHeight(35);
        searchbutton.setPrefWidth(80);

        Label popularLabel = new Label("Popular Books");
        popularLabel.setFont(Font.font(39));
        popularLabel.setPrefSize(247.2158203125, 56);
        popularLabel.setLayoutX(505);
        popularLabel.setLayoutY(552);

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, banner, helpLabel, searchbar, searchbutton); //adds header to the root (children are the modules)
        root.getChildren().addAll(popularLabel);
        stage.setScene(scene);
        stage.show();
    }
}
