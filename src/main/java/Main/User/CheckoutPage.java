package Main.User;

import Main.*;
import Main.Media.Books;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
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

import static Main.Header.removeCart;

public class CheckoutPage {

    public static void checkoutPage(Stage stage, String ID){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set
        stage.setResizable(false);
        stage.setFullScreen(false);

        Header.getHeader(stage, root);

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

        stage.setTitle("Cart");// sets current scene
        stage.getIcons().add(new Image(Objects.requireNonNull(CheckoutPage.class.getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(forUser); //adds all the elements to the root
        root.getChildren().addAll(shoppingCartLabel, checkoutButton);
        stage.setScene(scene); //sets the scene
        stage.show(); //shows the stage

        ArrayList<Books> cartItems = getCartItems(ID);

        createCart(root, cartItems, ID, stage);

        checkoutButton.setOnAction(e -> {
            if (canCheckout(ID)) {
                if(checkoutBooks(ID, cartItems)){
                    HomePage home = new HomePage();
                    home.start(stage);
                }
                // Refresh the page or navigate to a different page
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Checkout Error");
                alert.setHeaderText(null);
                alert.setContentText("You cannot checkout at this time. Please try again later.");
                alert.showAndWait();
            }
        });
    }


    private static void createCart(Group root, ArrayList<Books> cartItems, String ID, Stage stage){

        for (int i = 0; i < cartItems.size(); i++) {
            ImageView bookview = new ImageView();
            Image bookImg = new Image(Objects.requireNonNull(CheckoutPage.class.getResourceAsStream(cartItems.get(i).getImage())));
            bookview.setImage(bookImg);
            bookview.setFitWidth(200);
            bookview.setFitHeight(150);
            bookview.setLayoutX(57);
            bookview.setLayoutY(235 + (i*161));
            bookview.setPreserveRatio(true);

            Label title = new Label(cartItems.get(i).getTitle());
            title.setLayoutX(175);
            title.setLayoutY(242 + (i*154));
            title.setFont(Font.font(28));

            Label author = new Label(cartItems.get(i).getAuthor());
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

    private static boolean checkoutBooks(String userID, ArrayList<Books> cartItems) {
        try (Connection connection = HomePage.getConnection()) {
            for (Books book : cartItems) {
                // Insert into borrowed_books table
                String sql = "INSERT INTO borrowed_books (user_id, book_id, borrowed_date, return_date) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, userID);
                statement.setString(2, book.getID());
                statement.setString(3, LocalDate.now().toString());
                statement.setString(4, getReturnDate());
                statement.executeUpdate();

                // Insert into transactions table
                // Insert into transactions table
                sql = "INSERT INTO transactions (user_id, book_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, userID);
                statement.setString(2, book.getID());
                statement.setString(3, LocalDate.now().toString());
                statement.setString(4, getReturnDate());
                statement.setString(5, "Borrowed");
                statement.executeUpdate();
            }

            // Remove the user's cart
            removeCart(userID);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String getReturnDate(){
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return returnDate.format(formatter);
    }

    private static void removeBookFromCart(String userID, String bookID) {
        try (Connection connection = HomePage.getConnection()) {
            // Get the mediaIDs from the cart
            String sql = "SELECT media1ID, media2ID, media3ID FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String media1ID = resultSet.getString("media1ID");
                String media2ID = resultSet.getString("media2ID");
                String media3ID = resultSet.getString("media3ID");

                // Check if any of the mediaIDs matches the bookID and set it to NULL
                if (bookID.equals(media1ID)) {
                    sql = "UPDATE cart SET media1ID = NULL WHERE userID = ?";
                } else if (bookID.equals(media2ID)) {
                    sql = "UPDATE cart SET media2ID = NULL WHERE userID = ?";
                } else if (bookID.equals(media3ID)) {
                    sql = "UPDATE cart SET media3ID = NULL WHERE userID = ?";
                }

                statement = connection.prepareStatement(sql);
                statement.setString(1, userID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
