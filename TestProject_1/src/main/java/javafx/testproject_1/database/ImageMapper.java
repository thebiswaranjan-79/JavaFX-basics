package javafx.testproject_1.database;

import javafx.testproject_1.ImageInfo;

public class ImageMapper {

    public static ImageEntity toEntity(ImageInfo info) {
        return new ImageEntity(
                info.getImageName(),
                info.getImagePath(),
                info.getWidth(),
                info.getHeight(),
                info.getFormat(),
                info.getBitDepth(),
                info.getChannel(),
                info.getRemarks()
        );
    }

    public static ImageInfo toModel(ImageEntity entity) {
        ImageInfo info = new ImageInfo(
                entity.getImageName(),
                entity.getImagePath(),
                entity.getWidth(),
                entity.getHeight(),
                entity.getFormat(),
                entity.getBitDepth(),
                entity.getChannel()
        );
        info.setRemarks(entity.getRemarks());
        return info;
    }
}
