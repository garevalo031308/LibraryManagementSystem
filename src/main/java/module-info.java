module com.lms.librarymangementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires com.dlsc.formsfx;

    opens com.lms.librarymangementsystem to javafx.fxml, javafx.graphics;
    exports com.lms.librarymangementsystem;
}