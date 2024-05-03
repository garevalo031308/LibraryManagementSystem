package Main.Librarian;

import Main.*;
import Main.Media.BooksProperty;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

// TODO fix table size

public class LibrarianCatalogPage {

    public static void librarianCatalogPage(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Header.getHeader(stage, root);

        Label catalogLabel = new Label("LibraHub Media Catalog");
        catalogLabel.setLayoutX(447);
        catalogLabel.setLayoutY(136);
        catalogLabel.setFont(Font.font(36));
        catalogLabel.setUnderline(true);

        // ---- Book Table ---- //

        TableView<BooksProperty> bookTable = new TableView<>();
        bookTable.setPrefSize(1075, 448);
        bookTable.setLayoutX(100);
        bookTable.setLayoutY(225);

        TableColumn<BooksProperty, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty());

        TableColumn<BooksProperty, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titlePropertyProperty());

        TableColumn<BooksProperty, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorPropertyProperty());

        TableColumn<BooksProperty, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genrePropertyProperty());

        TableColumn<BooksProperty, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typePropertyProperty());

        TableColumn<BooksProperty, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().datePropertyProperty());

        TableColumn<BooksProperty, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherPropertyProperty());

        TableColumn<BooksProperty, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionPropertyProperty());

        TableColumn<BooksProperty, String> borrowColumn = new TableColumn<>("Borrowed");
        borrowColumn.setCellValueFactory(cellData -> cellData.getValue().borrowedPropertyProperty());

        bookTable.getItems().addAll(getAllBooks());

        bookTable.getColumns().addAll(idColumn, titleColumn, authorColumn, genreColumn, typeColumn, dateColumn, publisherColumn, descriptionColumn, borrowColumn);

        Button addMedia = new Button("Add Media");
        addMedia.setPrefSize(277, 75);
        addMedia.setLayoutX(100);
        addMedia.setLayoutY(750);
        addMedia.setFont(Font.font(35));
        addMedia.setStyle("-fx-background-color:  #6DA6C5");

        Button editMedia = new Button("Edit Media");
        editMedia.setPrefSize(277, 75);
        editMedia.setLayoutX(501);
        editMedia.setLayoutY(750);
        editMedia.setFont(Font.font(35));
        editMedia.setStyle("-fx-background-color:  #A9DDD6");
        editMedia.setDisable(true);

        Button removeMedia = new Button("Remove Media");
        removeMedia.setPrefSize(277.2, 75);
        removeMedia.setLayoutX(899);
        removeMedia.setLayoutY(750);
        removeMedia.setFont(Font.font(35));
        removeMedia.setStyle("-fx-background-color:  #FF5A5F");
        removeMedia.setDisable(true);

        root.getChildren().addAll(catalogLabel, bookTable, addMedia, editMedia, removeMedia);
        stage.setTitle("Library Management System - Librarian Catalog Page");
        stage.getIcons().add(new Image(Objects.requireNonNull(LibrarianCatalogPage.class.getResourceAsStream("/Images/Main/libgenlogo.png"))));
        stage.setScene(scene);
        stage.show();

        bookTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editMedia.setDisable(false);
                removeMedia.setDisable(false);
                String bookId = newSelection.getIdProperty();
                editMedia.setOnAction(e-> EditingMediaPage.editingMediaPage(stage, bookId));

                removeMedia.setOnAction(e -> {
                    BooksProperty selectedBook = bookTable.getSelectionModel().getSelectedItem();
                    if (selectedBook.getBorrowedProperty().equals("Yes")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("This book is currently borrowed and cannot be removed.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to remove this book? This action is irreversible.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK){
                            removeBookFromDatabase(bookId);
                            bookTable.getItems().remove(selectedBook);
                        }
                    }
                });
            }
        });

        addMedia.setOnAction(e-> AddingMediaPage.addingMediaPage(stage));
    }

    private static void removeBookFromDatabase(String bookId) {
        try (Connection connection = HomePage.getConnection()){
            String SQL = "DELETE FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<BooksProperty> getAllBooks() {
        ArrayList<BooksProperty> books = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StringProperty id = new SimpleStringProperty(resultSet.getString("id"));
                StringProperty title = new SimpleStringProperty(resultSet.getString("title"));
                StringProperty author = new SimpleStringProperty(resultSet.getString("author"));
                StringProperty genre = new SimpleStringProperty(resultSet.getString("genre"));
                StringProperty type = new SimpleStringProperty(resultSet.getString("type"));
                StringProperty date = new SimpleStringProperty(resultSet.getString("date"));
                StringProperty publisher = new SimpleStringProperty(resultSet.getString("publisher"));
                StringProperty description = new SimpleStringProperty(resultSet.getString("description"));
                StringProperty borrowed = new SimpleStringProperty(resultSet.getString("borrowed"));

                books.add(new BooksProperty(id, title, author, genre, type, date, publisher, description, borrowed));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
