module javafx.hbox_vbox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javafx.hbox_vbox to javafx.fxml;
    exports javafx.hbox_vbox;
}