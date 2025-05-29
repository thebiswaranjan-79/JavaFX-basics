module javafx.basic_project_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.basic_project_1 to javafx.fxml;
    exports javafx.basic_project_1;
}