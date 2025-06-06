package javafx.sql_javafx_connection.model;

import javafx.sql_javafx_connection.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

        // CREATE
       public void addStudent(Student student) throws SQLException {

           String sql = "INSERT INTO students(name, age) VALUES (? , ?)";

           try(Connection conn = DBUtil.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)){
               stmt.setString(1,student.getName());
               stmt.setInt(2,student.getAge());
               stmt.executeUpdate();
           }
       }

       public List<Student> getAllStudents() throws SQLException {
           List<Student> list = new ArrayList<>();
           String sql = "SELECT * FROM students";

           try(Connection conn = DBUtil.getConnection();
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(sql)){
               while(rs.next()){
                   list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
               }
           }
            return list;
       }


       // UPDATE

       public void updateStudent(Student student) throws SQLException {
           String sql = "Update Students SET name = ?, age = ? WHERE id = ?";



           try(Connection conn = DBUtil.getConnection();

               PreparedStatement stmt = conn.prepareStatement(sql)){
               stmt.setString(1, student.getName());
               stmt.setInt(2, student.getAge());
               stmt.setInt(3, student.getId());

               stmt.executeUpdate();
           }

       }

        // DELETE

        public void deleteStudent(int id) throws SQLException {
           String sql = "DELETE FROM students WHERE id = ?";
           try(Connection conn = DBUtil.getConnection();
                  PreparedStatement stmt = conn.prepareStatement(sql)){
                  stmt.setInt(1, id);
                  stmt.executeUpdate();
           }
        }

        // INIT TABLE
       public void createTableIfNotExists() throws SQLException {
           String sql = """
                            CREATE TABLE IF NOT EXISTS students(
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             name TEXT NOT NULL,
                             age INTEGER NOT NULL
                            );
                   """;

           try(Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement()
           ){
               stmt.execute(sql);
           }
       }

}
