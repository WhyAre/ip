package jank.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the UI
 */
public class Main extends Application {


    @Override
    public void start(Stage stage) {
        try {
            URL url = getClass().getResource("/main.fxml");
            Objects.requireNonNull(url);
            Parent root = FXMLLoader.load(url);
            var scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
