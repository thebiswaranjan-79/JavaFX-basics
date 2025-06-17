package javafx.resizable_pane;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;



public class HelloController {

    @FXML
    private StackPane imageContainer;

    @FXML
    private Group group;

    @FXML
    private GridPane gridPane;

    private final double originalWidth = 5000;
    private final double originalHeight = 5000;


    @FXML
    public void initialize() {
        gridPane.setPrefSize(originalWidth, originalHeight);

        imageContainer.widthProperty().addListener((obs, oldVal, newVal) -> resize());
        imageContainer.heightProperty().addListener((obs, oldVal, newVal) -> resize());
    }


    private void resize() {
        double containerWidth = imageContainer.getWidth();
        double containerHeight = imageContainer.getHeight();

        double scaleX = containerWidth / originalWidth;
        double scaleY = containerHeight / originalHeight;
        double scale = Math.min(scaleX, scaleY); // Preserve aspect ratio

        group.getTransforms().setAll(new Scale(scale, scale));
    }
}

