package main;

import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/** Main class - this initializes the application and opens the login screen */
public class Main extends Application {

    /** Loads Login page on initialization.
     @param stage Loads main stage. */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400.0, 200.0));
        stage.show();
    }



    /** Main method.
     @param args Launches program.
     Javadocs are in a directory in project zip file. */
    public static void main(String[] args) {
        DBConnection.openConnection();

        Locale.setDefault(new Locale("fr")); // to test that language changed to French

/*
        ResourceBundle rb = ResourceBundle.getBundle("languages/localization/localization", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {

            System.out.println(rb.getString("Enter") + rb.getString("username") + rb.getString("and")
                    + rb.getString("password"));
        }

 */
        launch(args);
        DBConnection.closeConnection();
    }
}