package javafx.testproject_1;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project_1 {

    @FXML
    private TableView<ImageInfo> tableView;
    @FXML
    private TableColumn<ImageInfo, String>  colImageName, colImagePath, colFormat, colRemarks;

    @FXML private TableColumn<ImageInfo, Integer> colBitDepth, colChannel;

    @FXML
    private TableColumn<ImageInfo, Double> colWidth, colHeight;

    @FXML
    private TextField remarks;


    private final Set<String> imagePathSet = new HashSet<>();
    private final ObservableList<ImageInfo> imageList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colImageName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getImageName()));
        colImagePath.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getImagePath()));
        colFormat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFormat()));
        colWidth.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getWidth()));
        colHeight.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getHeight()));
        colBitDepth.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBitDepth()));
        colChannel.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getChannel()));
        colRemarks.setCellValueFactory(data -> data.getValue().remarksProperty());

        colImagePath.setCellFactory(column -> new TableCell<ImageInfo, String>() {
            private final Button linkButton = new Button();

            {
                linkButton.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-padding: 6 8 6 8;");

                linkButton.setOnAction(event -> {
                    ImageInfo info = getTableView().getItems().get(getIndex());
                    File file = new File(info.getImagePath());
                    try {
                        // Open the folder and select the file
                        new ProcessBuilder("explorer.exe", "/select,", file.getAbsolutePath()).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(String path, boolean empty) {
                super.updateItem(path, empty);
                if (empty || path == null) {
                    setGraphic(null);
                } else {
                    linkButton.setText(new File(path).getName());
                    setGraphic(linkButton);
                }
            }
        });

        tableView.setEditable(true);

        colImageName.setCellFactory(TextFieldTableCell.forTableColumn());
        colImageName.setOnEditCommit(event -> {
            ImageInfo imageInfo = event.getRowValue();
            imageInfo.setImageName(event.getNewValue());
        });
         colWidth.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colWidth.setOnEditCommit(event -> {
            ImageInfo imageInfo = event.getRowValue();
            imageInfo.setWidth(event.getNewValue());
        });

        colHeight.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colHeight.setOnEditCommit(event -> {
            ImageInfo imageInfo = event.getRowValue();
            imageInfo.setHeight(event.getNewValue());
        });

        tableView.setItems(imageList);
    }

    @FXML
    public void handleAddButtonClick(ActionEvent event) {
        String remarkText = remarks.getText();
        ImageInfo selectedRow = tableView.getSelectionModel().getSelectedItem();

        if (selectedRow != null && remarkText != null && !remarkText.trim().isEmpty()) {
            selectedRow.setRemarks(remarkText);
            // no need to refresh tableView here
            remarks.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row and enter a remark.");
            alert.showAndWait();
        }

        System.out.println(selectedRow.getRemarks());



    }



    @FXML
    private void handleOpenButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Images");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());
        if (files == null) return;


        for (File file : files) {

            try {
                Image fxImage = new Image(file.toURI().toString());

                String absolutePath = file.getAbsolutePath();

                if (imagePathSet.contains(absolutePath)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplicate Image");
                    alert.setHeaderText(null);
                    alert.setContentText("Image \"" + file.getName() + "\" already exists!");
                    alert.showAndWait();
                    continue;
                }

                // Using AWT to read BufferedImage for bitDepth and channel info
                BufferedImage bufferedImage = ImageIO.read(file);
                int bitDepth = bufferedImage.getColorModel().getPixelSize();
                int channels = bufferedImage.getColorModel().getNumComponents();

                // Print info
                System.out.println("Image: " + file.getName());
                System.out.println(" - Bit Depth: " + bitDepth);
                System.out.println(" - Channels: " + channels);


                String imageName = file.getName();
                String imagePath = file.getAbsolutePath();
                double width = fxImage.getWidth();
                double height = fxImage.getHeight();
                String format = getFileExtension(file);


                System.out.println("Image Name is " + imageName);
                System.out.println("Image Path  is " + imagePath);
                System.out.println("Width => " + width);
                System.out.println("Height => " + height);
                System.out.println("Format => " + format);


                ImageInfo info = new ImageInfo( imageName, imagePath,
                        width, height, format,
                        bitDepth, channels
                );

                imageList.add(info);
                imagePathSet.add(absolutePath);  // Track path in set to avoid duplicates


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private String getFileExtension(File file) {
        String name = file.getName();
        int dot = name.lastIndexOf(".");
        return dot == -1 ? "Unknown" : name.substring(dot + 1).toUpperCase();
    }


}
