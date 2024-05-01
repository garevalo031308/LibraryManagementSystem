package Main;

import Main.Chris.AboutUsPage;
import Main.Gabriel.CatalogPage;
import Main.Gabriel.CheckoutPage;
import Main.Sukeer.LoginPage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import java.util.ArrayList;
import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;
// TODO fix table size

public class LibrarianCatalogPage extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

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
        addMedia.setDisable(true);

        Button editMedia = new Button("Edit Media");
        editMedia.setPrefSize(277, 75);
        editMedia.setLayoutX(501);
        editMedia.setLayoutY(750);
        editMedia.setFont(Font.font(35));
        editMedia.setStyle("-fx-background-color:  #A9DDD6");
        editMedia.setDisable(true);

        Button removeMedia = new Button("Remove Media");
        removeMedia.setPrefSize(277, 75);
        removeMedia.setLayoutX(899);
        removeMedia.setLayoutY(750);
        removeMedia.setFont(Font.font(35));
        removeMedia.setStyle("-fx-background-color:  #FF5A5F");
        removeMedia.setDisable(true);

        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);
        root.getChildren().addAll(catalogLabel, bookTable, addMedia, editMedia, removeMedia);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    private ArrayList<BooksProperty> getAllBooks() {
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
