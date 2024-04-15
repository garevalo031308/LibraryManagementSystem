module HomePage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens HomePage to javafx.fxml, javafx.graphics;
    exports HomePage;
}