package controller;


import database.DBContacts;
import database.DBCustomer;
import database.DBAppointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class MainScreenController implements Initializable {

    public Tab customerTab;
    public TableColumn customerIdColumn;
    public TableColumn customerNameColumn;
    public TableColumn customerPhoneColumn;
    public TableColumn customerAddressColumn;
    public TableColumn customerStateColumn;
    public TableColumn customerCountryColumn;
    public TableColumn customerPostalCodeColumn;
    public TableColumn customerDivisionIdColumn;
    public Button updateCustomerButton;
    public Button deleteCustomerButton;
    public Button logoutCustomerButton;
    public Tab appointmentsTab;
    public Tab monthlyViewTab;
    public TableColumn monthlyAppointmentIdColumn;
    public TableColumn monthlyTitleColumn;
    public TableColumn monthlyDescriptionColumn;
    public TableColumn monthlyLocationColumn;
    public TableColumn monthlyContactColumn;
    public TableColumn monthlyTypeColumn;
    public TableColumn monthlyStartColumn;
    public TableColumn monthlyEndColumn;
    public TableColumn monthlyCustomerIdColumn;
    public TableColumn monthlyUserIdColumn;
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
    public Button addReportsButton;
    public Button updateReportsButton;
    public Button deleteReportsButton;
    public Button logoutReportsButton;
    public Tab appointmentsByMonthTab;
    public Tab reportsTab;
    public ComboBox monthSelectorBox;
    public ComboBox typeSelectorBox;
    public TableColumn monthlyReportAppointmentIdColumn;
    public TableColumn monthlyReportTitleColumn;
    public TableColumn monthlyReportDescriptionColumn;
    public TableColumn monthlyReportLocationColumn;
    public TableColumn monthlyReportContactColumn;
    public TableColumn monthlyReportTypeColumn;
    public TableColumn monthlyReportStartColumn;
    public TableColumn monthlyReportEndColumn;
    public TableColumn monthlyReportCustomerIdColumn;
    public TableColumn monthlyReportUserIdColumn;
    public Tab appointmentsByContactTab;
    public TableColumn contactAppointmentIdColumn;
    public TableColumn contactTitleColumn;
    public TableColumn contactDescriptionColumn;
    public TableColumn contactLocationColumn;
    public TableColumn contactContactColumn;
    public TableColumn contactTypeColumn;
    public TableColumn contactStartColumn;
    public TableColumn contactEndColumn;
    public TableColumn contactCustomerColumn;
    public TableColumn contactUserIdColumn;
    public ComboBox<Contacts> contactSelectorBox;
    public Tab customerReportTab;
    public TableView appointmentsByCustomerTable;
    public TableColumn customerAppointmentIdColumn;
    public TableColumn customerTitleColumn;
    public TableColumn customerDescriptionColumn;
    public TableColumn customerLocationColumn;
    public TableColumn customerContactColumn;
    public TableColumn customerTypeColumn;
    public TableColumn customerStartDateColumn;
    public TableColumn customerEndDateColumn;
    public TableColumn customerCustomerIdColumn;
    public TableColumn customerUserIdColumn;
    public ComboBox<Customer> customerIdSelectorBox;
    public TableColumn divisionIdColumn;
    public Label reportsByMonthLabel;
    public Label reportsByContactLabel;
    public Label reportsByCustomerLabel;
    public Button totalAppointmentReportButton;
    public Button totalContactButton;
    public Button totalCustomerButton;
    public Tab allAppointmentsTab;
    public TableView allAppointmentsTableview;
    public TableColumn allAppointmentsIdColumn;
    public TableColumn allAppointmentsTitleColumn;
    public TableColumn allAppointmentsDescriptionColumn;
    public TableColumn allAppointmentsLocationColumn;
    public TableColumn allAppointmentsContactIdColumn;
    public TableColumn allAppointmentsTypeColumn;
    public TableColumn allAppointmentsStartColumn;
    public TableColumn allAppointmentsEndColumn;
    public TableColumn allAppointmentsCustomerColumn;
    public TableColumn allAppointmentsUserIdColumn;
    private Stage stage;
    private Object scene;

    Appointments selectedAppointment;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableView<Appointments> weeklyViewTable;

    @FXML
    private TableView<Appointments> monthlyViewTable;

    @FXML
    private TableView<Reports> monthReportTable;

    @FXML
    private TableView<Reports> contactReportTable;

    @FXML
    private Button addCustomerButton;

    Customer selectedCustomer;

    private String contactName;
    private String customerName;

    /**
     * Initialize method, initializes Main Screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initializes customer table
        ObservableList<Customer> customerList = DBCustomer.getAllCustomers();
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerTable.setItems(customerList);


        // Initializes appointment table
        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        monthlyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        monthlyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        monthlyEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        monthlyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        allAppointmentsTableview.setItems(appointmentsList);


        // Sets Contacts observable list
        ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
        // Fills contact combo box
        contactSelectorBox.setItems(allContacts);

        // Sets Customer observable list
        ObservableList<Customer> allCustomers = DBCustomer.getAllCustomers();
        //Fills customer combo box
        customerIdSelectorBox.setItems(allCustomers);

        // Sets months observable list
       // ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();

        // Fills type in combo box
        // monthSelectorBox.setItems(allAppointments);

        // Sets Appointments observable list

        ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
        // Fills type in combo box
        typeSelectorBox.setItems(allAppointments);


    }


        /** Add customer method.
         * @param event Opens Add Customer screen when add button clicked.
         */

        @FXML
        void OnActionAddCustomer (ActionEvent event) throws IOException {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Add Customer");
            stage.show();

        }

        /**
         * Update customer method.
         * @param event Opens Update Customer screen when update button clicked.
         */

        @FXML
        void OnActionUpdateCustomer (ActionEvent event) throws IOException {

            System.out.println("Update button clicked");
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/UpdateCustomer.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Update Customer");
            stage.show();
        }

        /** Delete customer method. Deletes customer data when delete button clicked. */

        @FXML
        void OnActionDeleteCustomer (ActionEvent event) throws SQLException {
               selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
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
                            DBCustomer.deleteCustomer(selectedCustomer);
                            customerTable.getItems().clear();
                            customerTable.setItems(DBCustomer.getAllCustomers());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        }

        /**
         * Logout customer method.
         * @param event Goes back to log in screen when log out button clicked.
         */

        @FXML
        void OnActionLogoutCustomer (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Add Customer");
                stage.show();
            }
        }


        /**
         * Add appointment method.
         * @param event Opens Add Appointment screen when add button clicked.
         */


        @FXML
        void  OnActionAddAppointment (ActionEvent event) throws IOException {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/AddAppointments.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Add Appointment");
            stage.show();
        }



        /**
         * Update appointment method.
         * @param event Opens Update Appointment screen when update button clicked.
         */

        @FXML
        void OnActionUpdateAppointment (ActionEvent event) throws IOException {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/view/AddAppointments.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.setTitle("Update Appointment");
            stage.show();
        }

        /** Delete appointment method in monthly view. Deletes appointment when delete button clicked. */

        @FXML
        void OnActionDeleteAppointment(ActionEvent event) {

            Appointments selectedAppointment = monthlyViewTable.getSelectionModel().getSelectedItem();
            if (selectedAppointment == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must select an appointment to delete.");
                alert.showAndWait();
            } else if (monthlyViewTable.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this appointment?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    try {
                        DBAppointments.cancelAppointment(selectedAppointment);
                        monthlyViewTable.getItems().clear();
                        monthlyViewTable.setItems(DBAppointments.getAllAppointments());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        // **** FIX ME ****
        /** Delete appointment method in weekly view. Deletes appointment when delete button clicked. */


        /**
         * Logout appointment method.
         * @param event Goes back to log in screen when log out button clicked.
         */

        @FXML
        void onActionLogoutAppointments(ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Add Customer");
                stage.show();
            }
        }



        /**
         * Logout report method.
         * @param event Goes back to log in screen when log out button clicked.
         */

        @FXML
        void OnActionLogoutReports (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
                stage.setScene(new Scene((Parent) scene));
                stage.setTitle("Add Customer");
                stage.show();
            }
        }

    public void onSelectionCustomer(ActionEvent event) {
        reportsByCustomerLabel.setText(String.valueOf(DBCustomer.totalCustomers(customerIdSelectorBox.getValue().getCustomerName())));
    }

    public void OnSelectionContact(ActionEvent event) {
       // Contacts selectedContact = (Contacts) contactSelectorBox.getValue();
        reportsByContactLabel.setText(String.valueOf(DBContacts.totalContacts(contactSelectorBox.getValue().getContactName())));
    }

    public void OnSelectionMonth(ActionEvent event) {

    }

    public void OnDeleteAppointment(ActionEvent event) {
            Appointments selectedAppointment = monthlyViewTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete " +
                    selectedAppointment.getTitle() + " from database?");
             Optional<ButtonType> result = alert.showAndWait();
             DBAppointments.cancelAppointment(selectedAppointment);

             DBAppointments.getAllAppointments();

    }
}

