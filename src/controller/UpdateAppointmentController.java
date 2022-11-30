package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCustomer;
import database.DBUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customer;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public ComboBox contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField userIdTextField;
    public TextField appointmentIdTextField;
    public DatePicker startTimeDatePicker;
    public DatePicker endTimeDatePicker;
    public Button cancelButton;
    public Button saveButton;
    public TextField typeTextField;
    public DatePicker datePicker;
    public ComboBox<CharSequence> startTimeComboBox;
    public ComboBox<CharSequence> endTimeComboBox;
    public ComboBox<Customer> customerIdComboBox;
    public ComboBox<Users> userIdComboBox;

    private int appointmentId;

    private static Appointments appointments;

    private ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();

    private ObservableList<Users> allUsers = DBUser.getAllUsers();
    private ObservableList<Customer> allCustomers = DBCustomer.getAllCustomers();

    Stage stage;
    Parent scene;

    // Sets Contacts observable list
    ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(allContacts);

        customerIdComboBox.setItems(allCustomers);

        userIdComboBox.setItems(allUsers);

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

    public void OnActionCancel(ActionEvent event) throws IOException {
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

    public void OnActionSaveAppointment(ActionEvent event) throws SQLException {

        int contactId = contactComboBox.getSelectionModel().getSelectedIndex();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(datePicker.getValue(), LocalTime.parse(startTimeComboBox.getValue()));
        LocalDateTime endTime = LocalDateTime.of(datePicker.getValue(), LocalTime.parse(endTimeComboBox.getValue()));
        int customerId = customerIdComboBox.getValue().getCustomerId();
        int userId = userIdComboBox.getValue().getUserId();
        DBAppointments.updateAppointment(title, description, location, contactId, type, startTime, endTime, customerId, userId, appointmentId);
    }


    public void sendAppointment() {
        appointmentId = appointments.getAppointmentId();
        contactComboBox.setValue(appointments.getContactId());
        titleTextField.setText(appointments.getTitle());
        descriptionTextField.setText(appointments.getDescription());
        locationTextField.setText(appointments.getLocation());
        typeTextField.setText(appointments.getType());
        startTimeComboBox.setValue(appointments.getStartTime());
        endTimeComboBox.setValue(appointments.getEndTime());
        customerIdComboBox.setValue(appointments.getCustomerId());
        // send to DB, return customer object

    }



    public void OnSelectStartTime(ActionEvent event) {
    }

    public void OnSelectEndTime(ActionEvent event) {
    }
}
