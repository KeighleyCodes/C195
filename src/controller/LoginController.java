package controller;

import database.DBConnection;
import database.DBQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

import static javafx.fxml.FXMLLoader.load;

/** Login controller - initializes the login form UI where the program begins for the user. */
public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public TextField usernameTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button enterButton;

    @FXML
    public Button exitButton;

    @FXML
    public Label zoneIdLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Displays local time zone
        zoneIdLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));

        // Translates login page to French
        ResourceBundle rb = ResourceBundle.getBundle("languages.localization", Locale.getDefault());

      /* if (Locale.getDefault().getLanguage().equals("fr")) {

            System.out.println(rb.getString("Enter") + rb.getString("username") + rb.getString("and")
                    + rb.getString("password"));
        }

       */

    }

    private boolean validLogin() throws IOException {
        ObservableList<Users> allUsers = DBQuery.getAllUsers();
        for (Users user : allUsers) {
            if (user.getUsername().equals(usernameTextField.getText()) && user.getPassword().equals(passwordTextField.getText())) {
                Users.currentUser = user;
                return true;
            }
        }
        return false;
    }


        /** Enter method. Enters Main Screen when enter button clicked. */
    @FXML
    void OnActionEnter(ActionEvent event) throws IOException {

        if (validLogin()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainScreen.fxml"));
            loader.load();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Screen");
            stage.show();

        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please provide valid username and / or password");
            alert.showAndWait();
        }

    }


    /**
     * Exit method. Displays confirmation box and exits program when exit button clicked.
     */
    @FXML
    void OnActionExit() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

}
