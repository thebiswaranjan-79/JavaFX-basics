package javafx.testproject_1.database;

public class ImageEntity {
    private String imageName;
    private String imagePath;
    private double width;
    private double height;
    private String format;
    private int bitDepth;
    private int channel;
    private String remarks;

    // Constructor
    public ImageEntity(String imageName, String imagePath, double width, double height,
                       String format, int bitDepth, int channel, String remarks) {
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.format = format;
        this.bitDepth = bitDepth;
        this.channel = channel;
        this.remarks = remarks;
    }

    // Getters and Setters
    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public int getBitDepth() { return bitDepth; }
    public void setBitDepth(int bitDepth) { this.bitDepth = bitDepth; }

    public int getChannel() { return channel; }
    public void setChannel(int channel) { this.channel = channel; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
