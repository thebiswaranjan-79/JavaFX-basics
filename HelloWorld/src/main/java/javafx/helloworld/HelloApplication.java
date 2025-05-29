package javafx.helloworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 450, 300, Color.SKYBLUE);
//        stage.setTitle("Hello Biswaranjan");
//
        // TO set the icon in the title
        Image icon = new Image(getClass().getResource("/logo.PNG").toExternalForm());
        stage.getIcons().add(icon);
        stage.getIcons().size();

//        // For the Fullsize property
//
////        stage.setFullScreen(true);
////        stage.setResizable(false);
//
//            // For Full Screen Exit these below 2 command are used
//     stage.setFullScreenExitHint("Press Q if You want to Quit From the Application ");
//     stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
//
//
//        Text txt = new Text();
//        txt.setText("Hey Welcome to My World");
//        txt.setX(50);
//        txt.setY(50);
//        txt.set
//        stage.setScene(scene);
//        stage.show();


        Group root = new Group();
        Scene scene = new Scene(root,600,600,Color.LIGHTSKYBLUE);
//        Stage stage = new Stage();

        Text text = new Text();
        text.setText("WHOOOOOA!!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.LIMEGREEN);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.BLUE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                200.0,200.0,
                300.0,300.0,
                200.0,300.0
        );
        triangle.setFill(Color.YELLOW);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.ORANGE);

//        Image image = new Image("pizza.png");
//        ImageView imageView = new ImageView(image);
//        imageView.setX(400);
//        imageView.setY(400);

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
//        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}