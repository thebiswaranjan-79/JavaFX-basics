package javafx.resizable_pane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ResponsiveSquarePane squarePane = new ResponsiveSquarePane();
        squarePane.setStyle("-fx-background-color: lightgray;");

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: red;");
        grid.setPrefSize(500, 500); // Large logical size

        squarePane.getChildren().add(grid);
        Scene scene = new Scene(squarePane , 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}