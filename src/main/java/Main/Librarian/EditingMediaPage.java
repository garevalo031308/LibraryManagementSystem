package Main.Librarian;

//nessesary import packages
import Main.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EditingMediaPage {

    public static void editingMediaPage(Stage stage, String bookID) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 900);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Header.getHeader(stage, root);


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

        Button clearSelectionButton = new Button("Clear Selection");
        clearSelectionButton.setLayoutX(43);
        clearSelectionButton.setLayoutY(854);
        clearSelectionButton.setOnAction(e -> fictionGroup.selectToggle(null));

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

        fillBookInfo(bookID, titleField, authorField, idField, nonFictionRadioButton, fictionRadioButton, fictionGroup, booksRadioButton, ebooksRadioButton, publicationDateField, publisherField, descriptionArea, imageLocationField);

        mainGenreGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == fictionRadioButton) {
                scienceFictionRadioButton.setDisable(false);
                fantasyRadioButton.setDisable(false);
                mysteryRadioButton.setDisable(false);
                horrorRadioButton.setDisable(false);
                dramaRadioButton.setDisable(false);
                mythologyRadioButton.setDisable(false);
            } else if (newValue == nonFictionRadioButton) {
                scienceFictionRadioButton.setDisable(true);
                fantasyRadioButton.setDisable(true);
                mysteryRadioButton.setDisable(true);
                horrorRadioButton.setDisable(true);
                dramaRadioButton.setDisable(true);
                mythologyRadioButton.setDisable(true);
            }
        });

        addMediaButton.setOnAction(e -> {
            String titletext = titleField.getText();
            String author = authorField.getText();
            String genre = "";
            if (nonFictionRadioButton.isSelected()) {
                genre = nonFictionRadioButton.getText();
            }
            if (fictionRadioButton.isSelected()) {
                RadioButton selectedFiction = (RadioButton) fictionGroup.getSelectedToggle();
                if (selectedFiction != null) {
                    genre = selectedFiction.getText();
                } else {
                    genre = fictionRadioButton.getText();
                }
            }
            String type = "";
            if (booksRadioButton.isSelected()) {
                type = booksRadioButton.getText();
            } else if (ebooksRadioButton.isSelected()) {
                type = ebooksRadioButton.getText();
            }
            String date = publicationDateField.getText();
            String publisher = publisherField.getText();
            String description = descriptionArea.getText();
            String image = imageLocationField.getText();
            boolean updateSuccessful = updateBookInfo(bookID, titletext, author, genre, type, date, publisher, description, image);
            Alert alert = new Alert(updateSuccessful ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(updateSuccessful ? "Success" : "Error");
            alert.setHeaderText(null);
            alert.setContentText(updateSuccessful ? "Book information updated successfully." : "An error occurred while updating the book information.");
            alert.showAndWait();
            LibrarianCatalogPage.librarianCatalogPage(stage);
        });
    }

    public static boolean updateBookInfo(String bookId, String title, String author, String genre, String type, String date, String publisher, String description, String image) {
        try (Connection connection = HomePage.getConnection()) {
            String sql = "UPDATE books SET title = ?, author = ?, genre = ?, type = ?, date = ?, publisher = ?, description = ?, image = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, genre);
            statement.setString(4, type);
            statement.setString(5, date);
            statement.setString(6, publisher);
            statement.setString(7, description);
            statement.setString(8, image);
            statement.setString(9, bookId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void fillBookInfo(String bookId, TextField titleField, TextField authorField, TextField idField, RadioButton nonFictionRadioButton, RadioButton fictionRadioButton, ToggleGroup fictionGroup, RadioButton booksRadioButton, RadioButton ebooksRadioButton, TextField publicationDateField, TextField publisherField, TextArea descriptionArea, TextField imageLocationField) {
        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                titleField.setText(resultSet.getString("title"));
                authorField.setText(resultSet.getString("author"));
                idField.setText(resultSet.getString("id"));
                // Set the genre radio buttons based on the genre from the database
                String genre = resultSet.getString("genre");
                if (genre.equals(nonFictionRadioButton.getText())) {
                    nonFictionRadioButton.setSelected(true);
                } else {
                    fictionRadioButton.setSelected(true);
                    // Set the fictionGroup radio buttons based on the genre
                    for (Toggle toggle : fictionGroup.getToggles()) {
                        RadioButton radioButton = (RadioButton) toggle;
                        if (radioButton.getText().equals(genre)) {
                            radioButton.setSelected(true);
                            break;
                        }
                    }
                }
                // Set the type radio buttons based on the type from the database
                String type = resultSet.getString("type");
                if (type.equals(booksRadioButton.getText())) {
                    booksRadioButton.setSelected(true);
                } else {
                    ebooksRadioButton.setSelected(true);
                }
                publicationDateField.setText(resultSet.getString("date"));
                publisherField.setText(resultSet.getString("publisher"));
                descriptionArea.setText(resultSet.getString("description"));
                imageLocationField.setText(resultSet.getString("image"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
