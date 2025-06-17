package javafx.student_crud.services;

import javafx.student_crud.dao.StudentDAO;
import javafx.student_crud.entity.Student_Entity;
import javafx.student_crud.mapper.Student_Mapper;
import javafx.student_crud.model.Student_Model;

import java.util.List;
import java.util.stream.Collectors;

public class Student_Service {

    private final StudentDAO studentDAO = new StudentDAO();

    public void saveStudent(Student_Model studentModel) {
        Student_Entity studentEntity = Student_Mapper.toEntity(studentModel);
        studentDAO.insert(studentEntity);
    }

    public List<Student_Model> getAllStudents() {
        List<Student_Entity> studentEntities = studentDAO.findAll();
        return studentEntities.stream()
                .map(Student_Mapper::toModel).collect(Collectors.toList());
    }
}
