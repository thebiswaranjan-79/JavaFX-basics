module javafx.sql_javafx_connection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens javafx.sql_javafx_connection to javafx.fxml;
    exports javafx.sql_javafx_connection;
}