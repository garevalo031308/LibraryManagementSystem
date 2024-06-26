package Main;

import Main.Media.BookPopUp;
import Main.Media.Books;
import Main.Media.CatalogPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.sql.*;
import java.util.Objects;

// STRIKE link up all pages together
// STRIKE log in and save user while they are browsing
// STRIKE logout function
// STRIKE Remove cart from user when they log out/close application
// TODO make sure all buttons do something
// STRIKE search bar on all pages

// TODO Forget password thingy
// TODO back buttons on certain pages
// TODO a way for librarian to create accounts for customers
// STRIKE allow librarians to also have a customer account and have same functions as a customer
// TODO stage titles for each page
// TODO logos for each page

// STRIKE Fix admin, librarian, and customer database
// STRIKE Database for borrowed books that is linked to user that contains the book, return date, etc.
// STRIKE database that contains transaction history of user
// STRIKE make sure books database changes status when book is borrowed
// STRIKE make sure books database changes status when book is returned
// TODO possibly change catalog depending on what books are borrowed etc.
// TODO catalog filter that shows borrowed books, but have them greyed out or something
// TODO books table set status to borrwed as true
// STRIKE make sure user can return a book
// STRIKE Do not allow users to checkout any books if >3 or if they have a book that is overdue

// STRIKE - CreateAccountPage: allow users to create an account through application
// STRIKE - CreateAccountPage: after adding a user go to their account page
// STRIKE - Catalog Page: Fix filters, allow for searcing by title, author, genre, etc. on the page, and resetting of filters
// STRIKE - Login page: allow users to login with their credentials or create an account
// STRIKE - Admin page: create users, have some type of button that allows admins to login with their credentials and open something up
// STRIKE /\ edit users, delete users, etc.
// TODO - Books popup into an actual page with back button
// STRIKE - Updating Media Page: allow librarians to update media information (extra button if logged in as librarian?)
// STRIKE /\ a functions page that allows them to do certain things depending on their role, then a button that gets added on home page to access it
// STRIKE - AboutUs Page: add some extra fluff like a google maps thing or whatever
// STRIKE - UserLookUp Page: allow librarians to look up users and see what they have checked out
// STRIKE - User Page: allow users to see what they have checked out (Need to make in scene builder)
// STRIKE - Librarian Catalog page: table of books in database that they can edit, delete, or add to

public class HomePage extends Application {

    public static String url = "jdbc:mysql://localhost:3306/librahub";

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


        stage.setTitle("Library Management System - Home Page");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(banner, helpLabel, searchbar, searchbutton); //adds header to the root (children are the modules)
        root.getChildren().addAll(popularLabel);
        root.getChildren().addAll(popular1, popular2, popular3, popular4, popular5);
        Header.getHomeHeader(stage, root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        popular1.setOnMouseClicked(e -> {
            Books book1 = getBookFromDatabase("5225100");
            if (book1 != null) {
                BookPopUp.bookPopUp(stage, book1);
            }
        });

        popular2.setOnMouseClicked(e -> {
            Books book1 = getBookFromDatabase("6458744");
            if (book1 != null) {
                BookPopUp.bookPopUp(stage, book1);
            }
        });

        popular3.setOnMouseClicked(e -> {
            Books book1 = getBookFromDatabase("1271204");
            if (book1 != null) {
                BookPopUp.bookPopUp(stage, book1);
            }
        });

        popular4.setOnMouseClicked(e -> {
            Books book1 = getBookFromDatabase("5587883");
            if (book1 != null) {
                BookPopUp.bookPopUp(stage, book1);
            }
        });

        popular5.setOnMouseClicked(e -> {
            Books book1 = getBookFromDatabase("5030003");
            if (book1 != null) {
                BookPopUp.bookPopUp(stage, book1);
            }
        });
    }

    public static Books getBookFromDatabase(String bookID) {
        Books book = null;
        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Books();
                book.setID(String.valueOf(resultSet.getInt("id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setType(resultSet.getString("type"));
                book.setBorrowed(resultSet.getBoolean("borrowed"));
                book.setImage(resultSet.getString("image"));
                book.setDate(resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}
