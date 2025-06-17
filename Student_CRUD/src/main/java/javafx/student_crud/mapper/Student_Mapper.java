package javafx.student_crud.mapper;

import javafx.student_crud.entity.Student_Entity;
import javafx.student_crud.model.Student_Model;

public class Student_Mapper {
    public static Student_Entity toEntity(Student_Model model){
        Student_Entity entity = new Student_Entity();
        entity.setName(model.getName());
        entity.setAge(model.getAge());
        entity.setDepartment(model.getDepartment());
        return entity;
    }

    public static Student_Model toModel(Student_Entity entity){
        return new Student_Model(entity.getName(), entity.getAge(), entity.getDepartment());
    }

}
