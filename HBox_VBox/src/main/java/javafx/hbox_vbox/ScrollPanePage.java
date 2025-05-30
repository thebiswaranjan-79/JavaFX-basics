package javafx.hbox_vbox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ScrollPanePage {


    @FXML
    private AnchorPane mainPane; // Inject the anchor pane from FXML

    public void initialize(){

        // Main container that will hold multiple HBoxes & VBoxes
        VBox mainContent = new VBox(20); // spacing between children

        int counter = 1;

        for (int i = 0; i < 15; i++) {
            HBox row = new HBox(15);
            for (int j = 0; j < 5; j++) {

                Rectangle box = new Rectangle(90, 50, Color.LIGHTBLUE);

                Label number = new Label(String.valueOf(counter++));
                number.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

                StackPane stack = new StackPane();
                stack.getChildren().addAll(box, number);

                row.getChildren().add(stack);
            }
            mainContent.getChildren().add(row);
        }


        // Wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);  // Horizontal scrolling disabled
        scrollPane.setPannable(true);    // Allow dragging


        // Set layout constraints (optional, but good to fit full anchor pane)
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        // Add ScrollPane to mainPane
        mainPane.getChildren().add(scrollPane);


    }
      // Added VBoxes and HBoxes that go beyond 600x500


}
