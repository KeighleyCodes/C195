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
import static database.DBDivision.divisionNameFromId;


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
    private String divisionName;
    private String country;

    private static Customer customer;

    Stage stage;
    Parent scene;


    // Sets Countries observable list
    ObservableList<Countries> allCountries = DBCountries.getAllCountries();

    // Sets division observable list
    ObservableList<Divisions> allDivisions = DBDivision.getAllDivisions();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populates combo boxes with countries and divisions
        countryComboBox.setItems(allCountries);
        divisionComboBox.setItems(allDivisions);

    }


    /** Close window method.
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

    public void OnActionSelectCountry(ActionEvent event) {
    }

    public void OnActionSelectDivision(ActionEvent event) {
    }

    public void OnActionSaveUpdate(ActionEvent event) throws IOException, SQLException {

        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        String postalCode = postalCodeTextField.getText();
        int divisionId = divisionComboBox.getValue().getDivisionId();
        String country = countryComboBox.getValue().getCountry();
        DBCustomer.updateCustomer(customerName, address, postalCode, phone, divisionId, customerId);

        this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
        this.stage.setScene(new Scene(this.scene));
        this.stage.setTitle("Main screen");
        this.stage.show();

        }

   public void sendCustomer(Customer customer) {

        customerId = customer.getCustomerId();
        idTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(String.valueOf(customer.getCustomerName()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        postalCodeTextField.setText(String.valueOf(customer.getPostalCode()));
        divisionComboBox.setValue(divisionNameFromId(customer.getDivisionId())); // FIX ME
        int cid = DBDivision.divisionFromCountry(customer.getDivisionId());
        countryComboBox.setValue(selectedCountryName(cid)); // FIX ME

        // query database, pass division ID and ask to return division
       // Localdatetime has month, get current month (.now) extract month
    }

}

