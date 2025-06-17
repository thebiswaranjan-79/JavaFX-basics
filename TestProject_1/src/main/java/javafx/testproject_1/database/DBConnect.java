package javafx.testproject_1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String URL = "jdbc:sqlite:ImageInfo.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("✅ Connected to the database.");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }
}
