module javafx.testproject_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javafx.testproject_1 to javafx.fxml;
    exports javafx.testproject_1;
}