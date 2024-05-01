package Main.Media;


import Main.Header;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static Main.HomePage.getConnection;


public class CatalogPage {

    public static void catalogPage(Stage stage, String searchQuery){
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

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

        Button filterButton = new Button("Set Filter");
        filterButton.setLayoutX(66);
        filterButton.setLayoutY(737);
        filterButton.setPrefSize(67.3, 25);

        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(1082, 770);
        ap.setStyle("-fx-background-color: #F4CE90");

        ScrollPane sp = new ScrollPane(ap);
        sp.setPrefSize(1082, 769);
        sp.setLayoutX(200);
        sp.setLayoutY(132);
        sp.setFitToWidth(true);
        sp.setFitToHeight(false);

        stage.getIcons().add(new Image(Objects.requireNonNull(CatalogPage.class.getResourceAsStream("/Images/Main/libgenlogo.png")))); //sets icon
        root.getChildren().addAll(filterPane,sp); //add all the elements to the root
        Header.getHeader(stage, root);
        filterPane.getChildren().addAll(sortByLabel, filterButton, titleButton, authorButton, dateNewButton, dateOldButton, sortmediasep);
        filterPane.getChildren().addAll(mediaLabel, bookCheck, eBookCheck, mediaSep);
        filterPane.getChildren().addAll(genreLabel, fictionCheck, scienceFictionCheck, fantasyCheck, mysteryCheck, horrorCheck, dramaCheck, MythologyCheck, nonFictionCheck, genreSep);
        stage.setTitle("Catalog");
        stage.setScene(scene);

        ArrayList<String> genres = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        filterButton.setOnAction(e -> {
            ap.getChildren().clear();
            genres.clear();
            types.clear();
            addIfSelected(genres, fictionCheck, scienceFictionCheck, "Science Fiction");
            addIfSelected(genres, fictionCheck, fantasyCheck, "Fantasy");
            addIfSelected(genres, fictionCheck, mysteryCheck, "Mystery");
            addIfSelected(genres, fictionCheck, horrorCheck, "Horror");
            addIfSelected(genres, fictionCheck, dramaCheck, "Drama");
            addIfSelected(genres, fictionCheck, MythologyCheck, "Mythology");
            addIfSelected(genres, nonFictionCheck, "Non-Fiction");

            addIfSelected(types, bookCheck, "Book");
            addIfSelected(types, eBookCheck, "E-Book");

            if (titleButton.isSelected()) {
                createBookBox(stage, ap, "Title", genres, types, searchQuery);
            } else if (authorButton.isSelected()) {
                createBookBox(stage, ap, "Author", genres, types, searchQuery);
            } else if (dateNewButton.isSelected()) {
                createBookBox(stage, ap, "Date (Newest)", genres, types, searchQuery);
            } else if (dateOldButton.isSelected()) {
                createBookBox(stage, ap, "Date (Oldest)", genres, types, searchQuery);
            }
        });

        createBookBox(stage, ap, "Title", genres, types, searchQuery);
        stage.show();

    }

    // Method that creates a book "box"
    // Need to get book info from database
    public static void createBookBox(Stage stage, AnchorPane anchorPane, String sort, ArrayList<String> genreFilter, ArrayList<String> typeFilter, String searchQuery){
        ArrayList<Books> books = bookDatabase(); // List of books in database
        books.sort((b1, b2) -> switch (sort) {
            case "Title" -> b1.title.compareTo(b2.title);
            case "Author" -> b1.author.compareTo(b2.author);
            case "Date (Newest)" -> b2.date.compareTo(b1.date);
            case "Date (Oldest)" -> b1.date.compareTo(b2.date);
            default -> 0;
        });

        if (searchQuery != null && !searchQuery.isEmpty()) {
            books.removeIf(book -> !book.title.toLowerCase().contains(searchQuery.toLowerCase()) && !book.author.toLowerCase().contains(searchQuery.toLowerCase()));
        }

        // Filter books based on selected genres
        if (!genreFilter.isEmpty()) {
            books.removeIf(book -> !genreFilter.contains(book.genre));
        }

        // Filter books based on selected types
        if (!typeFilter.isEmpty()) {
            books.removeIf(book -> !typeFilter.contains(book.type));
        }

        for (int i = 0; i < books.size(); i++){

            ImageView cover = new ImageView();
            Image coverImage = new Image(Objects.requireNonNull(CatalogPage.class.getResourceAsStream(books.get(i).image)));
            cover.setImage(coverImage);
            cover.setPreserveRatio(true);
            cover.setFitHeight(229);
            cover.setFitWidth(169);
            cover.setLayoutX(14);
            cover.setLayoutY(14 + (i*294));

            Label genre = new Label("Genre: " + books.get(i).genre);
            genre.setFont(Font.font(14));
            genre.setLayoutX(14);
            genre.setLayoutY(243 + (i*294));

            Label type = new Label("Type: " + books.get(i).type);
            type.setFont(Font.font(12));
            type.setLayoutX(14);
            type.setLayoutY(265 + (i*294));

            Label borrowed = new Label("Borrowed: " + books.get(i).borrowed);
            borrowed.setFont(Font.font(12));
            borrowed.setLayoutX(14);
            borrowed.setLayoutY(285 + (i*294));

            Label title = new Label(books.get(i).title);
            title.setFont(Font.font(22));
            title.setStyle("-fx-font-weight: bold");
            title.prefHeight(32);
            title.setLayoutX(175);
            title.setLayoutY(14 + (i*294));

            Label author = new Label(books.get(i).author);
            author.setFont(Font.font(16));
            author.setStyle("-fx-font-weight: bold");
            author.setLayoutX(175);
            author.setLayoutY(41 + (i*294));

            Label description = new Label(books.get(i).description);
            description.setWrapText(true);
            description.setFont(Font.font(12));
            description.setLayoutX(175);
            description.setLayoutY(62 + (i*292));
            description.setPrefSize(878, 240);

            anchorPane.getChildren().addAll(cover, genre, type, borrowed, title, author, description);
            int finalI = i;
            cover.setOnMouseClicked(e->{
                BookPopUp.bookPopUp(stage, books.get(finalI));
            });
        }
    }

    private static ArrayList<Books> bookDatabase() {
        ArrayList<Books> books = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Books book = new Books();
                book.setID(String.valueOf(resultSet.getInt("id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setType(resultSet.getString("type"));
                book.setBorrowed(resultSet.getBoolean("borrowed"));
                book.setImage(resultSet.getString("image"));
                book.setDate(resultSet.getString("date"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    private static void addIfSelected(ArrayList<String> list, CheckBox mainCheckBox, CheckBox checkBox, String value) {
        if (mainCheckBox.isSelected() || checkBox.isSelected()) {
            list.add(value);
        }
    }

    private static void addIfSelected(ArrayList<String> list, CheckBox checkBox, String value) {
        if (checkBox.isSelected()) {
            list.add(value);
        }
    }
}
