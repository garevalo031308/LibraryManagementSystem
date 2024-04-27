package Main.Daniel;

//nessesary import packages
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.util.Objects;

public class UpdatingMediaPage extends Application {
    public static void main(String[] args){
        Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
    }// end main

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 1280,  800);
        scene.setFill(Paint.valueOf("#F4CE90"));

        //Red rectangle header
        Rectangle header = new Rectangle();
        header.setWidth(1480); //1280 width does not take up all the horizontal space when full screen
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

        //---Body content for Create Account Page---//

        //"Updating Media" label
        Label updatingMediaLabel = new Label("Update Media");
        updatingMediaLabel.setLayoutX(540);
        updatingMediaLabel.setLayoutY(165);
        updatingMediaLabel.setFont(Font.font(50));
        updatingMediaLabel.setTextFill(Paint.valueOf("Black"));

        //Update media description
        Label updateMediaDescription = new Label("Click the button below to add or remove media to the Library catalog");
        updateMediaDescription.setLayoutX(450);
        updateMediaDescription.setLayoutY(225);
        updateMediaDescription.setFont(Font.font("System", FontPosture.ITALIC, 15));
        updateMediaDescription.setTextFill(Paint.valueOf("#707070"));

        //Add media button
        // Add media button
        Button addMediaButton2 = new Button("Add Media");
        addMediaButton2.setLayoutX(920); // Adjusted position
        addMediaButton2.setLayoutY(700); // Adjusted position
        addMediaButton2.setStyle("-fx-background-color: #BFDDD8"); // Set background color


        //Increase text size
        Button addMediaButton = new Button("Add Media");
        addMediaButton.setLayoutX(540);
        addMediaButton.setLayoutY(270);
        addMediaButton.setPrefSize(120, 40);
        addMediaButton.setStyle("-fx-background-color: #6DA6C5");
        addMediaButton.setFont(Font.font(15));

        //Delete media button
        //Increase text size
        Button deleteMediaButton = new Button("Delete Media");
        deleteMediaButton.setLayoutX(680);
        deleteMediaButton.setLayoutY(270);
        deleteMediaButton.setPrefSize(120, 40);
        deleteMediaButton.setStyle("-fx-background-color: #FF5A5F");
        deleteMediaButton.setFont(Font.font(15));

        //pane where radio buttons and input text fields will be placed
        Pane bodyPane = new Pane();
        bodyPane.setPrefWidth(675);
        bodyPane.setPrefHeight(400);
        bodyPane.setLayoutX(350);
        bodyPane.setLayoutY(350);
        bodyPane.setStyle("-fx-background-color: #F7DFB6");

        //Radio button for Book
        RadioButton bookRadioButton = new RadioButton("Book");
        bookRadioButton.setLayoutX(10);
        bookRadioButton.setLayoutY(10);

        //Radio button for E-Book
        RadioButton eBookRadioButton = new RadioButton("E-Book");
        eBookRadioButton.setLayoutX(10);
        eBookRadioButton.setLayoutY(40);

        ToggleGroup mediaTypeGroup = new ToggleGroup();
        bookRadioButton.setToggleGroup(mediaTypeGroup);
        eBookRadioButton.setToggleGroup(mediaTypeGroup);

        //TODO: Add text fields for media title, author, genre, ISBN, and publication date

        // Media Title label and text field
        Label mediaTitleLabel = new Label("Media Title");
        mediaTitleLabel.setLayoutX(10);
        mediaTitleLabel.setLayoutY(70);
        TextField mediaTitleTextField = new TextField();
        mediaTitleTextField.setLayoutX(10);
        mediaTitleTextField.setLayoutY(100);

        // Author label and text field
        Label authorLabel = new Label("Author");
        authorLabel.setLayoutX(10);
        authorLabel.setLayoutY(130);
        TextField authorTextField = new TextField();
        authorTextField.setLayoutX(10);
        authorTextField.setLayoutY(160);


        // Genre label and text field
        Label genreLabel = new Label("Genre");
        genreLabel.setLayoutX(10);
        genreLabel.setLayoutY(190);
        TextField genreTextField = new TextField();
        genreTextField.setLayoutX(10);
        genreTextField.setLayoutY(220);

        // ISBN label and text field
        Label isbnLabel = new Label("ISBN");
        isbnLabel.setLayoutX(10);
        isbnLabel.setLayoutY(250);
        TextField isbnTextField = new TextField();
        isbnTextField.setLayoutX(10);
        isbnTextField.setLayoutY(280);

        // Publication Date label and text field
        Label publicationDateLabel = new Label("Publication Date");
        publicationDateLabel.setLayoutX(10);
        publicationDateLabel.setLayoutY(310);
        TextField publicationDateTextField = new TextField();
        publicationDateTextField.setLayoutX(10);
        publicationDateTextField.setLayoutY(340);

        //---End of Body content for Create Account Page---//


        stage.setTitle("Library Management System");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, account); //adds header to the root (children are the modules)
        root.getChildren().addAll(catalog, aboutus, loginLabel, bodyPane, addMediaButton); //adds buttons to the root
        root.getChildren().addAll(deleteMediaButton);
        root.getChildren().addAll(updatingMediaLabel, updateMediaDescription, addMediaButton2);
        bodyPane.getChildren().addAll(bookRadioButton, eBookRadioButton, mediaTitleLabel,mediaTitleTextField, authorLabel, authorTextField, genreLabel, genreTextField, isbnLabel, isbnTextField, publicationDateLabel,publicationDateTextField);
        stage.setScene(scene);
        stage.show();


}//end class

}//end package
