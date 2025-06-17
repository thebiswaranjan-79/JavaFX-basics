package javafx.testproject_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("webLayout.fxml"));
        Scene scene = new Scene(root, 1920, 1080);

        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D bounds1 = primaryScreen.getVisualBounds();
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setX(bounds1.getMinX());
        mainStage.setY(bounds1.getMinY());
        mainStage.setWidth(bounds1.getWidth());
        mainStage.setHeight(bounds1.getHeight());
        mainStage.setMaximized(true);
        mainStage.setFullScreen(true);
//        mainStage.setFullScreenExitHint("");
        mainStage.setTitle("Test Project");
        mainStage.setScene(scene);
        mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        mainStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}

