package javafx.testproject_1.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private Connection getConnection() throws SQLException {
        return DBConnect.getConnection();
    }

    public void insert(ImageEntity entity) throws SQLException {
        String sql = "INSERT INTO ImageInfo_Entity(" +
                "imageName, imagePath, width, height, format, " +
                "bitDepth, channel, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getImageName());
            stmt.setString(2, entity.getImagePath());
            stmt.setDouble(3, entity.getWidth());
            stmt.setDouble(4, entity.getHeight());
            stmt.setString(5, entity.getFormat());
            stmt.setInt(6, entity.getBitDepth());
            stmt.setInt(7, entity.getChannel());
            stmt.setString(8, entity.getRemarks());
            stmt.executeUpdate();
        }
    }

    public List<ImageEntity> getAll() throws SQLException {
        List<ImageEntity> entities = new ArrayList<>();
        String sql = "SELECT * FROM ImageInfo_Entity";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entities.add(new ImageEntity(
                        rs.getString("imageName"),
                        rs.getString("imagePath"),
                        rs.getDouble("width"),
                        rs.getDouble("height"),
                        rs.getString("format"),
                        rs.getInt("bitDepth"),
                        rs.getInt("channel"),
                        rs.getString("remarks")
                ));
            }
        }
        return entities;
    }

    public void update(ImageEntity entity) throws SQLException {
        String sql = "UPDATE ImageInfo_Entity SET " +
                "imageName=?, width=?, height=?, format=?, bitDepth=?, channel=?, remarks=? " +
                "WHERE imagePath=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getImageName());
            stmt.setDouble(2, entity.getWidth());
            stmt.setDouble(3, entity.getHeight());
            stmt.setString(4, entity.getFormat());
            stmt.setInt(5, entity.getBitDepth());
            stmt.setInt(6, entity.getChannel());
            stmt.setString(7, entity.getRemarks());
            stmt.setString(8, entity.getImagePath()); // imagePath is the unique key
            stmt.executeUpdate();
        }
    }

    public void delete(String imagePath) throws SQLException {
        String sql = "DELETE FROM ImageInfo_Entity WHERE imagePath = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, imagePath);
            stmt.executeUpdate();
        }
    }

    public void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS ImageInfo_Entity (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                imageName TEXT NOT NULL,
                imagePath TEXT NOT NULL UNIQUE,
                width REAL NOT NULL,
                height REAL NOT NULL,
                format TEXT NOT NULL,
                bitDepth INTEGER NOT NULL,
                channel INTEGER NOT NULL,
                remarks TEXT
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}
