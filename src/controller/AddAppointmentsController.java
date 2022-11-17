package controller;

import database.DBContacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentsController implements Initializable {
    public ComboBox contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField userIdTextField;
    public TextField appointmentIdTextField;
    public DatePicker datePicker;
    public DatePicker endTimeDatePicker;
    public Button cancelButton;
    public Button saveButton;
    public ComboBox <LocalTime>startTimeComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox endTimeComboBox;

    private ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;

    // Sets Contacts observable list
    ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fills contact combo box
        contactComboBox.setItems(allContacts);

        for(int i = 1; i < 24; i++) {
            startTimes.add(LocalTime.of(i,0));
        }
        startTimes.add(LocalTime.of(0,0));
        startTimeComboBox.setItems(startTimes);

    }



    @FXML
    void OnActionSaveAppointment(ActionEvent event) throws IOException {

        this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
        this.stage.setScene(new Scene(this.scene));
        this.stage.setTitle("Main Screen");
        this.stage.show();
    }

    /** Close window method.
     @param event Closes window and returns to Main Screen. */
    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", new ButtonType[0]);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
            this.stage.setScene(new Scene(this.scene));
            this.stage.setTitle("Main Screen");
            this.stage.show();
        }
    }

    public void OnSelectStartTime(ActionEvent event) {
    }

    public void OnSelectEndTime(ActionEvent event) {
    }

    // initialize method - loop through observable list of times (run 23x for each case - start and end) midnight
    // - 2300 hrs
}
