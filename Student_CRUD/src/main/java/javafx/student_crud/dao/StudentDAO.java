package javafx.student_crud.dao;

import javafx.student_crud.db.DBConnect;
import javafx.student_crud.entity.Student_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    DBConnect db = DBConnect.getInstance();

    public void insert(Student_Entity studentEntity) {
        String query = "INSERT INTO students(name, age, dept) VALUES (?,?,?)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            System.out.println("Connected (Insert)");

            ps.setString(1, studentEntity.getName());
            ps.setInt(2, studentEntity.getAge());
            ps.setString(3, studentEntity.getDepartment());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert Error");
            e.printStackTrace();
        }
    }

    public List<Student_Entity> findAll() {
        String query = "SELECT * FROM students";
        List<Student_Entity> list = new ArrayList<>();

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Connected (FindAll)");

            while (rs.next()) {
                Student_Entity s = new Student_Entity();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setDepartment(rs.getString("dept"));
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("FindAll Error");
            e.printStackTrace();
        }

        return list;
    }
}

