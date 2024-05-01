package Main;

import Main.Media.CatalogPage;
import Main.User.AccountPage;
import Main.User.CheckoutPage;
import Main.User.LoginPage;
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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

// STRIKE link up all pages together
// TODO log in and save user while they are browsing
// TODO logout function
// TODO Remove cart from user when they log out/close application
// TODO make sure all buttons do something
// STRIKE search bar on all pages
// TODO change cart image with each time they add something to cart
// TODO Forget password thingy
// TODO back buttons on certain pages
// TODO a way for librarian to create accounts for customers
// TODO allow librarians to also have a customer account and have same functions as a customer

// TODO Fix admin, librarian, and customer database
// STRIKE Database for borrowed books that is linked to user that contains the book, return date, etc.
// STRIKE database that contains transaction history of user
// TODO make sure books database changes status when book is borrowed
// TODO make sure books database changes status when book is returned
// TODO possibly change catalog depending on what books are borrowed etc.
// TODO catalog filter that shows borrowed books, but have them greyed out or something
// TODO make sure user can return a book
// TODO Do not allow users to checkout any books if >3 or if they have a book that is overdue

// STRIKE - CreateAccountPage: allow users to create an account through application
// STRIKE - CreateAccountPage: after adding a user go to their account page
// TODO - Catalog Page: Fix filters, allow for searcing by title, author, genre, etc. on the page, and resetting of filters
// STRIKE - Login page: allow users to login with their credentials or create an account
// TODO - Admin page: create users, have some type of button that allows admins to login with their credentials and open something up
// TODO /\ edit users, delete users, etc.
// STRIKE - Updating Media Page: allow librarians to update media information (extra button if logged in as librarian?)
// TODO /\ a functions page that allows them to do certain things depending on their role, then a button that gets added on home page to access it
// STRIKE - AboutUs Page: add some extra fluff like a google maps thing or whatever
// STRIKE - UserLookUp Page: allow librarians to look up users and see what they have checked out
// STRIKE - User Page: allow users to see what they have checked out (Need to make in scene builder)
// STRIKE - Librarian Catalog page: table of books in database that they can edit, delete, or add to

public class HomePage extends Application {

    public static String url = "jdbc:mysql://localhost:3306/librahub";
    public static String currentLoggedInUser = "";

public static void main(String[] args){
    try {
        if (getConnection() != null) {
            Application.launch(args); // needed to launch the application. It will run the code in the "public void start()"
        } else {
            System.out.println("Cannot connect to the database!");
            System.exit(1);
        }
    } catch (SQLException e) {
        System.out.println("Cannot connect to the database!");
        e.printStackTrace();
        System.exit(1);
    }
}


    public static Connection getConnection() throws SQLException {
        String username = "sa";
        String password = "20Gabriel03!";

        return DriverManager.getConnection(url, username, password);
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
        Image cart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> {
            CheckoutPage.checkoutPage(stage, "4440486");
        });

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

        // TODO - search bar that looks up title or author when search bar is hit
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
        searchbutton.setOnAction(e -> {
            String searchQuery = searchbar.getText();
            CatalogPage.catalogPage(stage, searchQuery);
        });

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
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
