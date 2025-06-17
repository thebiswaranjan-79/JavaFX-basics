module javafx.student_crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens javafx.student_crud to javafx.fxml;
    opens javafx.student_crud.Controllers to javafx.fxml;
    opens javafx.student_crud.model to javafx.base; // ✅ Add this line for PropertyValueFactory

    exports javafx.student_crud;
}
