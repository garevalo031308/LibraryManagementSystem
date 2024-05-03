package Main;

import Main.Admin.AdminPage;
import Main.Librarian.LibrarianCatalogPage;
import Main.Librarian.UserLookupPage;
import Main.Media.BookPopUp;
import Main.Media.CatalogPage;
import Main.User.AccountPage;
import Main.User.CheckoutPage;
import Main.User.LoginPage;
import javafx.scene.Group;
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
import java.util.Objects;


public class Header {

    public static String currentLoggedInUser = "";
    public static String currentLoggedInUserRole = "";
    private static Label loginLabel;

    public static void getHeader(Stage stage, Group root){
        HomePage home = new HomePage();
        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image logoimg = new Image(Objects.requireNonNull(Header.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
        logo.setImage(logoimg); //set image
        logo.setFitHeight(124);
        logo.setFitWidth(122);
        logo.setLayoutX(8);
        logo.setLayoutY(6);
        logo.setOnMouseClicked(e-> {
            try {
                home.start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Label title = new Label("LibraHub");
        title.setLayoutX(154);
        title.setLayoutY(7);
        title.setFont(Font.font(80));
        title.setOnMouseClicked(e-> {
            try {
                home.start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button account = new Button("Account");
        account.setLayoutX(525);
        account.setLayoutY(32);
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
        catalog.setLayoutX(680);
        catalog.setLayoutY(32);
        catalog.setOnAction(e-> CatalogPage.catalogPage(stage, ""));

        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(835);
        aboutus.setLayoutY(32);
        aboutus.setOnAction(e-> AboutUsPage.aboutUsPage(stage));

        loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);
        loginLabel.setOnMouseClicked(e-> {
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Log Out Confirmation");
                alert.setContentText("Are you sure you want to log out?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        removeCart(currentLoggedInUser);
                        currentLoggedInUser = "";
                        currentLoggedInUserRole = "";
                        updateLoginLabel();
                        try {
                            home.start(stage);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        });

        ImageView cartimage = new ImageView();
        Image cart = new Image(Objects.requireNonNull(Header.class.getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> {
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                if(BookPopUp.checkIfHasCart(currentLoggedInUser)){
                    CheckoutPage.checkoutPage(stage, currentLoggedInUser);
                }
            }
        });

        TextField searchBar = new TextField();
        searchBar.setPromptText("Type a title, author, etc. here");
        searchBar.setPrefSize(226, 26);
        searchBar.setLayoutX(972);
        searchBar.setLayoutY(83);

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(1198);
        searchButton.setLayoutY(83);
        searchButton.setOnAction(e -> {
            String searchQuery = searchBar.getText();
            CatalogPage.catalogPage(stage, searchQuery);
        });

        updateLoginLabel();
        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage, searchBar, searchButton);
        if (currentLoggedInUserRole.equals("admin")) {
            addAdminHeader(root, stage);
            addLibrarianHeader(root,stage);
        } else if (currentLoggedInUserRole.equals("librarian")) {
            addLibrarianHeader(root, stage);
        }
    }

    public static void getHomeHeader(Stage stage, Group root){
        HomePage home = new HomePage();
        Rectangle header = new Rectangle();//new rectangle within the stage
        header.setWidth(1280); //set width
        header.setHeight(132); //set height
        header.setFill(Paint.valueOf("#FF5A5F"));

        ImageView logo = new ImageView(); //new image view
        Image img = new Image(Objects.requireNonNull(Header.class.getResourceAsStream("/Images/Main/libgenlogo.png"))); //get image from the path
        logo.setImage(img); //set image
        logo.setFitHeight(124);
        logo.setFitWidth(122);
        logo.setLayoutX(8);
        logo.setLayoutY(6);

        Label title = new Label("LibraHub");
        title.setLayoutX(154);
        title.setLayoutY(7);
        title.setFont(Font.font(80));
        title.setOnMouseClicked(e-> {
            try {
                home.start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button account = new Button("Account");
        account.setLayoutX(525);
        account.setLayoutY(32);
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
        catalog.setLayoutX(680);
        catalog.setLayoutY(32);
        catalog.setOnAction(e-> CatalogPage.catalogPage(stage, ""));

        Button aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: #363732");
        aboutus.setTextFill(Paint.valueOf("white"));
        aboutus.setPrefHeight(67);
        aboutus.setPrefWidth(109);
        aboutus.setLayoutX(835);
        aboutus.setLayoutY(32);
        aboutus.setOnAction(e-> AboutUsPage.aboutUsPage(stage));

        loginLabel = new Label("Log In");
        loginLabel.setLayoutX(1164);
        loginLabel.setLayoutY(6);
        loginLabel.setFont(Font.font(13));
        loginLabel.setUnderline(true);
        loginLabel.setOnMouseClicked(e-> {
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Log Out Confirmation");
                alert.setContentText("Are you sure you want to log out?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        removeCart(currentLoggedInUser);
                        currentLoggedInUser = "";
                        currentLoggedInUserRole = "";
                        updateLoginLabel();
                        try {
                            home.start(stage);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
            if (currentLoggedInUserRole.equals("admin")) {
                addAdminHeader(root, stage);
                addLibrarianHeader(root,stage);
            } else if (currentLoggedInUserRole.equals("librarian")) {
                addLibrarianHeader(root,stage);
            }
        });

        ImageView cartimage = new ImageView();
        Image cart = new Image(Objects.requireNonNull(Header.class.getResourceAsStream("/Images/Main/cart.png")));
        cartimage.setImage(cart);
        cartimage.setFitWidth(90);
        cartimage.setFitHeight(59);
        cartimage.setLayoutX(1206);
        cartimage.setOnMouseClicked(e -> {
            if (currentLoggedInUser.isEmpty()) {
                LoginPage.loginPage(stage);
            } else {
                if(BookPopUp.checkIfHasCart(currentLoggedInUser)){
                    CheckoutPage.checkoutPage(stage, currentLoggedInUser);
                }
            }
        });

        updateLoginLabel();

        root.getChildren().addAll(header, logo, title, account, catalog, aboutus, loginLabel, cartimage);

        if (currentLoggedInUserRole.equals("admin")) {
            addAdminHeader(root, stage);
            addLibrarianHeader(root, stage);
        } else if (currentLoggedInUserRole.equals("librarian")) {
            addLibrarianHeader(root, stage);
        }
    }

    public static void addLibrarianHeader(Group root, Stage stage){
        Button userLookup = new Button("User Lookup");
        userLookup.setLayoutX(972);
        userLookup.setLayoutY(35);
        userLookup.setPrefSize(98, 45);
        userLookup.setStyle("-fx-background-color: #363732");
        userLookup.setTextFill(Paint.valueOf("white"));
        userLookup.setOnAction(e-> UserLookupPage.userLookupPage(stage));

        Button mediaButton = new Button("Media");
        mediaButton.setLayoutX(1077);
        mediaButton.setLayoutY(35);
        mediaButton.setPrefSize(74, 45);
        mediaButton.setStyle("-fx-background-color: #363732");
        mediaButton.setTextFill(Paint.valueOf("white"));
        mediaButton.setOnAction(e-> LibrarianCatalogPage.librarianCatalogPage(stage));


        root.getChildren().addAll(userLookup, mediaButton);
    }

    public static void addAdminHeader(Group root, Stage stage){
        Button adminButton = new Button("Admin");
        adminButton.setLayoutX(1158);
        adminButton.setLayoutY(35);
        adminButton.setPrefSize(74, 45);
        adminButton.setStyle("-fx-background-color: #363732");
        adminButton.setTextFill(Paint.valueOf("white"));
        adminButton.setOnAction(e-> AdminPage.adminPage(stage));
        root.getChildren().add(adminButton);
    }

    public static void updateLoginLabel() {
        if (currentLoggedInUser.isEmpty()) {
            loginLabel.setText("Log In");
        } else {
            loginLabel.setText("Welcome " + getUserNameById(currentLoggedInUser) + "! Click here if you want to log out.");
            loginLabel.setLayoutX(900);
        }
    }

    public static void removeCart(String userID) {
        try (Connection connection = HomePage.getConnection()) {
            // Check if the user has a cart
            String sql = "SELECT COUNT(*) AS cart_count FROM cart WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getInt("cart_count") > 0) {
                // If the user has a cart, delete it
                sql = "DELETE FROM cart WHERE userID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, userID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getUserNameById(String userId) {
        String userName = null;
        String query = "SELECT first_name FROM user WHERE id = ?";

        try (Connection conn = HomePage.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("first_name");
            }
        } catch (SQLException e) {
            System.out.println("Error getting user name: " + e.getMessage());
        }

        return userName;
    }


}
