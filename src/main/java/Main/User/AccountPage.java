package Main.User;

import Main.Header;
import Main.HomePage;
import Main.Media.Books;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;
import java.util.Objects;

public class AccountPage {

    public static void accountPage(Stage stage, String ID) {
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280, 900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

        String[] userDetails = getUserDetails(ID);

        Label idLabel = new Label("ID: " + ID);
        idLabel.setLayoutX(41);
        idLabel.setLayoutY(487);
        idLabel.setFont(Font.font(35));

        Label nameLabel = new Label("Name: " + userDetails[0]);
        nameLabel.setLayoutX(41);
        nameLabel.setLayoutY(560);
        nameLabel.setFont(Font.font(35));

        Label emailLabel = new Label("Email: " + userDetails[1]);
        emailLabel.setLayoutX(41);
        emailLabel.setLayoutY(638);
        emailLabel.setFont(Font.font(35));
        emailLabel.setWrapText(true);
        emailLabel.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        emailLabel.setPrefSize(334, 235);


        ImageView userProfilePic = new ImageView();
        Image imgage = new Image(Objects.requireNonNull(AccountPage.class.getResourceAsStream("/Images/UserProfile/DefaultPFP.png")));
        userProfilePic.setImage(imgage);
        userProfilePic.setFitHeight(276);
        userProfilePic.setFitWidth(276);
        userProfilePic.setLayoutX(41);
        userProfilePic.setLayoutY(154);

        Label borrowedBooksLabel = new Label("Borrowed Books:");
        borrowedBooksLabel.setLayoutX(413);
        borrowedBooksLabel.setLayoutY(165);
        borrowedBooksLabel.setFont(Font.font(25));

        Label transactionHistoryLabel = new Label("Transaction History:");
        transactionHistoryLabel.setLayoutX(839);
        transactionHistoryLabel.setLayoutY(165);
        transactionHistoryLabel.setFont(Font.font(22));

        // ----- Table ----- //
        TableView<Transaction> transactions = new TableView<>();
        transactions.setPrefSize(469, 638);
        transactions.setLayoutX(761);
        transactions.setLayoutY(205);

        TableColumn<Transaction, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cd -> cd.getValue().titleProperty());

        TableColumn<Transaction, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cd -> cd.getValue().authorProperty());

        TableColumn<Transaction, String> dateCheckedOutColumn = new TableColumn<>("Date Checked Out");
        dateCheckedOutColumn.setCellValueFactory(cd -> cd.getValue().dateCheckedOutProperty());

        TableColumn<Transaction, String> dateDueColumn = new TableColumn<>("Date Due");
        dateDueColumn.setCellValueFactory(cd -> cd.getValue().dateDueProperty());

        TableColumn<Transaction, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cd -> cd.getValue().statusProperty());

        ArrayList<Transaction> transactionData = getTransactionData(ID);
        transactions.getItems().addAll(transactionData);

        transactions.getColumns().addAll(titleColumn, authorColumn, dateCheckedOutColumn, dateDueColumn, statusColumn);


        root.getChildren().addAll(transactions, nameLabel, emailLabel, idLabel, userProfilePic, borrowedBooksLabel, transactionHistoryLabel);
        Header.getHeader(stage, root);
        stage.setTitle("Account Page");
        stage.setScene(scene);
        stage.show();

        createBorrowedBooksBox(Header.currentLoggedInUser, root, stage);
    }

    public static String[] getUserDetails(String userID) {
        String[] userDetails = new String[2];

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT first_name, last_name, email FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userDetails[0] = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                userDetails[1] = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDetails;
    }

    public static ArrayList<Transaction> getTransactionData(String userID) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT t.borrow_date, t.return_date, t.status, b.title, b.author, b.id " +
                    "FROM transactions t JOIN books b ON t.book_id = b.id " +
                    "WHERE t.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String dateCheckedOut = resultSet.getString("borrow_date");
                String dateDue = resultSet.getString("return_date");
                String status = resultSet.getString("status");
                String bookID = resultSet.getString("id");

                transactions.add(new Transaction(title, author, dateCheckedOut, dateDue, status, bookID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static void createBorrowedBooksBox(String ID, Group root, Stage stage) {
        ArrayList<Books> books = getBorrowedBooks(ID);
        for (int i = 0; i < books.size(); i++){

            ImageView cover = new ImageView();
            Image img = new Image(String.valueOf(Objects.requireNonNull(AccountPage.class.getResource(books.get(i).getImage()))));
            cover.setImage(img);
            cover.setFitHeight(150);
            cover.setFitWidth(200);
            cover.setLayoutX(406);
            cover.setLayoutY(217 + (i*195));
            cover.setPreserveRatio(true);

            Label title = new Label(books.get(i).getTitle());
            title.setLayoutX(513);
            title.setLayoutY(217 + (i*195));

            Label author = new Label(books.get(i).getAuthor());
            author.setLayoutX(513);
            author.setLayoutY(252 + (i*195));

            Label dueDate = new Label("Due Date: " + getDueDate(books.get(i).getID()));
            dueDate.setLayoutX(513);
            dueDate.setLayoutY(350 + (i*195));

            Button returnButton = new Button("Return");
            returnButton.setLayoutX(513);
            returnButton.setLayoutY(318 + (i*195));

            int finalI = i;
            returnButton.setOnAction(e -> {
                returnBook(ID, books.get(finalI).getID());
                // Refresh the page or navigate to a different page
                AccountPage.accountPage(stage, ID);
            });

            root.getChildren().addAll(cover, title, author, dueDate, returnButton);
        }
    }

    public static void returnBook(String userID, String bookID) {
        try (Connection connection = HomePage.getConnection()) {
            // Remove the book from the borrowed_books table
            String sql = "DELETE FROM borrowed_books WHERE user_id = ? AND book_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, bookID);
            statement.executeUpdate();

            // Update the book's status in the transactions table to "returned"
            sql = "UPDATE transactions SET status = 'returned' WHERE user_id = ? AND book_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, bookID);
            statement.executeUpdate();

            // Update the borrowed column in the books table back to false
            sql = "UPDATE books SET borrowed = false WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDueDate(String bookID) {
        String dueDate = null;

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT return_date FROM borrowed_books WHERE book_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                dueDate = resultSet.getString("return_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dueDate;
    }

    public static ArrayList<Books> getBorrowedBooks(String userID) {
        ArrayList<Books> borrowedBooks = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT b.* FROM borrowed_books bb JOIN books b ON bb.book_id = b.id WHERE bb.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create a new Books object and set its fields using the setter methods
                Books book = new Books();
                book.setID(String.valueOf(resultSet.getInt("id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setImage(resultSet.getString("image"));
                borrowedBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }
}
