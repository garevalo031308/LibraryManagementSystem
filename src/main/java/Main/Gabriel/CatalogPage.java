package Main.Gabriel;

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
import javafx.stage.Stage;

import java.util.Objects;

public class CatalogPage extends Application {

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

        Pane filterPane = new Pane();
        filterPane.setPrefWidth(200);
        filterPane.setPrefHeight(776);
        filterPane.setLayoutY(130);
        filterPane.setStyle("-fx-background-color: #6DA6C5");

        Label sortByLabel = new Label("Sort By:");
        sortByLabel.setLayoutX(14);
        sortByLabel.setLayoutY(14);
        sortByLabel.setFont(Font.font(24));
        sortByLabel.setTextFill(Paint.valueOf("white"));

        ToggleGroup sortGroup = new ToggleGroup();

        RadioButton titleButton = new RadioButton("Title");
        titleButton.setLayoutX(14);
        titleButton.setLayoutY(49);
        titleButton.setFont(Font.font(16));
        titleButton.setToggleGroup(sortGroup);
        sortGroup.selectToggle(titleButton);

        RadioButton authorButton = new RadioButton("Author");
        authorButton.setLayoutX(14);
        authorButton.setLayoutY(74);
        authorButton.setFont(Font.font(16));
        authorButton.setToggleGroup(sortGroup);

        RadioButton dateNewButton = new RadioButton("Date (Newest)");
        dateNewButton.setLayoutX(14);
        dateNewButton.setLayoutY(99);
        dateNewButton.setFont(Font.font(16));
        dateNewButton.setToggleGroup(sortGroup);

        RadioButton dateOldButton = new RadioButton("Date (Oldest)");
        dateOldButton.setLayoutX(14);
        dateOldButton.setLayoutY(124);
        dateOldButton.setFont(Font.font(16));
        dateOldButton.setToggleGroup(sortGroup);

        Separator sortmediasep = new Separator();
        sortmediasep.setLayoutY(159);
        sortmediasep.setPrefWidth(200);
        sortmediasep.setPrefHeight(19);

        Label mediaLabel = new Label("Media Type:");
        mediaLabel.setLayoutX(14);
        mediaLabel.setLayoutY(168);
        mediaLabel.setFont(Font.font(24));
        mediaLabel.setTextFill(Paint.valueOf("white"));

        CheckBox bookCheck = new CheckBox("Book");
        bookCheck.setLayoutX(14);
        bookCheck.setLayoutY(203);
        bookCheck.setFont(Font.font(16));

        CheckBox eBookCheck = new CheckBox("eBook");
        eBookCheck.setLayoutX(14);
        eBookCheck.setLayoutY(228);
        eBookCheck.setFont(Font.font(16));

        CheckBox videoCheck = new CheckBox("Video");
        videoCheck.setLayoutX(14);
        videoCheck.setLayoutY(253);
        videoCheck.setFont(Font.font(16));

        CheckBox audioCheck = new CheckBox("Audio");
        audioCheck.setLayoutX(14);
        audioCheck.setLayoutY(278);
        audioCheck.setFont(Font.font(16));

        Separator mediaSep = new Separator();
        mediaSep.setLayoutY(303);
        mediaSep.setPrefWidth(200);
        mediaSep.setPrefHeight(19);

        Label genreLabel = new Label("Genre:");
        genreLabel.setLayoutX(14);
        genreLabel.setLayoutY(322);
        genreLabel.setFont(Font.font(24));
        genreLabel.setTextFill(Paint.valueOf("white"));

        CheckBox fictionCheck = new CheckBox("Fiction");
        fictionCheck.setLayoutX(14);
        fictionCheck.setLayoutY(357);
        fictionCheck.setFont(Font.font(16));

        CheckBox scienceFictionCheck = new CheckBox("Science Fiction");
        scienceFictionCheck.setLayoutX(27);
        scienceFictionCheck.setLayoutY(385);

        CheckBox fantasyCheck = new CheckBox("Fantasy");
        fantasyCheck.setLayoutX(27);
        fantasyCheck.setLayoutY(405);

        CheckBox mysteryCheck = new CheckBox("Mystery");
        mysteryCheck.setLayoutX(27);
        mysteryCheck.setLayoutY(425);

        CheckBox horrorCheck = new CheckBox("Horror");
        horrorCheck.setLayoutX(27);
        horrorCheck.setLayoutY(445);

        CheckBox dramaCheck = new CheckBox("Drama");
        dramaCheck.setLayoutX(27);
        dramaCheck.setLayoutY(465);

        CheckBox MythologyCheck = new CheckBox("Mythology");
        MythologyCheck.setLayoutX(27);
        MythologyCheck.setLayoutY(485);

        CheckBox nonFictionCheck = new CheckBox("Non-Fiction");
        nonFictionCheck.setLayoutX(14);
        nonFictionCheck.setLayoutY(504);
        nonFictionCheck.setFont(Font.font(16));

        Separator genreSep = new Separator();
        genreSep.setLayoutY(529);
        genreSep.setPrefWidth(200);
        genreSep.setPrefHeight(19);

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(filterPane, header, logo, title, cartimage, account, catalog, aboutus, loginLabel); //add all the elements to the root
        filterPane.getChildren().addAll(sortByLabel, titleButton, authorButton, dateNewButton, dateOldButton, sortmediasep);
        filterPane.getChildren().addAll(mediaLabel, bookCheck, eBookCheck, videoCheck, audioCheck, mediaSep);
        filterPane.getChildren().addAll(genreLabel, fictionCheck, scienceFictionCheck, fantasyCheck, mysteryCheck, horrorCheck, dramaCheck, MythologyCheck, nonFictionCheck, genreSep);
        stage.setTitle("Catalog");
        stage.setScene(scene);
        stage.show();
    }
}
