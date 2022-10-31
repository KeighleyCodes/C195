package database;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQuery {
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try {
           String sql = "SELECT * FROM customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                int divisionId = rs.getInt("Division_ID");
                Customer customerObject = new Customer(customerId, customerName,phone, address, postalCode, divisionId);
                allCustomers.add(customerObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        };

        return allCustomers;
    }
}
