package Main.Chris;

import Main.AccountPage;
import Main.Gabriel.CatalogPage;
import Main.Gabriel.CheckoutPage;
import Main.Sukeer.LoginPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

import static Main.HomePage.currentLoggedInUser;

public class AboutUsPage{

    public static void aboutUsPage(Stage stage) {
        Group root = new Group(); //group is groups of module(containers, test fields)
        Scene scene = new Scene(root, 1280,  900); //scene of page, creating width, and height (x,y value)
        scene.setFill(Paint.valueOf("#F4CE90")); //set

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
        Image cart = new Image(Objects.requireNonNull(AboutUsPage.class.getResourceAsStream("/Images/Main/cart.png")));
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

        Label welcomeLabel = new Label("Welcome to LibraHub");
        welcomeLabel.setLayoutX(33.0);
        welcomeLabel.setLayoutY(136.0);
        welcomeLabel.setFont(new Font(30.0));
        welcomeLabel.setUnderline(true);

        Label welcomeTextLabel = new Label("At LibraHub, we believe that access to knowledge is fundamental to human progress. We are dedicated to empowering libraries and their patrons with innovative technology solutions that streamline library management and enhance the user experience.");
        welcomeTextLabel.setLayoutX(33.0);
        welcomeTextLabel.setLayoutY(168.0);
        welcomeTextLabel.setPrefHeight(81.0);
        welcomeTextLabel.setPrefWidth(603.0);
        welcomeTextLabel.setWrapText(true);

        Label missionLabel = new Label("Our Mission");
        missionLabel.setLayoutX(33.0);
        missionLabel.setLayoutY(249.0);
        missionLabel.setFont(new Font(24.0));

        Label missionTextLabel = new Label("Our mission is to revolutionize the way libraries operate and serve their communities by providing cutting-edge library management systems. We strive to make library resources more accessible, efficient, and enjoyable for both library staff and patrons.");
        missionTextLabel.setLayoutX(33.0);
        missionTextLabel.setLayoutY(291.0);
        missionTextLabel.setPrefHeight(56.0);
        missionTextLabel.setPrefWidth(603.0);
        missionTextLabel.setWrapText(true);

        Label whatWeDoLabel = new Label("What We Do");
        whatWeDoLabel.setLayoutX(33.0);
        whatWeDoLabel.setLayoutY(355.0);
        whatWeDoLabel.setFont(new Font(24.0));

        Label whatWeDoTextLabel = new Label("""
                LibraHub specializes in developing comprehensive library management software tailored to meet the unique needs of modern libraries. Our platform offers a wide range of features designed to simplify library operations and improve user engagement:

                • Intuitive Catalog Browsing: Our user-friendly interface allows patrons to easily browse the library's catalog, discover new titles, and find the resources they need.
                • Streamlined Account Management: Patrons can conveniently access their library accounts, view borrowing history, renew materials, and manage holds with just a few clicks.
                • Effortless Sign-Up Process: We have streamlined the registration process, making it quick and hassle-free for new patrons to sign up for library memberships online.
                • Seamless Check-Out Experience: With LibraHub, patrons can check out books, eBooks, audiobooks, and other materials seamlessly, whether they're in the library or browsing from home.""");
        whatWeDoTextLabel.setLayoutX(33.0);
        whatWeDoTextLabel.setLayoutY(392.0);
        whatWeDoTextLabel.setPrefHeight(228);
        whatWeDoTextLabel.setPrefWidth(603.0);
        whatWeDoTextLabel.setWrapText(true);

        Label visionLabel = new Label("Our Vision");
        visionLabel.setLayoutX(33.0);
        visionLabel.setLayoutY(619.0);
        visionLabel.setFont(new Font(24.0));

        Label visionTextLabel = new Label("At LibraHub, we envision a world where libraries are vibrant hubs of learning and discovery, accessible to all members of the community. By leveraging technology, we aim to empower libraries to fulfill their vital role as centers for education, enrichment, and cultural exchange.");
        visionTextLabel.setLayoutX(33.0);
        visionTextLabel.setLayoutY(654.0);
        visionTextLabel.setPrefHeight(68.0);
        visionTextLabel.setPrefWidth(603.0);
        visionTextLabel.setWrapText(true);

        Label whyChooseUsLabel = new Label("Why Choose LibraHub?");
        whyChooseUsLabel.setLayoutX(33.0);
        whyChooseUsLabel.setLayoutY(730.0);
        whyChooseUsLabel.setFont(new Font(24.0));

        Label whyChooseUsTextLabel = new Label("""
                • Expertise: With years of experience in the library technology industry, our team brings unparalleled expertise to every project.
                • Innovation: We are committed to staying at the forefront of technological innovation, constantly evolving our platform to meet the changing needs of libraries and their patrons.
                • Customer-Centric Approach: We prioritize customer satisfaction above all else, working closely with libraries to understand their unique requirements and delivering solutions that exceed expectations.""");
        whyChooseUsTextLabel.setLayoutX(33.0);
        whyChooseUsTextLabel.setLayoutY(765.0);
        whyChooseUsTextLabel.setPrefHeight(114.0);
        whyChooseUsTextLabel.setPrefWidth(603.0);
        whyChooseUsTextLabel.setWrapText(true);

        Rectangle contactUsRectangle = new Rectangle();
        contactUsRectangle.setLayoutX(683.0);
        contactUsRectangle.setLayoutY(226.0);
        contactUsRectangle.setWidth(536.0);
        contactUsRectangle.setHeight(660.0);
        contactUsRectangle.setFill(Paint.valueOf("#a9ddd6"));
        contactUsRectangle.setStrokeWidth(2);
        contactUsRectangle.setStroke(Paint.valueOf("#000000"));

        Label getInTouchLabel = new Label("Get In Touch");
        getInTouchLabel.setLayoutX(683.0);
        getInTouchLabel.setLayoutY(146.0);
        getInTouchLabel.setFont(new Font(24.0));

        Label getInTouchTextLabel = new Label("Ready to take your library to the next level with LibraHub? We'd love to hear from you! Contact us today to learn more about our products and services.");
        getInTouchTextLabel.setLayoutX(683.0);
        getInTouchTextLabel.setLayoutY(181.0);
        getInTouchTextLabel.setPrefHeight(35.0);
        getInTouchTextLabel.setPrefWidth(536.0);
        getInTouchTextLabel.setWrapText(true);

        Label hoursLabel = new Label("Hours:");
        hoursLabel.setLayoutX(694.0);
        hoursLabel.setLayoutY(242.0);
        hoursLabel.setFont(new Font(33.0));
        hoursLabel.setUnderline(true);

        Label hoursTextLabel = new Label("• Monday: 9:00 AM - 6:00 PM\n• Tuesday: 9:00 AM - 8:00 PM\n• Wednesday: 10:00 AM - 6:00 PM\n• Thursday: 10:00 AM - 8:00 PM\n• Friday: 9:00 AM - 5:00 PM\n• Saturday: 10:00 AM - 4:00 PM\n• Sunday: Closed");
        hoursTextLabel.setLayoutX(749.0);
        hoursTextLabel.setLayoutY(293.0);
        hoursTextLabel.setPrefHeight(273.0);
        hoursTextLabel.setPrefWidth(450.0);
        hoursTextLabel.setFont(new Font(27.0));
        hoursTextLabel.setWrapText(true);

        Label contactUsLabel = new Label("Contact Us:");
        contactUsLabel.setLayoutX(694.0);
        contactUsLabel.setLayoutY(566.0);
        contactUsLabel.setFont(Font.font(33));
        contactUsLabel.setUnderline(true);

        Label contactUsTextLabel = new Label("""
                Have questions or need assistance? Our dedicated team is here to help! You can reach us by email, phone, or through our online contact form.\


                Email: info@librahub.com
                Phone: +1 (555) 123-4567
                Address: 123 Library Way, Cityville, State, ZIP

                Feel free to contact us during our business hours, and we'll be happy to assist you with any inquiries, technical support, or feedback you may have.""");
        contactUsTextLabel.setLayoutX(694.0);
        contactUsTextLabel.setLayoutY(606.0);
        contactUsTextLabel.setPrefHeight(273.0);
        contactUsTextLabel.setPrefWidth(518.0);
        contactUsTextLabel.setWrapText(true);
        contactUsTextLabel.setFont(new Font(16.0));

        stage.setTitle("Library Management System");// sets current scene
        root.getChildren().addAll(welcomeLabel, welcomeTextLabel, missionLabel, missionTextLabel, whatWeDoLabel, whatWeDoTextLabel, visionLabel, visionTextLabel, whyChooseUsLabel, whyChooseUsTextLabel, contactUsRectangle, getInTouchLabel, getInTouchTextLabel, hoursLabel, hoursTextLabel, contactUsLabel, contactUsTextLabel);
        stage.setScene(scene);
        stage.show();
    }
}