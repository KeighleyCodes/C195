package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Database Contacts. Contains SQL code pertaining to the Contacts table. */

public class DBContacts {

    /** Contact observable list.
      @return Creates observable list of all contacts from database. */

    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts contactObject = new Contacts(contactId, contactName, email);
                allContacts.add(contactObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allContacts;
    }

    /** Contact name from ID method.
      @param contactId
      @return Creates contact object from contact ID. */

    public static Contacts contactNameFromId(int contactId) {

        Contacts contactObject = null;
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = " + contactId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String contactName = rs.getString("Contact_Name");
                contactObject = new Contacts(contactId, contactName);
               // System.out.println(contactObject.getContactName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactObject;
    }

}
