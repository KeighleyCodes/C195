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
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/** Add Customer Controller.
    Opens add customer screen. Here the customer is able to add a new customer to the database. */

public class AddCustomerController implements Initializable {

    public TextField idTextField;
    public TextField postalCodeTextField;
    public TextField nameTextField;
    public TextArea addressTextField;
    public TextField phoneTextField;
    public ComboBox<Countries> countryComboBox;
    public ComboBox<Divisions> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;

    Stage stage;
    Parent scene;

    // SETS COUNTRIES OBSERVABLE LIST
        ObservableList<Countries> allCountries = DBCountries.getAllCountries();

    // SETS DIVISION OBSERVABLE LIST
        ObservableList<Divisions> allDivisions = DBDivision.getAllDivisions();

    /** Initialize method.
        @param url
        @param resourceBundle Initializes screen, fills combo boxes with appropriate values. */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(allCountries);

    }


    /** Save customer method.
        @param event Adds new customer to the database and returns to the Main Screen. */

    @FXML
    void OnActionSaveCustomer(ActionEvent event) throws IOException, SQLException {
        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneTextField.getText();
        int divisionId = divisionComboBox.getValue().getDivisionId();
        DBCustomer.insertCustomer(customerName, address, postalCode, phone, divisionId);

        this.stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        this.scene = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("/view/MainScreen.fxml")));
        this.stage.setScene(new Scene(this.scene));
        this.stage.setTitle("Main Screen");
        this.stage.show();

    }

    /** On Select Country method
        @param event Filters division combo box based on selection from country combo box. */

    public void onSelectCountry(ActionEvent event) {

        divisionComboBox.setItems(DBDivision.countryFromDivision(countryComboBox.getValue().getCountryId()));

    }


    /** Cancel method.
        @param event Closes window and returns to Main Screen. */

    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
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

}
