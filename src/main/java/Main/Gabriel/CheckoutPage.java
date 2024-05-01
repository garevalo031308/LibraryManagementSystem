package Main.Gabriel;

import Main.AccountPage;
import Main.Chris.AboutUsPage;
import Main.HomePage;
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
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;

public class CheckoutPage {

    public static void checkoutPage(Stage stage, String ID){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set
        stage.setResizable(false);
        stage.setFullScreen(false);

        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(AccountPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
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

        Label shoppingCartLabel = new Label("Shopping Cart:");
        shoppingCartLabel.setLayoutX(18.0);
        shoppingCartLabel.setLayoutY(132.0);
        shoppingCartLabel.setFont(Font.font(34.0));

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setLayoutX(42.0);
        checkoutButton.setLayoutY(778.0);
        checkoutButton.setPrefHeight(50.0);
        checkoutButton.setPrefWidth(176.0);

        Label forUser = new Label("For User:");
        forUser.setLayoutX(55.0);
        forUser.setLayoutY(191.0);

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(467.0);
        exitButton.setLayoutY(828.0);


        stage.setTitle("Cart");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(CheckoutPage.class.getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(forUser, exitButton); //adds all the elements to the root
        root.getChildren().addAll(shoppingCartLabel, checkoutButton);
        stage.setScene(scene); //sets the scene
        stage.show(); //shows the stage

        ArrayList<Books> cartItems = getCartItems(ID);

        createCart(root, cartItems, ID, stage);

        checkoutButton.setOnAction(e -> checkoutBooks(ID, cartItems, stage));
    }

    private static void checkoutBooks(String ID, ArrayList<Books> cartItems, Stage stage) {
        if (!canCheckout(ID)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You cannot checkout more than 3 books at a time.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try (Connection connection = HomePage.getConnection()) {
            // Start a transaction
            connection.setAutoCommit(false);

            // Get the current date
            LocalDate today = LocalDate.now();
            // Calculate the return date
            LocalDate returnDate = today.plusWeeks(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String borrowedDate = today.format(formatter);
            String returnDateStr = returnDate.format(formatter);

            // Iterate over the items in the cart
            for (Books book : cartItems) {
                // Insert a new row into the borrowed_books table
                String sql = "INSERT INTO borrowed_books (book_id, user_id, borrowed_date, return_date) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, Integer.parseInt(book.getID()));
                statement.setInt(2, Integer.parseInt(ID));
                statement.setString(3, borrowedDate);
                statement.setString(4, returnDateStr);
                statement.executeUpdate();

                // Add a new transaction
                addTransaction(ID, book.getID(), borrowedDate, returnDateStr, "BORROWED");
            }

            // Delete the user's cart
            String sql = "DELETE FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ID);
            statement.executeUpdate();

            // Commit the transaction
            connection.commit();

            // Set the connection's auto-commit mode back to true
            connection.setAutoCommit(true);

            // Refresh the page
            checkoutPage(stage, ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void addTransaction(String userID, String bookID, String borrowDate, String returnDate, String status) {
        try (Connection connection = HomePage.getConnection()) {
            String sql = "INSERT INTO transactions (user_id, book_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, bookID);
            statement.setString(3, borrowDate);
            statement.setString(4, returnDate);
            statement.setString(5, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createCart(Group root, ArrayList<Books> cartItems, String ID, Stage stage){

        for (int i = 0; i < cartItems.size(); i++) {
            ImageView bookview = new ImageView();
            Image bookImg = new Image(Objects.requireNonNull(CheckoutPage.class.getResourceAsStream(cartItems.get(i).image)));
            bookview.setImage(bookImg);
            bookview.setFitWidth(200);
            bookview.setFitHeight(150);
            bookview.setLayoutX(57);
            bookview.setLayoutY(235 + (i*161));
            bookview.setPreserveRatio(true);

            Label title = new Label(cartItems.get(i).title);
            title.setLayoutX(175);
            title.setLayoutY(242 + (i*154));
            title.setFont(Font.font(28));

            Label author = new Label(cartItems.get(i).author);
            author.setLayoutX(175);
            author.setLayoutY(282 + (i*154));
            author.setFont(Font.font(18));

            Label returnDate = new Label("Return Date: " + getReturnDate());
            returnDate.setLayoutX(175);
            returnDate.setLayoutY(324 + (i*147));

            Button removeButton = new Button("Remove");
            removeButton.setLayoutX(175);
            removeButton.setLayoutY(354 + (i*157));
            int finalI = i;
            removeButton.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this book from the cart?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        removeBookFromCart(ID, cartItems.get(finalI).getID());
                        checkoutPage(stage, ID);
                    }
                });
            });


            root.getChildren().addAll(bookview, title, author, returnDate, removeButton);
        }
    }

    private static boolean canCheckout(String userID) {
        try (Connection connection = HomePage.getConnection()) {
            // Check the number of books in the cart
            String sql = "SELECT COUNT(*) AS book_count FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            int cartBookCount = 0;
            if (resultSet.next()) {
                cartBookCount = resultSet.getInt("book_count");
            }

            // Check the number of books in the borrowed_books table
            sql = "SELECT COUNT(*) AS book_count FROM borrowed_books WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();

            int borrowedBookCount = 0;
            if (resultSet.next()) {
                borrowedBookCount = resultSet.getInt("book_count");
            }

            // Return true if the total number of books is less than or equal to 3
            return (cartBookCount + borrowedBookCount) <= 3;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static ArrayList<Books> getCartItems(String userID) {
        ArrayList<Books> cartItems = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT media1ID, media2ID, media3ID FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] mediaIDs = {resultSet.getString("media1ID"), resultSet.getString("media2ID"), resultSet.getString("media3ID")};

                for (String mediaID : mediaIDs) {
                    if (mediaID != null) {
                        sql = "SELECT * FROM Books WHERE id = ?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, mediaID);
                        ResultSet bookResultSet = statement.executeQuery();

                        if (bookResultSet.next()) {
                            Books book = new Books();
                            book.setID(String.valueOf(bookResultSet.getInt("id")));
                            book.setTitle(bookResultSet.getString("title"));
                            book.setAuthor(bookResultSet.getString("author"));
                            book.setGenre(bookResultSet.getString("genre"));
                            book.setType(bookResultSet.getString("type"));
                            book.setDate(bookResultSet.getString("date"));
                            book.setPublisher(bookResultSet.getString("publisher"));
                            book.setDescription(bookResultSet.getString("description"));
                            book.setImage(bookResultSet.getString("image"));
                            book.setBorrowed(bookResultSet.getBoolean("borrowed"));
                            cartItems.add(book);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    private static String getReturnDate(){
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return returnDate.format(formatter);
    }

    private static void removeBookFromCart(String userID, String bookID) {
        try (Connection connection = HomePage.getConnection()) {
            // Get the title of the book using the bookID
            String sql = "SELECT title FROM Books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String bookTitle = resultSet.getString("title");

                // Get the media and mediaIDs from the cart
                sql = "SELECT media1, media2, media3, media1ID, media2ID, media3ID FROM cart WHERE userID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, userID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String media1 = resultSet.getString("media1");
                    String media2 = resultSet.getString("media2");
                    String media3 = resultSet.getString("media3");

                    // Check if any of the media matches the book title and set it and its corresponding mediaID to NULL
                    if (bookTitle.equals(media1)) {
                        sql = "UPDATE cart SET media1 = NULL, media1ID = NULL WHERE userID = ?";
                    } else if (bookTitle.equals(media2)) {
                        sql = "UPDATE cart SET media2 = NULL, media2ID = NULL WHERE userID = ?";
                    } else if (bookTitle.equals(media3)) {
                        sql = "UPDATE cart SET media3 = NULL, media3ID = NULL WHERE userID = ?";
                    }

                    statement = connection.prepareStatement(sql);
                    statement.setString(1, userID);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
