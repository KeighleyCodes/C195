package main;

import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/** Main class - this initializes the application and opens the login screen */
public class Main extends Application {

    /*
    ResourceBundle rb = ResourceBundle.getBundle("properties.FR.properties", Locale.getDefault() );
    if (Locale.getDefault().getLanguage().equals("fr")) {

        System.out.println(rb.getString("Enter") + rb.getString("username") + rb.getString("and")
                + rb.getString("password"));
    }

     */

    /** Loads Login page on initialization.
     @param stage Loads main stage. */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        stage.setTitle("Main Inventory");
        stage.setScene(new Scene(root, 400.0, 200.0));
        stage.show();
    }

    /** Add test data method - adds test data. */

    /*
    private static void addTestData() {

        Customer one = new Customer(23159, "Bob", "2133 Main St", "12789", "8146678977",null ,"Jessica",null, 5497 );
        Customer.addCustomer(one);
    }

     */

    // Test translation to French ** FIX ME **
   // Locale.setDefault(new Locale("fr"));

    /** Main method.
     @param args Launches program.
     Javadocs are in a directory in project zip file. */
    public static void main(String[] args) {
        DBConnection.openConnection();
       // addTestData();
        launch(args);
       // DBConnection.closeConnection();
    }
}