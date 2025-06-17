package javafx.resizable_pane;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ResponsiveSquarePane extends Pane {

    @Override
    protected void layoutChildren() {
        double containerWidth = getWidth();
        double containerHeight = getHeight();

        // Choose the smaller dimension
        double size = Math.min(containerWidth, containerHeight);

        double offsetX = (containerWidth - size) / 2;
        double offsetY = (containerHeight - size) / 2;

        for (Node child : getChildren()) {
            child.resizeRelocate(offsetX, offsetY, size, size);
        }
    }

    @Override
    protected double computePrefWidth(double height) {
        return height;
    }

    @Override
    protected double computePrefHeight(double width) {
        return width;
    }
}
