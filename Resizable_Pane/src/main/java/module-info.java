module javafx.resizable_pane {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javafx.resizable_pane to javafx.fxml;
    exports javafx.resizable_pane;
}