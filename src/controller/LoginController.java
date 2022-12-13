package controller;

import database.DBAppointments;
import database.DBUser;
import javafx.animation.AnimationTimer;
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
import main.Main;
import model.Appointments;
import model.Users;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


/** Login controller - initializes the login form UI where the program begins for the user. */
public class LoginController implements Initializable {

    public Label usernameText;
    public Label passwordText;
    public Label localTimeLabel;
    public Label promptTextLabel;
    Stage stage;
    Parent scene;

    @FXML
    public TextField usernameTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button enterButton;

    @FXML
    public Button exitButton;

    @FXML
    public Label zoneIdLabel;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Displays local time zone
        zoneIdLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));

        // Displays local date and time
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                localTimeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();

        // Checks if locale is French
        Locale fr = new Locale("fr");

        if(Locale.getDefault().equals(fr)) {

            // Translates login page to French
            usernameText.setText(Main.rb.getString("userNamePrompt"));
            passwordText.setText(Main.rb.getString("passwordPrompt"));
            promptTextLabel.setText(Main.rb.getString("promptText"));
            enterButton.setText(Main.rb.getString("enterButton"));
            exitButton.setText(Main.rb.getString("exitButton"));

        }

    }

    // Boolean to check if login credentials are valid
    private boolean loginIsValid() {
        ObservableList<Users> allUsers = DBUser.getAllUsers();
        for (Users user : allUsers) {
            if (user.getUsername().equals(usernameTextField.getText()) && user.getPassword().equals(passwordTextField.getText())) {
                Users.currentUser = user;
                return true;
            }
        }
        return false;
    }


    /** Enter method. Enters Main Screen when enter button clicked. */
    @FXML
    void OnActionEnter(ActionEvent event) throws IOException {

        // Checks is login credentials are valid
        if (loginIsValid()) {

            ObservableList<Appointments> upcomingAppointments = FXCollections.observableArrayList();
            LocalDate localDateNow = LocalDate.now();
            LocalTime localTimeNow = LocalTime.now();
            LocalTime localTimePlus15 = LocalTime.now().plusMinutes(15);
            int currentUserUserId = Users.currentUser.getUserId();

            try {
                ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
                for(Appointments appointments : allAppointments) {
                    System.out.println(appointments.getAppointmentDay());
                    System.out.println(appointments.getStartTime());
                    System.out.println(appointments.getUserId());
                    System.out.println(currentUserUserId);
                    System.out.println(localTimeNow);
                    System.out.println(localTimePlus15);
                    System.out.println(localDateNow);
                    if((appointments.getAppointmentDay().equals(localDateNow)) && (appointments.getStartTime().isAfter(localTimeNow)) &&
                            (appointments.getStartTime().isBefore(localTimePlus15)) && (appointments.getUserId() == currentUserUserId)) {
                        upcomingAppointments.add(appointments);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(Main.rb.getString("upcomingAppointmentTitle"));
                        alert.setContentText((Main.rb.getString("appointmentId") + appointments.getAppointmentId() + "\n" +
                                (Main.rb.getString( "appointmentDate")) + appointments.getAppointmentDay() + "\n" +
                                (Main.rb.getString("appointmentTime")) + appointments.getStartTime()));
                        alert.showAndWait();
                    }
                }
                if(upcomingAppointments.size()<1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(Main.rb.getString("upcomingAppointmentTitle"));
                    alert.setContentText(Main.rb.getString("noAppointmentsMessage"));
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainScreen.fxml"));
            loader.load();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Screen");
            stage.show();

        }

        else {
            enterButton.setText(Main.rb.getString("enterButton"));
            enterButton.setText(Main.rb.getString("enterButton"));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Main.rb.getString("error"));
            alert.setContentText(Main.rb.getString("errorMessage"));
            alert.showAndWait();
        }

    }


    /** Exit method. Displays confirmation box and exits program when exit button clicked. */
    @FXML
    void OnActionExit() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, Main.rb.getString("confirmationMessage"));
        alert.setTitle(Main.rb.getString("confirmation"));
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

}
