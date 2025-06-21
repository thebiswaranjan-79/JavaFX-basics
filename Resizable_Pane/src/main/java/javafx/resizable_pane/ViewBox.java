package javafx.resizable_pane;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ViewBox extends Pane {

    @Override
    protected void layoutChildren() {
        if (getChildren().isEmpty()) return;

        Node child = getChildren().get(0); // Assume only one child

        // Get child’s preferred size
        double childPrefW = child.prefWidth(-1);
        double childPrefH = child.prefHeight(-1);


        double targetAspectRatio = childPrefW / childPrefH;

        double parentW = getWidth();
        double parentH = getHeight();
        double parentRatio = parentW / parentH;

        double finalW, finalH;

        if (parentRatio == targetAspectRatio) {
            finalW = parentW;
            finalH = parentH;
        } else if (targetAspectRatio < parentRatio) {
            finalH = parentH;
            finalW = targetAspectRatio * parentH;
        } else {
            finalW = parentW;
            finalH = parentW / targetAspectRatio;
        }

        double offsetX = (parentW - finalW) / 2;
        double offsetY = (parentH - finalH) / 2;

        child.resizeRelocate(offsetX, offsetY, finalW, finalH);
    }
}
