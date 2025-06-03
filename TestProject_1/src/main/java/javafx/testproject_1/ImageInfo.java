package javafx.testproject_1;

public class ImageInfo {

    private String imageName;
    private String imagePath;
    private double width;
    private double height;
    private String format;
    private int bitDepth;
    private int channel;

    // Constructor
    public ImageInfo( String imageName, String imagePath, double width, double height, String format, int bitDepth, int channels) {

        this.imageName = imageName;
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.format = format;
        this.bitDepth = bitDepth;
        this.channel = channels;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getFormat() {
        return format;
    }

    public int getBitDepth() {
        return bitDepth;
    }

    public int getChannel() {
        return channel;
    }
}