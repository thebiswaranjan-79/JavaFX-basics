package javafx.student_crud.model;

public class Student_Model {
     private String name;
     private int age;
     private String department;

     public Student_Model(){

     }

    public Student_Model(String name, int age, String dept){
         this.name = name;
         this.age = age;
         this.department = dept;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
