package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentsController implements Initializable {
    public ComboBox contactComboBox;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField userIdTextField;
    public TextField appointmentIdTextField;
    public DatePicker startTimeDatePicker;
    public DatePicker endTimeDatePicker;
    public Button cancelButton;
    public Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
