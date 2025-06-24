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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.testproject_1.database.ImageDAO;
import javafx.testproject_1.database.ImageService;
import javafx.testproject_1.utils.ImageViewController;
import javafx.util.converter.DoubleStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImageController {

        @FXML
        private TableView<ImageInfo> tableView;

        @FXML
        private ImageView imageView;

        @FXML
        private TableColumn<ImageInfo, String>  colImageName, colImagePath, colFormat, colRemarks;

        @FXML private TableColumn<ImageInfo, Integer> colBitDepth, colChannel;

        @FXML
        private TableColumn<ImageInfo, Double> colWidth, colHeight;

        @FXML
        private TextField remarks;

        @FXML
         private Button zoomInBtn, resetBtn, panBtn, HflipBtn, VflipBtn, rotateCWBtn, rotateCCWBtn;

        private ImageViewController imageController;
        private final ImageService imageService = new ImageService();


        private final Set<String> imagePathSet = new HashSet<>();
        private final ObservableList<ImageInfo> imageList = FXCollections.observableArrayList();

        @FXML
        public void initialize() {

            try {
                imageService.createTableIfNotExists();
                loadImagesFromDatabase(); // Load existing data from DB
                imageView.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            } catch (SQLException e) {
                showAlert("Database Error", "Error initializing database: " + e.getMessage());
            }

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

            // All Operations

            // ZOOM
            // Setup zoom button action
            zoomInBtn.setOnAction(event -> imageController.toggleZoomMode(zoomInBtn));


            // Initialize controller with imageView
            imageController = new ImageViewController(imageView);

            // RESET
            resetBtn.setOnAction(event -> imageController.resetImage());

            panBtn.setOnAction(event -> {
                imageController.togglePanning(panBtn);
            });

            HflipBtn.setOnAction(actionEvent -> {
                imageController.flipHorizontal();
            });

            VflipBtn.setOnAction(actionEvent -> {
                imageController.flipVertical();
            });

            rotateCWBtn.setOnAction(actionEvent -> {
                imageController.rotateClockwise();
            });

            rotateCCWBtn.setOnAction(actionEvent -> {
                imageController.roateAntiClockwise();
            });


            tableView.setEditable(true);
// Image Name
            colImageName.setCellFactory(TextFieldTableCell.forTableColumn());
            colImageName.setOnEditCommit(event -> {
                ImageInfo imageInfo = event.getRowValue();
                imageInfo.setImageName(event.getNewValue());
                updateImageInDatabase(imageInfo);
            });

// Width
            colWidth.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            colWidth.setOnEditCommit(event -> {
                ImageInfo imageInfo = event.getRowValue();
                imageInfo.setWidth(event.getNewValue());
                updateImageInDatabase(imageInfo);
            });

// Height
            colHeight.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            colHeight.setOnEditCommit(event -> {
                ImageInfo imageInfo = event.getRowValue();
                imageInfo.setHeight(event.getNewValue());
                updateImageInDatabase(imageInfo);
            });



// Remarks
            colRemarks.setCellFactory(TextFieldTableCell.forTableColumn());
            colRemarks.setOnEditCommit(event -> {
                ImageInfo imageInfo = event.getRowValue();
                imageInfo.setRemarks(event.getNewValue());
                updateImageInDatabase(imageInfo);
            });

            // Delete Row
            // Delete row on pressing Delete key
            tableView.setOnKeyPressed(event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.DELETE) {
                    ImageInfo selected = tableView.getSelectionModel().getSelectedItem();
                    if (selected != null) {
                        deleteImage(selected);
                    }
                }
            });

            tableView.setRowFactory(tv -> {
                TableRow<ImageInfo> SelectRow = new TableRow<>();
                SelectRow.setOnMouseClicked(event -> {
                    if (!SelectRow.isEmpty() && event.getClickCount() == 1) {
                        ImageInfo rowData = SelectRow.getItem();
                        Image image = new Image(new File(rowData.getImagePath()).toURI().toString());
                        imageView.setImage(image);
                    }
                });
                return SelectRow;
            });



            // Set data

            tableView.setItems(imageList);
        }

    private void deleteImage(ImageInfo imageInfo) {
        try {
            // Delete from database
            imageService.deleteImage(imageInfo.getImagePath());

            // 🛠️ Clear the image if the deleted row was selected
            imageView.setImage(null);
            // Remove from TableView and internal set
            imageList.remove(imageInfo);
            imagePathSet.remove(imageInfo.getImagePath());

            showAlert("Success", "Image deleted successfully.");
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete image: " + e.getMessage());
        }
    }


    private void updateImageInDatabase(ImageInfo imageInfo) {
            try {
                imageService.updateImage(imageInfo);
            } catch (SQLException e) {
                showAlert("Update Error", "Failed to update image: " + e.getMessage());
            }
        }

        private void loadImagesFromDatabase() {
            try {
                List<ImageInfo> images = imageService.getAllImages();
                imageList.setAll(images);

                // Populate path set for duplicate checking
                imagePathSet.addAll(
                        images.stream()
                                .map(ImageInfo::getImagePath)
                                .collect(Collectors.toList())
                );
            } catch (SQLException e) {
                showAlert("Database Error", "Failed to load images: " + e.getMessage());
            }
        }




        @FXML
        public void handleAddButtonClick(ActionEvent event) {
            try {
                String remarkText = remarks.getText();
                ImageInfo selectedRow = tableView.getSelectionModel().getSelectedItem();

                if (selectedRow != null && remarkText != null && !remarkText.trim().isEmpty()) {
                    selectedRow.setRemarks(remarkText);
                    updateImageInDatabase(selectedRow); // Update DB with new remark
                    remarks.clear();
                    System.out.println(selectedRow.getRemarks());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a row and enter a remark.");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred: " + e.getMessage());
                alert.showAndWait();
            }
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
                    String absolutePath = file.getAbsolutePath();

                    // Check if image already exists in database
                    if (imagePathSet.contains(absolutePath)) {
                        showDuplicateAlert(file.getName());
                        continue;
                    }

                    // Process image metadata
                    Image fxImage = new Image(file.toURI().toString());
                    BufferedImage bufferedImage = ImageIO.read(file);

                    int bitDepth = bufferedImage.getColorModel().getPixelSize();
                    int channels = bufferedImage.getColorModel().getNumComponents();
                    String imageName = file.getName();
                    String imagePath = absolutePath;
                    double width = fxImage.getWidth();
                    double height = fxImage.getHeight();
                    String format = getFileExtension(file);

                    // Create ImageInfo object
                    ImageInfo info = new ImageInfo(imageName, imagePath, width, height,
                            format, bitDepth, channels);

                    // Save to database
                    saveImageToDatabase(info);

                    // Add to UI if successfully saved
                    imageList.add(info);
                    imagePathSet.add(absolutePath);

                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error Processing Image", "Failed to process: " + file.getName());
                }
            }
        }

        private void saveImageToDatabase(ImageInfo info) {
            try {
                imageService.insertImage(info);
            } catch (SQLException e) {
                // Handle database errors specifically
                if (e.getMessage().contains("UNIQUE constraint")) {
                    showDuplicateAlert(info.getImageName());
                } else {
                    showAlert("Database Error", "Failed to save image: " + e.getMessage());
                }
                // Re-throw to prevent adding to UI
                throw new RuntimeException("Database save failed", e);
            }
        }

        private void showDuplicateAlert(String fileName) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplicate Image");
            alert.setHeaderText(null);
            alert.setContentText("Image \"" + fileName + "\" already exists!");
            alert.showAndWait();
        }

        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }

        private String getFileExtension(File file) {
            String name = file.getName();
            int dot = name.lastIndexOf(".");
            return dot == -1 ? "Unknown" : name.substring(dot + 1).toUpperCase();
        }


    }



