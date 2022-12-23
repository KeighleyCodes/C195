package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Database Appointments. Contains SQL code pertaining to the Appointments table. */

public class DBAppointments {

    /** Get all appointments method.
        @return Creates observable list of all appointments in database. */

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



    /** Weekly Appointments method.
        @return Creates observable list of appointments by current week. */

    public static ObservableList<Appointments> weeklyAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * from Appointments where Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 6 day);";

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


    /** Insert Appointment method.
        @param contactId
        @param title
        @param description
        @param location
        @param type
        @param start
        @param end
        @param customerId
        @param userId Inserts appointment into database. */

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


    /** Update appointment method.
        @param title
        @param description
        @param location
        @param contactId
        @param type
        @param startTime
        @param endTime
        @param customerId
        @param userId
        @param appointmentId
        @throws SQLException Updates appointment in database. */

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


    /** Delete appointment method.
        @param appointments
        @return Deletes appointment in database. */

    public static int deleteAppointment(Appointments appointments) {
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


    /** Appointments by customer method.
        @param selectedCustomerId
        @return Creates an observable list of appointments from selected customer. */

    public static ObservableList<Appointments> appointmentsByCustomer(int selectedCustomerId) {

        ObservableList<Appointments> appointmentsByCustomerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID = " + selectedCustomerId;
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
                appointmentsByCustomerList.add(appointmentObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsByCustomerList;
    }


    // ---------------------------------- FOR REPORTS TAB ----------------------------------------------


    /** Total customers method.
        @param customerId
        @return Counts total appointments by selected customer. */

    public static int totalCustomers(int customerId) {
        try {
            String sql = "SELECT COUNT(Appointment_ID) FROM Appointments WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("COUNT(Appointment_ID)");
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    /** Appointments by month and type method.
        @param month
        @param type
        @return Counts total of appointments on selected month and type. */

    public static int appointmentsByMonthAndType(String month, String type) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(Appointment_ID) AS count FROM appointments WHERE MONTHNAME(Start) = ? AND Type = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, month);
            ps.setString(2,type);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(count + "test");
        return count;
    }


    /** Appointments by contact observable list.
        @param selectedContactId
        @return Creates observable list based on selected contact. */

    public static ObservableList<Appointments> appointmentsByContact(int selectedContactId) {

        ObservableList<Appointments> appointmentsByContactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE Contact_ID = " + selectedContactId;
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
                appointmentsByContactList.add(appointmentObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsByContactList;
    }

}