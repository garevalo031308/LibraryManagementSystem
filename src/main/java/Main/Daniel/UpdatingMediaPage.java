package Main.Daniel;

//nessesary import packages
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class UpdatingMediaPage extends Application {
    public static void main(String[] args){
        Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
    }// end main

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 1280,  1600);
        scene.setFill(Paint.valueOf("#F4CE90"));

        //Red rectangle header
        Rectangle header = new Rectangle();
        header.setWidth(1480); //1280 width doesnt take up all the horizontal space when full screen
        header.setHeight(150);
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

        //TODO: Add "Updating Media" label
        //TODO: Add Update media description ("Click the button below to add or remove media to the Library catalog" )

        //Add media button
        //TODO: Figure out how to chnage text size
        Button addMediaButton = new Button("Add Media");
        addMediaButton.setLayoutX(450);
        addMediaButton.setLayoutY(270);
        addMediaButton.setPrefSize(150, 50);
        addMediaButton.setStyle("-fx-background-color: #6DA6C5");

        //Delete media button
        //TODO: Figure out how to chnage text size
        Button deleteMediaButton = new Button("Delete Media");
        deleteMediaButton.setLayoutX(870);
        deleteMediaButton.setLayoutY(270);
        deleteMediaButton.setPrefSize(150, 50);
        deleteMediaButton.setStyle("-fx-background-color: #FF5A5F");

        //pane where radio buttons and input text fields will be placed
        //TODO: Figure out spacing on top of pane to fit buttons nicely
        Pane bodyPane = new Pane();
        bodyPane.setPrefWidth(675);
        bodyPane.setPrefHeight(600);
        bodyPane.setLayoutX(400);
        bodyPane.setLayoutY(240);
        bodyPane.setStyle("-fx-background-color: #F7DFB6");

        //TODO: Add radio buttons for media type (Book, E-Book)
        //TODO: Add text fields for media title, author, genre, ISBN, and publication date









        //---Body content for Create Account Page---//

        //---End of Body content for Create Account Page---//


        stage.setTitle("Library Management System");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, account); //adds header to the root (children are the modules)
        root.getChildren().addAll(catalog, aboutus, loginLabel, bodyPane, addMediaButton); //adds buttons to the root
        root.getChildren().addAll(deleteMediaButton);
        stage.setScene(scene);
        stage.show();


}//end class

}//end package
