package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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


    public static void insertAppointment(int appointmentId, String title, String description, String location,
            int contactId, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId)
            throws SQLException {

        try {
            Appointments.allAppointments().clear();
            String sql =  "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, " +
                    "Start, End, Customer_ID, User_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, appointmentId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setInt(12, contactId);
            ps.setString(5, type);
            ps.setTimestamp(6, Timestamp.valueOf(startTime)); // Need date, start time and end time ?
            ps.setTimestamp(7, Timestamp.valueOf(endTime)); //
            ps.setInt(13, customerId);
            ps.setInt(14, userId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // ----- FOR REPORTS TAB ------------------------------------------------


/*
    // Counts total by appointment
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

