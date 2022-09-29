package model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Contacts class - provides contact objects. */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private int customerId;
    private int userId;
    private int contactId;

    // Constructor
    public Appointments(int appointmentId, String title, String description, String location, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    // Observable list
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();


    /** Deleted appointment method.
     @param selectedAppointment Deletes selected appointment. */
    public static boolean deleteAppointment (Appointments selectedAppointment) {

        if (allAppointments.contains(selectedAppointment)) {
            allAppointments.remove(selectedAppointment);
            return true;
        }
        else {
            return false;
        }
    }
    
}
