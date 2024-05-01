package Main.Daniel;

//nessesary import packages
import Main.AccountPage;
import Main.Chris.AboutUsPage;
import Main.Gabriel.CatalogPage;
import Main.Gabriel.CheckoutPage;
import Main.Sukeer.LoginPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;

public class EditingMediaPage {

    public static void editingMediaPage(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 1280,  900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(EditingMediaPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
        logo.setImage(logoimg); //set image
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
        account.setOnAction(e->{
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                AccountPage.accountPage(stage, currentLoggedInUser);
            }
        });

        Button catalog = new Button("Catalog");
        catalog.setStyle("-fx-background-color: #363732");
        catalog.setTextFill(Paint.valueOf("white"));
        catalog.setPrefWidth(109);
        catalog.setPrefHeight(67);
        catalog.setLayoutX(708);
        catalog.setLayoutY(41);
        catalog.setOnAction(e-> CatalogPage.catalogPage(stage, ""));

        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(863);
        aboutus.setLayoutY(41);
        aboutus.setOnAction(e-> AboutUsPage.aboutUsPage(stage));

        Label loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);
        loginLabel.setOnMouseClicked(e-> LoginPage.loginPage(stage));

        ImageView cartimage = new ImageView();
        Image cart = new Image(Objects.requireNonNull(AccountPage.class.getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> {
            CheckoutPage.checkoutPage(stage, "4440486");
        });

        TextField searchBar = new TextField();
        searchBar.setPromptText("Type a title, author, etc. here");
        searchBar.setPrefSize(226, 26);
        searchBar.setLayoutX(991);
        searchBar.setLayoutY(83);

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(1217);
        searchButton.setLayoutY(83);
        searchButton.setOnAction(e -> {
            String searchQuery = searchBar.getText();
            CatalogPage.catalogPage(stage, searchQuery);
        });

        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);



        //---Body content for Create Account Page---//
        Text titleText = new Text("Edit Media");
        titleText.setLayoutX(463);
        titleText.setLayoutY(187);
        titleText.setFont(Font.font(60));

        Text subTitle = new Text("Click the button to edit the media");
        subTitle.setLayoutX(344);
        subTitle.setLayoutY(234);
        subTitle.setFill(Paint.valueOf("#35363180"));
        subTitle.setFont(Font.font(30));

        TextField titleField = new TextField();
        titleField.setPromptText("Title of media");
        titleField.setPrefSize(500, 67);
        titleField.setLayoutX(44);
        titleField.setLayoutY(299);
        titleField.setFont(Font.font(35));

        Text titleText2 = new Text("Title");
        titleText2.setLayoutX(44);
        titleText2.setLayoutY(279);
        titleText2.setFont(Font.font(35));

        TextField authorField = new TextField();
        authorField.setLayoutX(44);
        authorField.setLayoutY(441);
        authorField.setPrefHeight(67);
        authorField.setPrefWidth(500);
        authorField.setPromptText("Author of the media");
        authorField.setFont(Font.font(35));

        Text authorText = new Text("Author");
        authorText.setLayoutX(44);
        authorText.setLayoutY(421);
        authorText.setFont(Font.font(35));

        TextField idField = new TextField();
        idField.setLayoutX(588);
        idField.setLayoutY(299);
        idField.setPrefHeight(75);
        idField.setPrefWidth(291);
        idField.setDisable(true);
        idField.setFont(Font.font(35));

        Text idText = new Text("ID");
        idText.setLayoutX(588);
        idText.setLayoutY(279);
        idText.setFont(Font.font(35));

        Text genreText = new Text("Genre");
        genreText.setLayoutX(44);
        genreText.setLayoutY(576);
        genreText.setFont(Font.font(35));

        Button addMediaButton = new Button("Edit Media");
        addMediaButton.setLayoutX(847);
        addMediaButton.setLayoutY(661);
        addMediaButton.setPrefHeight(75);
        addMediaButton.setPrefWidth(277);
        addMediaButton.setStyle("-fx-background-color:  #A9DDD6;");
        addMediaButton.setFont(Font.font(35));

        ToggleGroup typeGroup = new ToggleGroup();

        RadioButton booksRadioButton = new RadioButton("Books");
        booksRadioButton.setLayoutX(879);
        booksRadioButton.setLayoutY(542);
        booksRadioButton.setFont(Font.font(30));
        booksRadioButton.setToggleGroup(typeGroup);

        RadioButton ebooksRadioButton = new RadioButton("E-books");
        ebooksRadioButton.setLayoutX(1033);
        ebooksRadioButton.setLayoutY(542);
        ebooksRadioButton.setFont(Font.font(30));
        ebooksRadioButton.setToggleGroup(typeGroup);

        typeGroup.selectToggle(booksRadioButton);

        TextField publicationDateField = new TextField();
        publicationDateField.setLayoutX(945);
        publicationDateField.setLayoutY(299);
        publicationDateField.setPrefHeight(75);
        publicationDateField.setPrefWidth(291);
        publicationDateField.setPromptText("MM/DD/YYYY");
        publicationDateField.setFont(Font.font(35));

        Text publicationDateText = new Text("Publication Date");
        publicationDateText.setLayoutX(945);
        publicationDateText.setLayoutY(285);
        publicationDateText.setFont(Font.font(35));

        TextField publisherField = new TextField();
        publisherField.setLayoutX(586);
        publisherField.setLayoutY(441);
        publisherField.setPrefHeight(75);
        publisherField.setPrefWidth(291);
        publisherField.setPromptText("Publisher of the media");
        publisherField.setFont(Font.font(35));

