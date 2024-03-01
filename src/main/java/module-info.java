module com.lms.librarymangementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.lms.librarymangementsystem to javafx.fxml;
    exports com.lms.librarymangementsystem;
}