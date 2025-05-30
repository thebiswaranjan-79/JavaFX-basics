package javafx.hbox_vbox;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PaneProjects_1 {

    @FXML
    private Pane boxPane; // This should be the container where you want to restrict the rectangle

    @FXML
    private Rectangle redRect;

    private double currentMouseTopPosition;
    private double currentMouseLeftPosition;

    @FXML
    public void initialize() {
        setupDraggable();
        System.out.println("Initialized .....");
    }

    private void setupDraggable() {
        redRect.setOnMousePressed((MouseEvent event) -> {
            currentMouseLeftPosition = event.getSceneX() - redRect.getLayoutX();
            currentMouseTopPosition = event.getSceneY() - redRect.getLayoutY();

            System.out.println("The X axis is "+ currentMouseLeftPosition);
            System.out.println("The Y axis is "+ currentMouseTopPosition);
        });

        redRect.setOnMouseDragged((MouseEvent event) -> {
            double newMouseX = event.getSceneX() - currentMouseLeftPosition;
            double newMouseY = event.getSceneY() - currentMouseTopPosition;

            double maxX = boxPane.getWidth() - redRect.getWidth();
            double maxY = boxPane.getHeight() - redRect.getHeight();

            // X-boundary check
            if (newMouseX < 0) {
                newMouseX = 0;
            } else if (newMouseX > maxX) {
                newMouseX = maxX;
            }

            // Y-boundary check
            if (newMouseY < 0) {
                newMouseY = 0;
            } else if (newMouseY > maxY) {
                newMouseY = maxY;
            }

            redRect.setLayoutX(newMouseX);
            redRect.setLayoutY(newMouseY);
        });
    }

}
