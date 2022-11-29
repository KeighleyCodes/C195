package controller;

import database.DBAppointments;
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

public class AddCustomerController implements Initializable {

    public TextField idTextField;
    public TextField postalCodeTextField;
    public TextField nameTextField;
    public TextArea addressTextField;
    public TextField phoneTextField;
    public ComboBox countryComboBox;
    public ComboBox<Divisions> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;


    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    Stage stage;
    Parent scene;


    // Sets Countries observable list
        ObservableList<Countries> allCountries = DBCountries.getAllCountries();

    // Sets division observable list
        ObservableList<Divisions> allDivisions = DBDivision.getAllDivisions();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // NEED TO FILTER DIVISIONS BASED ON COUNTRIES
        countryComboBox.setItems(allCountries);
        divisionComboBox.setItems(allDivisions);

    }


    // ******* FIX ME! *******
    /** Save customer method.
     @param event Saves modified part info and returns to Main Screen.
     */

    @FXML
    void OnActionSaveCustomer(ActionEvent event) throws IOException, SQLException {
        customerName = nameTextField.getText();
        address = addressTextField.getText();
        postalCode = postalCodeTextField.getText();
        phone = phoneTextField.getText();
        divisionId = divisionComboBox.getValue().getDivisionId();
        DBCustomer.insertCustomer(customerName, address, postalCode, phone, divisionId);

        this.stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
            this.stage.setTitle("Main Inventory");
            this.stage.show();
        }
    }

}
