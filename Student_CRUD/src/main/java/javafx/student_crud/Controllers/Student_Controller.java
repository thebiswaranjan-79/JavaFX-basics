package javafx.student_crud.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.student_crud.model.Student_Model;
import javafx.student_crud.services.Student_Service;

import java.util.List;

public class Student_Controller {

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField departmentField;
    @FXML private TableView<Student_Model> studentTable;
    @FXML private TableColumn<Student_Model, String> nameColumn;
    @FXML private TableColumn<Student_Model, Integer> ageColumn;
    @FXML private TableColumn<Student_Model, String> departmentColumn;


    private final Student_Service studentService = new Student_Service();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        loadStudents();
    }

    private void loadStudents() {
        ObservableList<Student_Model> data = FXCollections.observableArrayList(fetchStudentsForTable());
        studentTable.setItems(data);
    }


    @FXML
    private void handleSaveStudent(ActionEvent event) {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String dept = departmentField.getText();

            saveStudentFromUI(name, age, dept);
            loadStudents(); // Refresh the table after saving

            // Optionally clear the form fields
            nameField.clear();
            ageField.clear();
            departmentField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input: " + ageField.getText());
        }
    }


    public void saveStudentFromUI(String name, int age, String dept){
        Student_Model st = new Student_Model(name, age, dept);
        studentService.saveStudent(st);
    }

    @FXML
    public List<Student_Model> fetchStudentsForTable(){
        return studentService.getAllStudents();
    }

}
