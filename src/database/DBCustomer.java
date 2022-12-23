package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Database Customer. Contains SQL code pertaining to the Customer table. */

public class DBCustomer {

    /** Get all customers method.
        @return Creates observable list of all customers in database. */

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                Customer customerObject = new Customer(customerId, customerName, address, postalCode, phone, divisionId);
                allCustomers.add(customerObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allCustomers;
    }

    /** Insert customer method.
         @param customerName
         @param address
         @param postalCode
         @param phone
         @param divisionId
         @throws SQLException Inserts new customer into the database. */

    public static void insertCustomer(String customerName, String address, String postalCode, String phone,
                                       int divisionId) throws SQLException {

        try {
            Customer.customerList().clear();
            String sql =  "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** Update customer method.
         @param customerName
         @param address
         @param postalCode
         @param phone
         @param divisionId
         @param customerId
         @throws SQLException Saved modified customer information into the database. */

    public static void updateCustomer(String customerName, String address, String postalCode, String phone,
                                      int divisionId, int customerId) throws SQLException {

        try {
           Customer.customerList().clear();
            String sql =  "UPDATE customers SET Customer_Name = ?, Address = ?, " +
                    "Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /** Delete customer method.
        @param customer
        @return
        @throws SQLException Deletes selected customer from the database. */

    public static int deleteCustomer(Customer customer) throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("DELETE FROM customers WHERE Customer_ID=?");
        ps.setInt(1, customer.getCustomerId());
        ps.executeUpdate();

        return 0;

    }

    /** Selected customer name method.
        @param customerId
        @return Creates customer object from selected customer ID. */

    public static Customer selectedCustomerName(int customerId) {

        Customer customerObject = null;
        try {
            String sql = "SELECT * FROM customers WHERE Customer_ID = " + customerId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String customerName = rs.getString("Customer_Name");
                customerObject = new Customer(customerId, customerName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerObject;
    }


}
