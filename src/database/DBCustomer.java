package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomer {
    // Pulls customer list into observable list
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


/*
    public static int insertCustomer (String customerName, String address, String postalCode, String phone) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setNString(2, this.nameTextField.getText());
        ps.setNString(3, Customer.getAddress());
        ps.setNString(4, Customer.getPostalCode());
        ps.setNString(5, Customer.getPhone());
        ps.executeUpdate();
        return 0;
    }

 */

    // Counts total by customer
    public static int totalCustomers() {
        try {
            String sql = "SELECT COUNT(Customer_Name) FROM Customers WHERE Customer_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }



public static int deleteCustomer(Customer customer) throws SQLException {
    String sql = "SELECT * FROM customers";
    PreparedStatement ps = DBConnection.getConnection().prepareStatement("DELETE FROM customers WHERE Customer_ID=?");
        ps.setInt(1, customer.getCustomerId());
        ps.executeUpdate();

        return 0;

    }
    // ****** FIX ME!! ******
    public static void insertCustomer(String customerName, String address, String phone, String postalCode,
                                       int divisionId) throws SQLException {
       // String divisions = "";
        // Divisions divisionString = DBDivision.getDivisionId();
        try {
            Customer.customerList().clear();
            String sql =  "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);


            ps.setString(2, customerName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, postalCode);
            ps.setInt(6, divisionId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



/*

    public static String deleteCustomer(Customer customerId) {
        String sql = "DELETE CUSTOMER FROM CUSTOMERS WHERE Customer_ID = ?";

    }

 */


}
