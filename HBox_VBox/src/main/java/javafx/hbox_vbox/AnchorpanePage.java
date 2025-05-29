package javafx.hbox_vbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class AnchorpanePage {
    @FXML private AnchorPane rootPane;
    @FXML private Button addLabelBtn;
    @FXML private Button addButtonBtn;
    @FXML private TextArea inputArea;

    private int labelCounter = 1;
    private int buttonCounter = 1;
    private final Random random = new Random();


    @FXML
    public  void initialize(){

        // Adding the Label Handler

        addLabelBtn.setOnAction(e-> {
            Label newLabel = new Label("Label " + labelCounter++);
            double top = 60 + random.nextInt(250);
            double left = 30 + random.nextInt(300);
            double right = 30 + random.nextInt(300);
            AnchorPane.setRightAnchor(newLabel, right);
            AnchorPane.setTopAnchor(newLabel, top);
            AnchorPane.setLeftAnchor(newLabel, left);
            rootPane.getChildren().add(newLabel);
        });

        // Add Button Handler
        addButtonBtn.setOnAction(e -> {
            Button newBtn = new Button("Button " + buttonCounter++);
            double top = 60 + random.nextInt(250);
            double left = 30 + random.nextInt(300);
            double right = 30 + random.nextInt(300);
            AnchorPane.setRightAnchor(newBtn, right);
            AnchorPane.setTopAnchor(newBtn, top);
            AnchorPane.setLeftAnchor(newBtn, left);
            rootPane.getChildren().add(newBtn);
        });


        // Right Click Context Menu

        ContextMenu contextMenu = new ContextMenu();
        MenuItem clearItem = new MenuItem("Clear Text");
        MenuItem copyItem = new MenuItem("Copy");
        contextMenu.getItems().addAll(clearItem, copyItem);

        clearItem.setOnAction(e -> inputArea.clear());
        copyItem.setOnAction(e -> inputArea.copy());

        rootPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(rootPane, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

    }

}
