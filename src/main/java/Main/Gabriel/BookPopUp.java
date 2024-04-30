package Main.Gabriel;


import Main.HomePage;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;


public class BookPopUp {

    public static void bookPopUp(Stage stage, Books book){
        Stage popup = new Stage();
        popup.initOwner(stage);
        popup.initModality(Modality.APPLICATION_MODAL);

        Group root = new Group();
        Scene scene = new Scene(root, 616,  556);
        scene.setFill(Paint.valueOf("#F4CE90"));

        Rectangle header = new Rectangle();
        header.setWidth(622);
        header.setHeight(132);
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView();
        Image img = new Image(Objects.requireNonNull(BookPopUp.class.getResourceAsStream("/Images/Main/libgenlogo.png")));
        logo.setImage(img);
        logo.setFitHeight(124);
        logo.setFitWidth(122);
        logo.setLayoutX(6);
        logo.setLayoutY(6);

        Label title = new Label("LibraHub");
        title.setLayoutX(143);
        title.setLayoutY(7);
        title.setFont(Font.font(80));

        ImageView bookCover = new ImageView();
        Image bookCoverImg = new Image(Objects.requireNonNull(BookPopUp.class.getResourceAsStream(book.image)));
        bookCover.setImage(bookCoverImg);
        bookCover.setFitHeight(247);
        bookCover.setFitWidth(152);
        bookCover.setLayoutX(10);
        bookCover.setLayoutY(141);
        bookCover.setPreserveRatio(true);

        Label bookTitle = new Label(book.title);
        bookTitle.setLayoutX(171);
        bookTitle.setLayoutY(141);
        bookTitle.setFont(Font.font("System Bold", 32.0));

        Label author = new Label("Author: " + book.author);
        author.setLayoutX(168);
        author.setLayoutY(188);
        author.setFont(Font.font(18.0));

        Label borrowed = new Label("Borrowed: " + book.borrowed);
        borrowed.setLayoutX(10.0);
        borrowed.setLayoutY(369.0);
        borrowed.setFont(Font.font(16));

        Label date = new Label("Date: " + book.date);
        date.setLayoutX(10.0);
        date.setLayoutY(429.0);
        date.setFont(Font.font(14.0));

        Label description = new Label(book.description);
        description.setLayoutX(168.0);
        description.setLayoutY(215.0);
        description.setWrapText(true);
        description.setPrefSize(446, 339);
        description.setAlignment(Pos.TOP_LEFT);

        Label genre = new Label("Genre: " + book.genre);
        genre.setLayoutX(10.0);
        genre.setLayoutY(399.0);
        genre.setFont(Font.font(16));

        Label type = new Label("Type: " + book.type);
        type.setLayoutX(10.0);
        type.setLayoutY(449.0);

        Label bookID = new Label("BookID: " + book.id);
        bookID.setLayoutX(10.0);
        bookID.setLayoutY(466.0);

        Button addToCartButton = new Button("Add To Cart");
        addToCartButton.setLayoutX(10.0);
        addToCartButton.setLayoutY(517.0);

        Button backButton = new Button("Back/Exit");
        backButton.setLayoutX(102.0);
        backButton.setLayoutY(517.0);

        root.getChildren().addAll(borrowed, date, description, genre, type, bookID, addToCartButton, backButton);
        root.getChildren().addAll(header, logo, title, bookCover, bookTitle, author);
        popup.setScene(scene);
        popup.show();
        popup.getIcons().add(new Image(String.valueOf(Objects.requireNonNull(BookPopUp.class.getResource("/Images/Main/libgenlogo.png")))));

        addToCartButton.setOnAction(e->addToCheckoutDatabase("4440486", Integer.valueOf(book.id), book.title));

    }

    // TODO check to see if customer is logged in
    // TODO check to see if customer already has a checkout "document", if so append book to it, else create a new one
    // TODO add book to checkout database
    // TODO remove cart when user logs out/application is closed
    private static void addToCheckoutDatabase(String ID, Integer BookID, String BookTitle){
        if (checkIfHasCart(ID)){
            try (Connection connection = HomePage.getConnection()){
                String sql = "SELECT media1ID, media2ID, media3ID FROM cart WHERE userID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, ID);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String updateSql = null;
                    if (resultSet.getObject("media1ID") == null) {
                        updateSql = "UPDATE cart SET media1 = ?, media1ID = ? WHERE userID = ?";
                    } else if (resultSet.getObject("media2ID") == null) {
                        updateSql = "UPDATE cart SET media2 = ?, media2ID = ? WHERE userID = ?";
                    } else if (resultSet.getObject("media3ID") == null) {
                        updateSql = "UPDATE cart SET media3 = ?, media3ID = ? WHERE userID = ?";
                    }

                    if (updateSql != null) {
                        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                        updateStatement.setString(1, BookTitle);
                        updateStatement.setInt(2, BookID);
                        updateStatement.setString(3, ID);
                        updateStatement.executeUpdate();
                        System.out.println("Book added to cart.");
                    } else {
                        System.out.println("Cart is full. Cannot add more items.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO check to see if customer has a cart in database
    private static boolean checkIfHasCart(String userID){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT COUNT(*) FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    return true;
                } else {
                    createUserCart(userID);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static void createUserCart(String userID){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "INSERT INTO cart (userID, cartID) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, createRandomID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String createRandomID(){
        // This will create a random ID for the media
        StringBuilder ID = new StringBuilder();
        for(int i = 0; i < 7; i++){
            Random rand = new Random();
            ID.append(rand.nextInt(0,9));
        }
        if (checkCartID(ID.toString())){
            createRandomID();
        }
        return ID.toString();
    }

    private static boolean checkCartID(String cartID){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT COUNT(*) FROM cart WHERE cartID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cartID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
