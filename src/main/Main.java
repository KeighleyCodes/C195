package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.util.Objects;

/** Main class - this initializes the application and opens the login screen */
public class Main extends Application {

    /** Loads Login page on initialization.
     @param stage Loads main stage. */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        stage.setTitle("Main Inventory");
        stage.setScene(new Scene(root, 400.0, 200.0));
        stage.show();
    }

    // Test translation to French ** FIX ME **
   // Locale.setDefault(new Locale("fr"));

    /** Main method.
     @param args Launches program.
     Javadocs are in a directory in project zip file. */
    public static void main(String[] args) {
        launch(args);
    }
}