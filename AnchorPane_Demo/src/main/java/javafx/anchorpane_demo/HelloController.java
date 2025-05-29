package javafx.anchorpane_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane topPane;

    private Button b;

    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle){
        b = new Button("My Button");
        topPane.getChildren().add(b);
    }

    public void clickButton(ActionEvent e){
//        b.setLayoutX(100);
//        b.setLayoutY(150);


    }

   }