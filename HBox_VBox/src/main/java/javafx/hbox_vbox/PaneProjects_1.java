package javafx.hbox_vbox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class PaneProjects_1 {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Rectangle redRect;
    @FXML
    private Label coordLabel;

    private double dragOffsetX;
    private double dragOffsetY;

    @FXML
    public void initialize(){
        setupDraggable(redRect);
    }

    private void setupDraggable(Rectangle redRect) {
        redRect.setOnMousePressed(event -> {
            dragOffsetX = event.getSceneX() - redRect.getLayoutX();
            dragOffsetY = event.getSceneY() - redRect.getLayoutY();
        });

        redRect.setOnMouseDragged(event -> {
            double newX = event.getSceneX() - dragOffsetX;
            double newY = event.getSceneY() - dragOffsetY;

            double maxX = mainPane.getWidth() - redRect.getWidth();
            double maxY = mainPane.getHeight() - redRect.getHeight();

            if (newX < 0) newX = 0;
            if (newY < 0) newY = 0;
            if (newX > maxX) newX = maxX - redRect.getWidth();
            if (newY > maxY) newY = maxY - redRect.getHeight();

            redRect.setLayoutX(newX);
            redRect.setLayoutY(newY);
        });

    }


}
