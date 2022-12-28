package controller;

import database.DBCountries;
import database.DBCustomer;
import database.DBDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Countries;
import model.Customer;
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import static database.DBCountries.selectedCountryName;
import static database.DBDivision.selectedDivisionName;

/** Update Customer Controller.
    Opens update appointment screen. Here the customer is able to update a customer in the database. */

public class UpdateCustomerController implements Initializable {

    public TextField idTextField;
    public TextField postalCodeTextField;
    public TextField nameTextField;
    public TextArea addressTextField;
    public TextField phoneTextField;
    public ComboBox <Countries> countryComboBox;
    public ComboBox <Divisions> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;

    private int customerId;

    Stage stage;
    Parent scene;


    // CREATES OBSERVABLE LISTS

    ObservableList<Countries> allCountries = DBCountries.getAllCountries();
    ObservableList<Divisions> allDivisions = DBDivision.getAllDivisions();

    /** Initialize method.
     @param url
     @param resourceBundle Initializes screen, fills combo boxes with appropriate values. */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(allCountries);
        divisionComboBox.setItems(allDivisions);

    }


    /** On Select Country method.
       @param event Filters division based on selection from country combo box. */

    public void OnActionSelectCountry(ActionEvent event) {

        divisionComboBox.setItems(DBDivision.countryFromDivision(countryComboBox.getValue().getCountryId()));

    }

    public Boolean validAppointments() {

        // LOGICAL ERROR CHECKS
        if(nameTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty() ||
                addressTextField.getText().isEmpty() ||
                postalCodeTextField.getText().isEmpty() ||
                countryComboBox.getValue() == null ||
                divisionComboBox.getValue() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields");
            alert.setContentText("Please ensure no fields are empty.");
            alert.showAndWait();
            return false;

        }

        return true;
    }


    /** Save customer method.
     @param event
     @throws IOException Saves modified customer information into database. */

    public void OnActionSaveUpdate(ActionEvent event) throws IOException, SQLException {

        boolean appointmentsAreValid = validAppointments();

        if(appointmentsAreValid) {
            String customerName = nameTextField.getText();
            String address = addressTextField.getText();
            String phone = phoneTextField.getText();
            String postalCode = postalCodeTextField.getText();
            int divisionId = divisionComboBox.getValue().getDivisionId();
            String country = countryComboBox.getValue().getCountry();
            DBCustomer.updateCustomer(customerName, address, postalCode, phone, divisionId, customerId);

            this.stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
            this.stage.setScene(new Scene(this.scene));
            this.stage.setTitle("Main screen");
            this.stage.show();
        }

    }

    /** Send Customer method.
        @param customer Sends selected customer information to be modified. */

   public void sendCustomer(Customer customer) {

        customerId = customer.getCustomerId();
        idTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(String.valueOf(customer.getCustomerName()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        postalCodeTextField.setText(String.valueOf(customer.getPostalCode()));
        divisionComboBox.setValue(selectedDivisionName(customer.getDivisionId()));
        int custId = DBDivision.divisionFromCountry(customer.getDivisionId());
        countryComboBox.setValue(selectedCountryName(custId));

    }

    /** Cancel method.
     @param event Closes window and returns to Main Screen. */

    @FXML
    void OnActionCancelCustomer(ActionEvent event) throws IOException {

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

