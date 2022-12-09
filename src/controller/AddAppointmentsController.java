package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCustomer;
import database.DBUser;
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
import model.Appointments;
import model.Contacts;
import model.Customer;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentsController implements Initializable {
    public ComboBox<Contacts> contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField typeTextField;
    public TextField userIdTextField;
    public TextField appointmentIdTextField;
    public DatePicker datePicker;
    public Button cancelButton;
    public Button saveButton;
    public ComboBox <LocalTime> startTimeComboBox;
    public ComboBox <LocalTime> endTimeComboBox;
    public ComboBox<Customer> customerIdComboBox;
    public ComboBox<Users> userIdComboBox;
    public ComboBox typeComboBox;

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
        // Fills combo boxes
        contactComboBox.setItems(allContacts);

        customerIdComboBox.setItems(allCustomers);

        userIdComboBox.setItems(allUsers);

        fillTimeComboBoxes();

        // Populates combo box with types
        ObservableList<String> typesList = FXCollections.observableArrayList(
                "Coffee Chat", "De-Briefing", "Mentoring", "Planning Session", "Sprint Meeting", "Other"
        );
        typeComboBox.setItems(typesList);

    }

    public void fillTimeComboBoxes() {
        ObservableList<LocalTime> times = FXCollections.observableArrayList();
        LocalTime startTimes = LocalTime.of(8, 0);
        LocalTime endTimes = LocalTime.of(22, 0);

        times.add(startTimes);

        while (startTimes.isBefore(endTimes)) {
            startTimes = startTimes.plusMinutes(15);
            times.add(startTimes);
        }

        startTimeComboBox.setItems(times);
        endTimeComboBox.setItems(times);
    }

    @FXML
    void OnActionSaveAppointment(ActionEvent event) throws IOException {

        int contactId = contactComboBox.getValue().getContactId();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(datePicker.getValue(), startTimeComboBox.getValue());
        LocalDateTime endTime = LocalDateTime.of(datePicker.getValue(), endTimeComboBox.getValue());
        int customerId = customerIdComboBox.getValue().getCustomerId();
        int userId = userIdComboBox.getValue().getUserId();
        DBAppointments.insertAppointment(contactId, title, description, location, type, startTime, endTime, customerId, userId);

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

}
