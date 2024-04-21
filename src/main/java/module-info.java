module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens Main to javafx.fxml, javafx.graphics;
    exports Main;
    exports Main.Sukeer;
    opens Main.Sukeer to javafx.fxml, javafx.graphics;
    exports Main.Chris;
    opens Main.Chris to javafx.fxml, javafx.graphics;
    exports Main.Daniel;
    opens Main.Daniel to javafx.fxml, javafx.graphics;
    exports Main.Gabriel;
    opens Main.Gabriel to javafx.fxml, javafx.graphics;
}