        Text publisherText = new Text("Publisher");
        publisherText.setLayoutX(589);
        publisherText.setLayoutY(432);
        publisherText.setFont(Font.font(35));

        TextArea descriptionArea = new TextArea();
        descriptionArea.setLayoutX(265);
        descriptionArea.setLayoutY(601);
        descriptionArea.setPrefHeight(253);
        descriptionArea.setPrefWidth(500);

        Text descriptionText = new Text("Description");
        descriptionText.setLayoutX(265);
        descriptionText.setLayoutY(576);
        descriptionText.setFont(Font.font(35));

        TextField imageLocationField = new TextField();
        imageLocationField.setLayoutX(945);
        imageLocationField.setLayoutY(441);
        imageLocationField.setPrefHeight(75);
        imageLocationField.setPrefWidth(291);
        imageLocationField.setFont(Font.font(35));

        Text imageLocationText = new Text("Image Location");
        imageLocationText.setLayoutX(945);
        imageLocationText.setLayoutY(432);
        imageLocationText.setFont(Font.font(35));

        ToggleGroup mainGenreGroup = new ToggleGroup();

        RadioButton nonFictionRadioButton = new RadioButton("Non-Fiction");
        nonFictionRadioButton.setLayoutX(43);
        nonFictionRadioButton.setLayoutY(587);
        nonFictionRadioButton.setPrefHeight(45);
        nonFictionRadioButton.setPrefWidth(187);
        nonFictionRadioButton.setFont(Font.font(22));
        nonFictionRadioButton.setToggleGroup(mainGenreGroup);

        RadioButton fictionRadioButton = new RadioButton("Fiction");
        fictionRadioButton.setLayoutX(43);
        fictionRadioButton.setLayoutY(625);
        fictionRadioButton.setPrefHeight(45);
        fictionRadioButton.setPrefWidth(187);
        fictionRadioButton.setFont(Font.font(22));
        fictionRadioButton.setToggleGroup(mainGenreGroup);

        mainGenreGroup.selectToggle(nonFictionRadioButton);

        ToggleGroup fictionGroup = new ToggleGroup();

        RadioButton scienceFictionRadioButton = new RadioButton("Science Fiction");
        scienceFictionRadioButton.setLayoutX(72);
        scienceFictionRadioButton.setLayoutY(668);
        scienceFictionRadioButton.setPrefHeight(30);
        scienceFictionRadioButton.setPrefWidth(187);
        scienceFictionRadioButton.setFont(Font.font(18));
        scienceFictionRadioButton.setToggleGroup(fictionGroup);

        RadioButton fantasyRadioButton = new RadioButton("Fantasy");
        fantasyRadioButton.setLayoutX(72);
        fantasyRadioButton.setLayoutY(698);
        fantasyRadioButton.setPrefHeight(30);
        fantasyRadioButton.setPrefWidth(187);
        fantasyRadioButton.setFont(Font.font(18));
        fantasyRadioButton.setToggleGroup(fictionGroup);

        RadioButton mysteryRadioButton = new RadioButton("Mystery");
        mysteryRadioButton.setLayoutX(72);
        mysteryRadioButton.setLayoutY(728);
        mysteryRadioButton.setPrefHeight(30);
        mysteryRadioButton.setPrefWidth(187);
        mysteryRadioButton.setFont(Font.font(18));
        mysteryRadioButton.setToggleGroup(fictionGroup);

        RadioButton horrorRadioButton = new RadioButton("Horror");
        horrorRadioButton.setLayoutX(72);
        horrorRadioButton.setLayoutY(758);
        horrorRadioButton.setPrefHeight(30);
        horrorRadioButton.setPrefWidth(187);
        horrorRadioButton.setFont(Font.font(18));
        horrorRadioButton.setToggleGroup(fictionGroup);

        RadioButton dramaRadioButton = new RadioButton("Drama");
        dramaRadioButton.setLayoutX(72);
        dramaRadioButton.setLayoutY(788);
        dramaRadioButton.setPrefHeight(30);
        dramaRadioButton.setPrefWidth(187);
        dramaRadioButton.setFont(Font.font(18));
        dramaRadioButton.setToggleGroup(fictionGroup);

        RadioButton mythologyRadioButton = new RadioButton("Mythology");
        mythologyRadioButton.setLayoutX(72);
        mythologyRadioButton.setLayoutY(818);
        mythologyRadioButton.setPrefHeight(30);
        mythologyRadioButton.setPrefWidth(187);
        mythologyRadioButton.setFont(Font.font(18));
        mythologyRadioButton.setToggleGroup(fictionGroup);

        fictionGroup.selectToggle(scienceFictionRadioButton);

        Text typeText = new Text("Type");
        typeText.setLayoutX(779);
        typeText.setLayoutY(576);
        typeText.setFont(Font.font(35));

        Button backButton = new Button("Back");
        backButton.setLayoutX(14);
        backButton.setLayoutY(137);
        //---End of Body content for Create Account Page---//

        root.getChildren().addAll(titleText, subTitle, titleField, titleText2, authorField, authorText, idField, idText, genreText, addMediaButton, booksRadioButton, ebooksRadioButton, publicationDateField, publicationDateText, publisherField, publisherText, descriptionArea, descriptionText, imageLocationField, imageLocationText, nonFictionRadioButton, fictionRadioButton);
        root.getChildren().addAll(scienceFictionRadioButton, fantasyRadioButton, mysteryRadioButton, horrorRadioButton, dramaRadioButton, mythologyRadioButton, typeText, backButton);
        stage.setTitle("Library Management System");// sets current scene
        stage.setScene(scene);
        stage.show();


}//end class

}//end package
