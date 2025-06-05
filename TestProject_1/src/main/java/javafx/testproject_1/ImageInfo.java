package javafx.testproject_1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ImageInfo {

    private  String imageName;
    private String imagePath;
    private double width;
    private double height;
    private String format;
    private int bitDepth;
    private int channel;


    private final StringProperty remarks = new SimpleStringProperty(this, "remarks", "");

    // Constructor
    public ImageInfo( String imgName, String imagePath, double width, double height, String format, int bitDepth, int channels) {

        this.imageName = imgName;
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



    // remarks getter and setter for StringProperty
    public String getRemarks() {
        return remarks.get();
    }

    public void setRemarks(String value) {
        remarks.set(value);
    }

    public StringProperty remarksProperty() {
        return remarks;
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

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }



}