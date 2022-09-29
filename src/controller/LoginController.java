package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

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

    }

    /** Enter method. Enters Main Screen when enter button clicked. */
    // *** FIX ME - need to have correct login info to proceed ***
    @FXML
    void OnActionEnter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MainScreen.fxml"));
        loader.load();

       // MainScreenController MSController = loader.getController();
       // APController.sendParts(mainPartsTable.getItems());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Main Screen");
        stage.show();
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
