package javafx.resizable_pane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GridPane content = new GridPane();
        content.setStyle("-fx-background-color: tomato;");
        content.setHgap(10);
        content.setVgap(10);

        // Just set pref size
        content.setPrefSize(300, 600);

        // Responsive pane (no aspect ratio passed!)
        ViewBox squarePane = new ViewBox();
        squarePane.setStyle("-fx-background-color: lightgray;");
        squarePane.getChildren().add(content);

        StackPane root = new StackPane(squarePane);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Auto-Aspect Ratio Responsive Pane");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
