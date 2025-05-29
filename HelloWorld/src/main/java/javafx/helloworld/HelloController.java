package javafx.helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    Boolean isSetText = true;

    @FXML
    protected void onHelloButtonClick() {

        if(isSetText){
            welcomeText.setText("Welcome to JavaFX Application!");

        }else{
            welcomeText.setText("");

        }

        isSetText = !isSetText;
    }

}