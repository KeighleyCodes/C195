package controller;

import database.DBContacts;
import database.DBCustomer;
import database.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.*;

/** Main Screen Controller
    Opens Main Screen of application, consisting of tabs for Customers, Appointments and Reports. Customer and
    Appointment tab have tableviews and the ability to add, update and delete respective data in connected
    database. Reports tab produces two totals and one tableview pulled from database. */

public class MainScreenController implements Initializable {

    // CUSTOMERS
    @FXML
    private TableView<Customer> customerTable;
    public Tab customerTab;
    public TableColumn customerIdColumn;
    public TableColumn customerNameColumn;
    public TableColumn customerPhoneColumn;
    public TableColumn customerAddressColumn;
    public TableColumn customerPostalCodeColumn;
    public TableColumn customerDivisionIdColumn;
    public Button updateCustomerButton;
    public Button deleteCustomerButton;
    public Button logoutCustomerButton;

    // ALL APPOINTMENTS
    public Tab allAppointmentsTab;
    public TableView<Appointments> allAppointmentsTable;
    public TableColumn allAppointmentsIdColumn;
    public TableColumn allAppointmentsTitleColumn;
    public TableColumn allAppointmentsDescriptionColumn;
    public TableColumn allAppointmentsLocationColumn;
    public TableColumn allAppointmentsContactIdColumn;
    public TableColumn allAppointmentsTypeColumn;
    public TableColumn allAppointmentsDateColumn;
    public TableColumn monthlyDateColumn;
    public TableColumn allAppointmentsStartColumn;
    public TableColumn allAppointmentsEndColumn;
    public TableColumn allAppointmentsCustomerColumn;
    public TableColumn allAppointmentsUserIdColumn;

    // MONTHLY APPOINTMENTS
    @FXML
    private TableView<Appointments> monthlyViewTable;
    public Tab appointmentsTab;
    public Tab monthlyViewTab;
    public TableColumn monthlyAppointmentIdColumn;
    public TableColumn monthlyTitleColumn;
    public TableColumn monthlyDescriptionColumn;
    public TableColumn monthlyLocationColumn;
    public TableColumn monthlyContactColumn;
    public TableColumn monthlyTypeColumn;
    public TableColumn weeklyDateColumn;
    public TableColumn monthlyStartColumn;
    public TableColumn monthlyEndColumn;
    public TableColumn monthlyCustomerIdColumn;
    public TableColumn monthlyUserIdColumn;

    // WEEKLY APPOINTMENTS
    @FXML
    private TableView<Appointments> weeklyViewTable;
    public Tab weeklyViewTab;
    public TableColumn weeklyAppointmentIdColumn;
    public TableColumn weeklyTitleColumn;
    public TableColumn weeklyDescriptionColumn;
    public TableColumn weeklyLocationColumn;
    public TableColumn weeklyContactColumn;
    public TableColumn weeklyTypeColumn;
    public TableColumn weeklyStartColumn;
    public TableColumn weeklyEndColumn;
    public TableColumn weeklyCustomerIdColumn;
    public TableColumn weeklyUserIdColumn;
    public Button logoutAppointmentsButton;
    public Button deleteAppointmentsButton;
    public Button updateAppointmentsButton;
    public Button addAppointmentsButton;
    public Button logoutReportsButton;

    // REPORTS
    public Tab reportsTab;
    public ComboBox<String> monthSelectorBox;
    public ComboBox<String>typeSelectorBox;
    public ComboBox<Contacts> contactSelectorBox;
    public ComboBox<Customer> customerIdSelectorBox;
    public Button totalCustomerReportButton;
    public Button totalMonthAndTypeReport;
    public Label reportsByMonthLabel;
    public Label reportsByContactLabel;
    public Label reportsByCustomerLabel;

    private Stage stage;
    private Object scene;


    /** Initialize method
        @param url Initializes Main Screen. */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ---------------------------- TABLE VIEWS ---------------------------------------

        // CUSTOMER TABLE
        ObservableList<Customer> customerList = DBCustomer.getAllCustomers();
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerTable.setItems(customerList);


        // APPOINTMENT TABLE
        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        allAppointmentsIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        allAppointmentsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        allAppointmentsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        allAppointmentsLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        allAppointmentsContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        allAppointmentsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        allAppointmentsDateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        allAppointmentsStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        allAppointmentsEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        allAppointmentsCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        allAppointmentsUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        allAppointmentsTable.setItems(appointmentsList);


