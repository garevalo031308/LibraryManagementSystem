package Main.Sukeer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class AdminPage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

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

        TextField searchbar = new TextField("Search:");
        searchbar.setLayoutX(1000);
        searchbar.setLayoutY(51);
        searchbar.setPrefWidth(273);
        searchbar.setPrefHeight(47);

        TableView admin = new TableView<>();
        admin.setLayoutX(29);
        admin.setLayoutY(142);
        admin.setPrefWidth(1238);
        admin.setPrefHeight(516);

        

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(header, title, account, catalog, aboutus, searchbar,admin); //adds header to the root (children are the modules)
        stage.setScene(scene);
        stage.show();
    }
}
