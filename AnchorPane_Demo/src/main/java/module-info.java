module javafx.anchorpane_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.anchorpane_demo to javafx.fxml;
    exports javafx.anchorpane_demo;
}