module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens Main to javafx.fxml, javafx.graphics;
    exports Main;
    exports Main.Admin;
    opens Main.Admin to javafx.fxml, javafx.graphics;
    exports Main.Librarian;
    opens Main.Librarian to javafx.fxml, javafx.graphics;
    exports Main.User;
    opens Main.User to javafx.fxml, javafx.graphics;
    exports Main.Media;
    opens Main.Media to javafx.fxml, javafx.graphics;
}