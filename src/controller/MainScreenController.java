package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
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
    public ComboBox contactSelectorBox;
    private Stage stage;
    private Object scene;

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


    /**
     * Initialize method, initializes Main Screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /*
        // Initializes part table
        customerTable.setItems(Customer.getAllCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        customerStateColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        customerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        */


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
        void OnActionDeleteCustomer () {

            System.out.println("Delete button clicked");

            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Customer.deleteCustomer(selectedCustomer);
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
        /*
        @FXML
        void OnActionDeleteAppointment () {

            Appointments selectedAppointment = monthlyViewTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Appointments.deleteAppointment(selectedAppointment);
            }
        }

         */

        // **** FIX ME ****
        /** Delete appointment method in weekly view. Deletes appointment when delete button clicked. */
    /*
    @FXML
    void OnActionDeleteAppointment() {

        Appointments selectedAppointment = weeklyViewTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Appointments.deleteAppointment(selectedAppointment);
        }
    }

     */

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

    }

