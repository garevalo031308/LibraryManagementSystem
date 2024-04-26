package Main;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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
import org.bson.Document;

import java.util.Objects;

public class HomePage extends Application {

    public static String connectionString = "mongodb+srv://garevalo031308:20BlueLuna03!@librahub.mgdhjt4.mongodb.net/?retryWrites=true&w=majority&appName=LibraHub";

    public static void main(String[] args){
        if (connectToDB()) {
            Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
        } else {
            System.exit(1);
            System.out.println("Failed to connect to the database.");
        }

    }

    public static boolean connectToDB(){ // This connects to Mongo database
        ServerApi serverAPI = ServerApi.builder().version(ServerApiVersion.V1).build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverAPI)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                // TODO make database a public static variable so it can be accessed from other classes
                MongoDatabase database = mongoClient.getDatabase("LibraHub");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                return true;
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        return false;
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

        ImageView banner = new ImageView();
        Image imgbanner = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/HomePage/banner.jpg")));
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

        Label popularLabel = new Label("Librarian Selects");
        popularLabel.setFont(Font.font(39));
        popularLabel.setPrefSize(278.5, 56);
        popularLabel.setLayoutX(499);
        popularLabel.setLayoutY(545);

        ImageView popular1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/Dune.jpg"))));
        popular1.setFitWidth(192);
        popular1.setFitHeight(225);
        popular1.setLayoutX(130);
        popular1.setLayoutY(625);
        popular1.setPreserveRatio(true);

        ImageView popular2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/Macbeth.jpg"))));
        popular2.setFitWidth(192);
        popular2.setFitHeight(225);
        popular2.setLayoutX(330);
        popular2.setLayoutY(625);
        popular2.setPreserveRatio(true);

        ImageView popular3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/HarryPotterandtheCursedChild.jpg"))));
        popular3.setFitWidth(192);
        popular3.setFitHeight(225);
        popular3.setLayoutX(530);
        popular3.setLayoutY(625);
        popular3.setPreserveRatio(true);

        ImageView popular4 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/PJChaliceoftheGods.jpg"))));
        popular4.setFitWidth(192);
        popular4.setFitHeight(225);
        popular4.setLayoutX(730);
        popular4.setLayoutY(625);
        popular4.setPreserveRatio(true);

        ImageView popular5 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/MediaCovers/TheOutsider.jpg"))));
        popular5.setFitWidth(192);
        popular5.setFitHeight(225);
        popular5.setLayoutX(930);
        popular5.setLayoutY(625);
        popular5.setPreserveRatio(true);


        stage.setTitle("Library Management System");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, banner, helpLabel, searchbar, searchbutton); //adds header to the root (children are the modules)
        root.getChildren().addAll(popularLabel);
        root.getChildren().addAll(popular1, popular2, popular3, popular4, popular5);
        stage.setScene(scene);
        stage.show();


    }
}
