module javafx.testproject_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens javafx.testproject_1 to javafx.fxml;
    opens javafx.testproject_1.utils to javafx.fxml;

    exports javafx.testproject_1;
    exports javafx.testproject_1.utils;
}
