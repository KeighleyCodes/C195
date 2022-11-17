package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    // Pulls contact list into observable list
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



    // Counts total by contact
    public static int totalContacts(String contactName) {
        try {
            String sql = "SELECT COUNT(Contact_Name) FROM Contacts WHERE Contact_Name LIKE ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("COUNT(Contact_Name)");
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
