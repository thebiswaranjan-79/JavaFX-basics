package javafx.testproject_1.utils;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ImageViewController {
    private final ImageView imageView;

    private boolean isPanningEnabled = false;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private double initialTranslateX;
    private double initialTranslateY;
    private boolean zoomModeActive = false;

    public void toggleZoomMode(Button zoomBtn) {
        zoomModeActive = !zoomModeActive;
        System.out.println("Zoom Mode: " + (zoomModeActive ? "Activated" : "Deactivated"));

        imageView.setCursor(zoomModeActive ? Cursor.CROSSHAIR : Cursor.DEFAULT);
        zoomBtn.setStyle(zoomModeActive
                ? "-fx-background-color: #90ee90;"      // light green when active
                : "-fx-background-color: lightblue;");  // light blue when inactive

        if (zoomModeActive) {
            imageView.setOnScroll(e -> {
                double delta = e.getDeltaY();
                double zoomFactor = (delta > 0) ? 0.1 : -0.1;

                double newScaleX = imageView.getScaleX() + zoomFactor;
                double newScaleY = imageView.getScaleY() + zoomFactor;

                double minScaleX = 10.0 / imageView.getImage().getWidth();
                double minScaleY = 10.0 / imageView.getImage().getHeight();

                // Clamp to minimum scale
                if (newScaleX >= minScaleX && newScaleY >= minScaleY) {
                    imageView.setScaleX(newScaleX);
                    imageView.setScaleY(newScaleY);
                }
            });
        } else {
            imageView.setOnScroll(null); // remove scroll listener
        }
    }

    public ImageViewController(ImageView imageView) {
        this.imageView = imageView;
    }

    public void zoomIn() {
        imageView.setScaleX(imageView.getScaleX() + 0.1);
        imageView.setScaleY(imageView.getScaleY() + 0.1);
    }

    public void resetImage(){
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public void togglePanning(Button panButton) {
        isPanningEnabled = !isPanningEnabled;

        if (isPanningEnabled) {
            panButton.setStyle("-fx-background-color: #90ee90;"); // greenish to show ON
            enablePanning();
        } else {
            panButton.setStyle("-fx-background-color: lightblue;"); // default/off
            disablePanning();
        }
    }

    public void disablePanning() {
        imageView.setOnMousePressed(null);
        imageView.setOnMouseDragged(null);
    }


    public void enablePanning() {
        imageView.setOnMousePressed(event -> {
            mouseAnchorX = event.getSceneX();
            mouseAnchorY = event.getSceneY();
            initialTranslateX = imageView.getTranslateX();
            initialTranslateY = imageView.getTranslateY();
        });

        imageView.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseAnchorX;
            double deltaY = event.getSceneY() - mouseAnchorY;
            imageView.setTranslateX(initialTranslateX + deltaX);
            imageView.setTranslateY(initialTranslateY + deltaY);
        });
    }

    public void flipHorizontal() {
        imageView.setScaleX(imageView.getScaleX() * -1);
    }


    public void flipVertical() {
        imageView.setScaleY(imageView.getScaleY() * -1);
    }

    public void rotateClockwise(){
        imageView.setRotate(imageView.getRotate()+90);
    }

    public void roateAntiClockwise(){
        imageView.setRotate(imageView.getRotate()-90);
    }

}