        // MONTHLY APPOINTMENT TABLE
        monthlyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        monthlyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyDateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        monthlyStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        monthlyEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        monthlyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));


        /** Lambda expression compares the local month of current user to that of all the appointments
            in the database. If they are equal, the appointment record is added to an observable list
            and then used to populate the monthly tableview. This expression reduces code required. */

        // LAMBDA EXPRESSION TO FILTER APPOINTMENTS BY MONTH
        LocalDateTime currentDay = LocalDateTime.now();
        ObservableList<Appointments> monthlyAppointments = FXCollections.observableArrayList();
        appointmentsList.forEach(appointments -> {
            if(appointments.getAppointmentDay().getMonth().equals(currentDay.getMonth())) {
                monthlyAppointments.add(appointments);
            }
        });
        monthlyViewTable.setItems(monthlyAppointments);


        // WEEKLY APPOINTMENT TABLE
        weeklyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        weeklyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyDateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        weeklyStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        weeklyEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        weeklyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        weeklyViewTable.setItems(DBAppointments.weeklyAppointments());



        // --------------------------- COMBO BOXES --------------------------------------

        // Fills combo boxes throughout the various tabs

        ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
        contactSelectorBox.setItems(allContacts);

        ObservableList<Customer> allCustomers = DBCustomer.getAllCustomers();
        customerIdSelectorBox.setItems(allCustomers);

        ObservableList<String> typesList = FXCollections.observableArrayList(
                "Coffee Chat", "De-Briefing", "Mentoring", "Planning Session", "Sprint Meeting", "Other");
                typeSelectorBox.setItems(typesList);

        ObservableList<String> monthsList = FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
                monthSelectorBox.setItems(monthsList);

    }


    // -------------------------------- CUSTOMERS ----------------------------------------

        /** Add Customer method.
            @param event Opens Add Customer screen when add button clicked. */

        @FXML
        void OnActionAddCustomer (ActionEvent event) throws IOException {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Add Customer");
            stage.show();

        }

        /** Update Customer method.
            @param event Opens Update Customer screen when update button clicked. */

        @FXML
        void OnActionUpdateCustomer (ActionEvent event) throws IOException {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/UpdateCustomer.fxml"));
                loader.load();

                UpdateCustomerController UCController = loader.getController();
                UCController.sendCustomer(customerTable.getSelectionModel().getSelectedItem());
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Customer");
                stage.show();

            } catch (NullPointerException n) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a customer record to modify");
                alert.show();
            }
        }


    /** Delete Customer method.
        @param event Deletes customer data when delete button clicked. */

        @FXML
        void OnActionDeleteCustomer (ActionEvent event) throws SQLException {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must select a customer to delete.");
                alert.showAndWait();
            } else if (customerTable.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete " + selectedCustomer.getCustomerName() + " from customer records?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    try {
                        ObservableList<Appointments> customerAppointments = DBAppointments.appointmentsByCustomer(selectedCustomer.getCustomerId());
                        if(customerAppointments.size() < 1) {
                            DBCustomer.deleteCustomer(selectedCustomer);
                            customerTable.getItems().clear();
                            customerTable.setItems(DBCustomer.getAllCustomers());
                        }
                        else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("You must first delete any appointments for " + selectedCustomer.getCustomerName() +
                                    " before deleting the customer.");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        /** Logout Customer method.
            @param event Returns to log in screen when log out button clicked. */

        @FXML
        void OnActionLogoutCustomer (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Main Screen");
                stage.show();
            }
        }


    // -------------------------------- APPOINTMENTS ----------------------------------------

        /** Add Appointment method.
            @param event Opens Add Appointment screen when add button clicked. */

        @FXML
        void  OnActionAddAppointment (ActionEvent event) throws IOException {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/AddAppointments.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Add Appointment");
            stage.show();
        }


        /** Update Appointment method.
            @param event Opens Update Appointment screen when update button clicked. */

        @FXML
        void OnActionUpdateAppointment (ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/UpdateAppointments.fxml"));
                loader.load();

                UpdateAppointmentController UAController = loader.getController();
                UAController.sendAppointment(allAppointmentsTable.getSelectionModel().getSelectedItem());
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Appointment");
                stage.show();

            } catch (NullPointerException n) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select an appointment to modify");
                alert.show();
            }
        }


    /** Delete Appointment method.
        @param event Deletes appointment data when delete button clicked. */

        @FXML
        void OnActionDeleteAppointment(ActionEvent event) {

            Appointments selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
            if (selectedAppointment == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must select an appointment to delete.");
                alert.showAndWait();
            } else if (allAppointmentsTable.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,  "Would you like to delete " +
                        selectedAppointment.getTitle() + " from database?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    try {
                        DBAppointments.deleteAppointment(selectedAppointment);
                        allAppointmentsTable.getItems().clear();
                        allAppointmentsTable.setItems(DBAppointments.getAllAppointments());
                        monthlyViewTable.getItems().clear();
                        monthlyViewTable.setItems(DBAppointments.getAllAppointments());
                        weeklyViewTable.getItems().clear();
                        weeklyViewTable.setItems(DBAppointments.getAllAppointments());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            assert selectedAppointment != null;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,  "Appointment " +
                    selectedAppointment.getAppointmentId() + ", " + selectedAppointment.getType() +
                    " has been deleted.");
            Optional<ButtonType> result = alert.showAndWait();
        }


        /** Logout Appointment method.
            @param event Goes back to log in screen when log out button clicked. */

        @FXML
        void onActionLogoutAppointments(ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Login");
                stage.show();
            }
        }


    // -------------------------------- REPORTS ----------------------------------------

        /** Logout Report method.
            @param event Goes back to log in screen when log out button clicked. */

        @FXML
        void OnActionLogoutReports (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Login");
                stage.show();
            }
        }


    /** Month and type report method.
        @param event Totals the amount of appointments by month and type. */

    public void OnActionMonthAndTypeSelection(ActionEvent event) {
         if(monthSelectorBox.getValue() == null || typeSelectorBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a month and type");
            alert.showAndWait();
        }
        else{
             reportsByMonthLabel.setText(String.valueOf(DBAppointments.appointmentsByMonthAndType(monthSelectorBox.getValue(), typeSelectorBox.getValue())));
        }
    }


    /** Customer report method.
        @param event Totals the amount of appointments by customer. */

    public void OnActionCustomerSelection(ActionEvent event) {
        if(customerIdSelectorBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a contact");
            alert.showAndWait();
        }
        else {
            reportsByCustomerLabel.setText(String.valueOf(DBAppointments.totalCustomers(customerIdSelectorBox.getValue().getCustomerId())));
        }
    }

    /** Contact report method.
        @param event Opens new window containing table view populated with appointment
        info filtered by selected contact in combo box. */

    public void onSelectionContact(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ContactReports.fxml"));
        loader.load();

        ContactReportsController CRController = loader.getController();
        CRController.sendContactAppointment(contactSelectorBox.getValue());

        stage = (Stage) ((ComboBox) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments by contact");
        stage.show();

    }

}

