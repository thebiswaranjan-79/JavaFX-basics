package javafx.resizable_pane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.<Parent>load(getClass().getResource("/hello-view.fxml"));
        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("FXML + ViewBox Demo");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
