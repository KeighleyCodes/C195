package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class DBAppointments {


    // Pulls appointment list into observable list
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("Contact_ID");
                Appointments appointmentObject = new Appointments(appointmentId, title, description, location, contactId,
                        type, startTime, endTime, customerId, userId);
                allAppointments.add(appointmentObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allAppointments;
    }

/*
    // Counts total by contact
    public static int totalAppointments() {
        try {
            String sql = "SELECT COUNT(Start) FROM appointments WHERE  = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

 */

    // Deletes appointment
    public static int cancelAppointment(Appointments appointments) {
        try {
            String sql =  "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, appointments.getAppointmentId());

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

}

