module javafx.helloworld {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.helloworld to javafx.fxml;
    exports javafx.helloworld;
}