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



public class UpdateCustomerController implements Initializable {

    public TextField idTextField;
    public TextField postalCodeTextField;
    public TextField nameTextField;
    public TextArea addressTextField;
    public TextField phoneTextField;
    public ComboBox countryComboBox;
    public ComboBox <Divisions> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    private static Customer customer;

    Stage stage;
    Parent scene;

    // FOR TESTING - DELETE LATER
    int uniqueId = 5;

    // Sets Countries observable list
    ObservableList<Countries> allCountries = DBCountries.getAllCountries();

    // Sets division observable list
    ObservableList<Divisions> allDivisions = DBDivision.getAllDivisions();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populates combo boxes with countries and divisions
        countryComboBox.setItems(allCountries);
        divisionComboBox.setItems(allDivisions);

        //sendCustomer();

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
            this.stage.setTitle("Main Inventory");
            this.stage.show();
        }
    }

    public void OnActionSelectCountry(ActionEvent event) {
    }

    public void OnActionSelectDivision(ActionEvent event) {
    }

    public void OnActionSaveUpdate(ActionEvent event) throws IOException, SQLException {

        customerName = nameTextField.getText();
        address = addressTextField.getText();
        phone = phoneTextField.getText();
        postalCode = postalCodeTextField.getText();
        divisionId = divisionComboBox.getVisibleRowCount();
        DBCustomer.insertCustomer(customerId, customerName, address, postalCode, phone, divisionId);

        this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
        this.stage.setScene(new Scene(this.scene));
        this.stage.setTitle("CHANGE ME");
        this.stage.show();
        }

   public void sendCustomer(Customer customer) {
        idTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(String.valueOf(customer.getCustomerName()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        postalCodeTextField.setText(String.valueOf(customer.getPostalCode()));
      //  divisionComboBox.setValue(customer.getDivisionId());

        // query database, pass division ID and ask to return division
    }


}

