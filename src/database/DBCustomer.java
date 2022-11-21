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


    // ----- FOR REPORTS TAB ------------------------------------------------


    // Counts total by customer
    public static int totalCustomers(String contactName) {
        try {
            String sql = "SELECT COUNT(Customer_Name) FROM Customers WHERE Customer_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("COUNT(Customer_Name)");
            }
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
    public static void insertCustomer(int customerId, String customerName, String address, String phone, String postalCode,
                                       int divisionId) throws SQLException {

        // Need to change division ID to division name?
        // Divisions divisionString = DBDivision.getDivisionId();
        try {
            Customer.customerList().clear();
            String sql =  "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setString(2, customerName);
            ps.setString(3, address);
            ps.setString(4, postalCode);
            ps.setString(5, phone);
            ps.setInt(6, divisionId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public static void updateCustomer(int customerId, String customerName, String address, String phone, String postalCode,
                                      int divisionId) throws SQLException {

        // Need to change division ID to division name?
        // Divisions divisionString = DBDivision.getDivisionId();
        try {
           // Customer.customerList().clear();
            String sql =  "UPDATE customers SET (Customer_ID = ?, Customer_Name = ?, Address = ?, " +
                    "Postal_Code = ?, Phone = ?, Division_ID = ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setString(2, customerName);
            ps.setString(3, address);
            ps.setString(4, postalCode);
            ps.setString(5, phone);
            ps.setInt(6, divisionId);

            ps.executeUpdate();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
