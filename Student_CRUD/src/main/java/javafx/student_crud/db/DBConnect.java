package javafx.student_crud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    private static final String URL = "jdbc:sqlite:student.db";
    private static DBConnect instance;

    private DBConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            createTableIfNotExists(); // <-- Automatically creates table if it doesn't exist
        } catch (Exception e) {
            System.out.println("DB Init Error");
            e.printStackTrace();
        }
    }

    public static synchronized DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    private void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    age INTEGER NOT NULL,
                    dept TEXT NOT NULL
                );
                """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table ready ✅");
        } catch (SQLException e) {
            System.out.println("Error creating table ❌");
            e.printStackTrace();
        }
    }
}
