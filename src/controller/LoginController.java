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
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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



    public LoginController() throws IOException {
    }

    /** Lambda expression to track login activity and print it to file. This reduces code repetition */

    public interface LoginActivity {

       String activity();

    }
    LoginActivity loginActivity = () -> "login_activity.txt";



    /** Initialize method.
        @param url
        @param rb Allows the user to log in using username and password. User's local time and zone are displayed.
        Translates page to French if determined necessary by user's Zone ID. */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // DISPLAYS LOCAL TIME ZONE
        zoneIdLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));

        // DISPLAYS LOCAL DATE AND TIME
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                localTimeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();

        // CHECKS IF LOCALE IS FRENCH
        Locale fr = new Locale("fr");

        if(Locale.getDefault().equals(fr)) {

            // TRANSLATES LOGIN SCREEN TO FRENCH
            usernameText.setText(Main.rb.getString("userNamePrompt"));
            passwordText.setText(Main.rb.getString("passwordPrompt"));
            promptTextLabel.setText(Main.rb.getString("promptText"));
            enterButton.setText(Main.rb.getString("enterButton"));
            exitButton.setText(Main.rb.getString("exitButton"));

        }
    }


    /** Log-in validation method.
     * @return Checks if username and password matches those of the database. */

    // BOOLEAN TO CHECK IF LOGIN CREDENTIALS ARE VALID
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


    /** Successful login method.
        @throws IOException Prints successful log-in message to file. */

    private void successfulLogin() throws IOException {

        FileWriter fileWriter = new FileWriter(loginActivity.activity(), true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("STATUS: Successful\n" + "USER: " + usernameTextField.getText() + "\n" +
                "DATE/TIME: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
        printWriter.close();

    }


    /** Unsuccessful login method. Prints unsuccessful log-in message to file. */

    private void unsuccessfulLogin() throws IOException {

        FileWriter fileWriter = new FileWriter(loginActivity.activity(), true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("STATUS: Not Successful\n" + "USER: " + usernameTextField.getText() + "\n" +
                "DATE/TIME: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
        printWriter.close();

    }


    /** Enter method.
        @param event
        @throws IOException Checks if user login credentials are valid. Loops through appointments in database
        and checks if current user has an appointment within the next 15 minutes. An alert pops up in either
        case, to inform user no appointments found or to inform the user of the appointment ID, title,\
        date and time. All text and alerts translated to French if required by Zone ID. */

    @FXML
    void OnActionEnter(ActionEvent event) throws IOException {

        // CHECKS IF LOGIN CREDENTIALS ARE VALID
        if (loginIsValid()) {

            successfulLogin();

            // CHECKS IF USER HAS APPOINTMENT IN THE NEXT 15 MINUTES, ALERTS IN EITHER CASE
            ObservableList<Appointments> upcomingAppointments = FXCollections.observableArrayList();
            LocalDate localDateNow = LocalDate.now();
            LocalTime localTimeNow = LocalTime.now();
            LocalTime localTimePlus15 = LocalTime.now().plusMinutes(15);
            int currentUserUserId = Users.currentUser.getUserId();

            try {
                ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
                for(Appointments appointments : allAppointments) {
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

            unsuccessfulLogin();

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
