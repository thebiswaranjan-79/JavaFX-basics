package javafx.testproject_1;

import javafx.testproject_1.utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageInfoDAO {

    // CREATE
    public void insertImage(ImageInfo info) throws SQLException {
        String sql = "INSERT INTO ImageInfo_Entity(" +
                "imageName, imagePath, width, height, format, " +
                "bitDepth, channel, remarks) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, info.getImageName());
            stmt.setString(2, info.getImagePath());
            stmt.setDouble(3, info.getWidth());
            stmt.setDouble(4, info.getHeight());
            stmt.setString(5, info.getFormat());
            stmt.setInt(6, info.getBitDepth());
            stmt.setInt(7, info.getChannel());
            stmt.setString(8, info.getRemarks());

            stmt.executeUpdate();
        }
    }

    // READ
    public List<ImageInfo> getAllImages() throws SQLException {
        List<ImageInfo> images = new ArrayList<>();
        String sql = "SELECT * FROM ImageInfo_Entity";

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ImageInfo info = new ImageInfo(
                        rs.getString("imageName"),
                        rs.getString("imagePath"),
                        rs.getDouble("width"),
                        rs.getDouble("height"),
                        rs.getString("format"),
                        rs.getInt("bitDepth"),
                        rs.getInt("channel")
                );
                info.setRemarks(rs.getString("remarks"));
                images.add(info);
            }
        }
        return images;
    }

    // UPDATE
    public void updateImage(ImageInfo info) throws SQLException {
        String sql = "UPDATE ImageInfo_Entity SET " +
                "imageName = ?, width = ?, height = ?, remarks = ? " +
                "WHERE imagePath = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, info.getImageName());
            stmt.setDouble(2, info.getWidth());
            stmt.setDouble(3, info.getHeight());
            stmt.setString(4, info.getRemarks());
            stmt.setString(5, info.getImagePath());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteImage(String imagePath) throws SQLException {
        String sql = "DELETE FROM ImageInfo_Entity WHERE imagePath = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, imagePath);
            stmt.executeUpdate();
        }
    }

    // INIT TABLE (unchanged)
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

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}