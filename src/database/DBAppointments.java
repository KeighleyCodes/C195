package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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
                LocalDate appointmentDay = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                LocalTime startTime = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
                LocalTime endTime = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("Contact_ID");
                Appointments appointmentObject = new Appointments(appointmentId, title, description, location, contactId,
                        type, appointmentDay, startTime, endTime, customerId, userId);
                allAppointments.add(appointmentObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allAppointments;
    }

    public static void insertAppointment(int contactId, String title, String description, String location, String type, LocalDateTime start,
                                         LocalDateTime end, int customerId, int userId) {
        try {
            Appointments.allAppointments().clear();
            String sql = "INSERT INTO appointments (Contact_ID, Title, Description, Location, Type, " +
                    "Start, End, Customer_ID, User_ID) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, contactId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setString(5, type);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setInt(8, customerId);
            ps.setInt(9, userId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updateAppointment(String title, String description, String location,
                                         int contactId, String type, LocalDateTime startTime, LocalDateTime endTime,
                                         int customerId, int userId, int appointmentId) throws SQLException {

        try {
            Appointments.allAppointments().clear();
            String sql = "UPDATE appointments SET  Title=?, Description=?, Location=?, Contact_ID=?, Type=?, " +
                    "Start=?, End=?, Customer_ID=?, User_ID=? WHERE Appointment_ID =?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setInt(4, contactId);
            ps.setString(5, type);
            ps.setTimestamp(6, Timestamp.valueOf(startTime));
            ps.setTimestamp(7, Timestamp.valueOf(endTime));
            ps.setInt(8, customerId);
            ps.setInt(9, userId);
            ps.setInt(10, appointmentId);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // Deletes appointment
    public static int cancelAppointment(Appointments appointments) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, appointments.getAppointmentId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }


    // ----- FOR REPORTS TAB ------------------------------------------------

    // Counts total by appointment
    public static int totalAppointments() {
        try {
            String sql = "SELECT COUNT(Start) FROM appointments WHERE  = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}