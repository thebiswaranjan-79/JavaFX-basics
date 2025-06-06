package javafx.sql_javafx_connection;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.sql_javafx_connection.model.Student;
import javafx.sql_javafx_connection.model.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextArea outputArea;

    private final StudentDAO dao = new StudentDAO();

    @FXML
    public void initialize() {
        try {
            dao.createTableIfNotExists();
        } catch (SQLException e) {
            outputArea.setText("DB Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            dao.addStudent(new Student(0, name, age));
            outputArea.setText("✅ Student added successfully!");
            clearFields();
        } catch (Exception e) {
            outputArea.setText("❌ Add Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleView() {
        try {
            List<Student> students = dao.getAllStudents();
            StringBuilder sb = new StringBuilder("📋 Student List:\n");
            for (Student s : students) {
                sb.append("ID: ").append(s.getId())
                        .append(" | Name: ").append(s.getName())
                        .append(" | Age: ").append(s.getAge()).append("\n");
            }
            outputArea.setText(sb.toString());
        } catch (Exception e) {
            outputArea.setText("❌ View Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            Student student = new Student(id, name, age);
            dao.updateStudent(student);

            System.out.println(student);

            outputArea.setText("✅ Student updated successfully!");

            clearFields();
        } catch (Exception e) {
            outputArea.setText("❌ Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        try {
            int id = Integer.parseInt(idField.getText());
            dao.deleteStudent(id);
            outputArea.setText("🗑️ Student deleted successfully!");
            clearFields();
        } catch (Exception e) {
            outputArea.setText("❌ Delete Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
    }

}

