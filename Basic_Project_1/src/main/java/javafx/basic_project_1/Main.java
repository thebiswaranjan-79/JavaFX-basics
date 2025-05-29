package javafx.basic_project_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Parent secPage;

    @Override
    public void start(Stage stage) throws IOException {


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        //FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent mainPage = fxmlLoader.load();
        //Parent secPage = fxmlLoader1.load();

        Scene scene = new Scene(mainPage);

        String css = this.getClass().getResource("/styles/hello.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}