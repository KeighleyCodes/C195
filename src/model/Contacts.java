package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Contacts class - provides contact objects. */
public class Contacts {

    private int contactId;
    private String contactName;
    private String email;

    // Constructor
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    // Getters and setters
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Observable list
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    // toString method for the combo box to return
    public String toString() {
        return contactName;
    }

    /** Add contact method.
     @param newContact Method for adding contacts. */
    public static void addContact(Contacts newContact) {
        allContacts.add(newContact);
    }

    /** Deleted contact method.
     @param selectedContact Deletes selected contact. */
    public static boolean deleteContact (Customer selectedContact) {

        if (allContacts.contains(selectedContact)) {
            allContacts.remove(selectedContact);
            return true;
        }
        else {
            return false;
        }
    }



}
