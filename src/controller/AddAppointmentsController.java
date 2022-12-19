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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentsController implements Initializable {
    public ComboBox<Contacts> contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField appointmentIdTextField;
    public DatePicker datePicker;
    public Button cancelButton;
    public Button saveButton;
    public ComboBox <LocalTime> startTimeComboBox;
    public ComboBox <LocalTime> endTimeComboBox;
    public ComboBox<Customer> customerIdComboBox;
    public ComboBox<Users> userIdComboBox;
    public ComboBox<String> typeComboBox;

    Stage stage;
    Parent scene;

    // OBSERVABLE LISTS
    private ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();
    private ObservableList<Users> allUsers = DBUser.getAllUsers();
    private ObservableList<Customer> allCustomers = DBCustomer.getAllCustomers();
    private ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ---------------- COMBO BOXES ----------------------------
        contactComboBox.setItems(allContacts);

        customerIdComboBox.setItems(allCustomers);

        userIdComboBox.setItems(allUsers);

        fillTimeComboBoxes();

        ObservableList<String> typesList = FXCollections.observableArrayList(
                "Coffee Chat", "De-Briefing", "Mentoring", "Planning Session", "Sprint Meeting", "Other");
        typeComboBox.setItems(typesList);

    }

    // Method to fill time combo boxes
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


    // CHECKS TO SEE IF DATE AND TIMES ARE AFTER CURRENT DAY AND START TIME ISN'T BEFORE, AFTER
    // OR THE SAME AS THE END TIME.
    public Boolean validTimes() {

        LocalDate startDate = datePicker.getValue();
        LocalTime startTime = startTimeComboBox.getValue();
        LocalTime endTime = endTimeComboBox.getValue();


        if(startDate.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid date");
            alert.setContentText("Please select a valid date.");
            alert.showAndWait();
            return false;
        }

        if(startTime.isBefore(LocalTime.of(8,0)) ||
                (endTime.isAfter(LocalTime.of(22,0)))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Outside business hours");
            alert.setContentText("Please select a time during business hours.");
            alert.showAndWait();
            return false;
        }

        if(endTime.isBefore(startTime) || endTime.equals(startTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect times");
            alert.setContentText("Please choose valid start and end times.");
            alert.showAndWait();
            return false;
        }

        // CHECK FOR OVERLAPPING APPOINTMENTS FOR SELECTED CUSTOMER

        LocalTime selectedStartTime = startTimeComboBox.getValue();
        LocalTime selectedEndTime = endTimeComboBox.getValue();

        ObservableList<Appointments> customerAppointments = DBAppointments.appointmentsByCustomer(customerIdComboBox.getValue().getCustomerId());
        for (Appointments appointments: customerAppointments) {
            LocalTime proposedStart = appointments.getStartTime();
            LocalTime proposedEnd = appointments.getEndTime();

            if ((proposedStart.isAfter(selectedStartTime) || proposedStart.equals(selectedStartTime))
                    && proposedStart.isBefore(selectedEndTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Overlapping appointments");
                alert.setContentText("New appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return false;
            }
            if (proposedEnd.isAfter(selectedStartTime) && (proposedEnd.isBefore(selectedEndTime) ||
                    (proposedEnd.equals(selectedEndTime)))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Overlapping appointments");
                alert.setContentText("New appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return false;
            }
            if ((proposedStart.isBefore(selectedStartTime) || proposedStart.equals(selectedStartTime)) &&
                    (proposedEnd.isAfter(selectedEndTime)) || proposedEnd.equals(selectedEndTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Overlapping appointments");
                alert.setContentText("New appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return false;
            }
        }

        return true;
    }

    // ************************ FIX ME ***********************************************


    public Boolean validAppointments() {



        /*
        try {
            for (Appointments appointments: customerAppointments) {


            }
        }
        catch (SQLException e) {

        }

         */

        return true;
    }



    // Make method for scheduling appointments
    // Could populate through observable list and loop through or query database

    // populate combo with lambda or filter with lambda (chose division based on country)


    @FXML
    void OnActionSaveAppointment(ActionEvent event) throws IOException {

        boolean timesAreValid = validTimes();
       // boolean appointmentsAreValid = validAppointments();

        if(timesAreValid) {
            int contactId = contactComboBox.getValue().getContactId();
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            String type = typeComboBox.getValue();
            LocalDateTime startTime = LocalDateTime.of(datePicker.getValue(), startTimeComboBox.getValue());
            LocalDateTime endTime = LocalDateTime.of(datePicker.getValue(), endTimeComboBox.getValue());
            int customerId = customerIdComboBox.getValue().getCustomerId();
            int userId = userIdComboBox.getValue().getUserId();
            DBAppointments.insertAppointment(contactId, title, description, location, type, startTime, endTime, customerId, userId);

            this.stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
            this.stage.setScene(new Scene(this.scene));
            this.stage.setTitle("Main Screen");
            this.stage.show();
        }

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
