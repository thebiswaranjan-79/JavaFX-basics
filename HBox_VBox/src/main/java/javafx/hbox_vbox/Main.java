package javafx.hbox_vbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

//    private String storedUsername = "admin"; // Default username
//    private String storedPassword= "123456"; // Default username

    @Override
    public void start(Stage stage) throws IOException {
//
//        Parent root = FXMLLoader.load(getClass().getResource("AnchorpanePage.fxml"));
//
//        Scene scene = new Scene(root, 600, 420);
//        stage.setTitle("AnchorPane ShowCase");
//        stage.setScene(scene);
//        stage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("ScrollPane_Project.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("Paneproject-1.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("BorderPaneLayout.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("GridPane.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("StackPaneLayout.fxml"));
        stage.setTitle("JavaFX Drag & Drop Demo");
        stage.setScene(new Scene(root, 1920, 1080));
        stage.setFullScreen(true);
        stage.show();




//       Parent root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));

//        VBox vbox = new VBox(10); // 10px spacing (we can set the default spacing)
//        HBox hbox = new HBox(10);
//        HBox hbox1 = new HBox(10);
//
//
//
//        Label l1 = new Label("Username : ");
//        TextField tf1 = new TextField();
//        Label l2 = new Label("Password : ");
//        TextField tf2 = new TextField();
//
//        Label resultLabel = new Label();
//
//        hbox.getChildren().addAll(l1, tf1);
//        hbox1.getChildren().addAll(l2, tf2);
//
//        Button bt1 = new Button("Login");
//        vbox.setAlignment(Pos.CENTER); // Overrides HBox's default alignment
//        vbox.getChildren().addAll(hbox, hbox1,bt1, resultLabel);
//        vbox.setPadding(new Insets(20));
//
//
//    // Step -2
//        // Adding CSS to the Fieilds and Labels
//        vbox.getStyleClass().add("vbox-root");
//        l1.getStyleClass().add("label");
//        tf1.getStyleClass().add("text-field");
//        bt1.getStyleClass().add("button");
//        resultLabel.getStyleClass().add("result-label");
//
//
//        // 3. Add Functionality
//        bt1.setOnAction( e->{
//            String inputUserName = tf1.getText().trim();
//            String inputPassword = tf2.getText().trim();
//
//            if (inputUserName.equals(storedUsername) && inputPassword.equals(storedPassword)){
//                resultLabel.setText("Login successful! Welcome, " + inputUserName + ".");
//                resultLabel.setStyle("-fx-text-fill: green;"); // Green for success
//            }else{
//                resultLabel.setText("Invalid username. Try again.");
//                resultLabel.setStyle("-fx-text-fill: red;"); // Red for error
//            }
//        });
//
//        Scene scene = new Scene(vbox, 320, 240);
//
//        // Step 4 => Load CSS from external files
//        scene.getStylesheets().add(getClass().getResource("/style/styles.css").toExternalForm());

//        stage.setTitle("Styled Login App!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}