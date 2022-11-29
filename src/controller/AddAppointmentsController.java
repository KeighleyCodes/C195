package controller;

import database.DBAppointments;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentsController implements Initializable {
    public ComboBox contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField typeTextField;
    public TextField userIdTextField;
    public TextField appointmentIdTextField;
    public DatePicker datePicker;
    public DatePicker endTimeDatePicker;
    public Button cancelButton;
    public Button saveButton;
    public ComboBox <CharSequence> startTimeComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox <CharSequence> endTimeComboBox;
    public ComboBox customerIdComboBox;
    public ComboBox userIdComboBox;

    private ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    // Sets Contacts observable list
    ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fills contact combo box
        contactComboBox.setItems(allContacts);

        //  customerIdComboBox.setItems();  *********** CREATE CUSTOMER ID OBSERVABLE LIST

        // userIdComboBox.setItems(); ************** CREATE USER ID OBSERVABLE LIST

        // Fills time combo boxes
        fillTimeComboBoxes();

    }

    public void fillTimeComboBoxes() {
        ObservableList<CharSequence> times = FXCollections.observableArrayList();
        LocalTime startTimes = LocalTime.of(8, 0);
        LocalTime endTimes = LocalTime.of(22, 0);

        times.add(startTimes.toString());

        while (startTimes.isBefore(endTimes)) {
            startTimes = startTimes.plusMinutes(15);
            times.add(startTimes.toString());
        }

        startTimeComboBox.setItems(times);
        endTimeComboBox.setItems(times);
    }

    @FXML
    void OnActionSaveAppointment(ActionEvent event) throws IOException {

       // System.out.println(startDatePicker.getValue());
      //  System.out.println(datePicker.getValue());
        int contactId = contactComboBox.getSelectionModel().getSelectedIndex();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(datePicker.getValue(), LocalTime.parse(startTimeComboBox.getValue()));
        LocalDateTime endTime = LocalDateTime.of(datePicker.getValue(), LocalTime.parse(endTimeComboBox.getValue()));
        DBAppointments.insertAppointment(contactId, title, description, location, type, startTime, endTime);

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

}
