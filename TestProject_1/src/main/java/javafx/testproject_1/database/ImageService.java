package javafx.testproject_1.database;

import javafx.testproject_1.ImageInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ImageService {

    private final ImageDAO dao = new ImageDAO();

    public void createTableIfNotExists() throws SQLException {
        dao.createTableIfNotExists();
    }

    public void insertImage(ImageInfo info) throws SQLException {
        ImageEntity entity = ImageMapper.toEntity(info);
        dao.insert(entity);
    }

    public List<ImageInfo> getAllImages() throws SQLException {
        return dao.getAll().stream()
                .map(ImageMapper::toModel)
                .collect(Collectors.toList());
    }

    public void updateImage(ImageInfo info) throws SQLException {
        ImageEntity entity = ImageMapper.toEntity(info);
        dao.update(entity);
    }

    public void deleteImage(String imagePath) throws SQLException {
        dao.delete(imagePath);
    }
}
