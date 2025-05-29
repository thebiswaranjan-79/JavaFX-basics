package javafx.basic_project_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class HelloController {

    public void initialize(){
        System.out.println("initialized");
    }

    public AnchorPane rootpane;


    @FXML
    private Label welcomeText;

    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void up(ActionEvent e){
        System.out.println("UP");
        myCircle.setCenterY(y -= 10);
    }

    public void down(ActionEvent e){
        System.out.println("Down");
        myCircle.setCenterY(y += 10);
    }

    public void left(ActionEvent e){
        System.out.println("Left");
        myCircle.setCenterX(x -= 10);
    }

    public void right(ActionEvent e){
        System.out.println("Right");
        myCircle.setCenterX(x += 10);
    }

    public void TopLeft(ActionEvent e){
        System.out.println("TopLeft");
        myCircle.setCenterX(x -= 10);
        myCircle.setCenterY(y -= 10);
    }

    public void TopRight(ActionEvent e){
        System.out.println("TopRight");
        myCircle.setCenterX(x += 10);
        myCircle.setCenterY(y -= 10);
    }
    public void BotRight(ActionEvent e){
        System.out.println("Bottom Right");
        myCircle.setCenterX(x += 10);
        myCircle.setCenterY(y += 10);
    }
    public void BotLeft(ActionEvent e){

        rootpane.setStyle("-fx-background-color:red");
        System.out.println("Bottom Left");
        myCircle.setCenterX(x -= 10);
        myCircle.setCenterY(y += 10);
    }
}