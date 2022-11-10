package model;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Customer class creates customer objects. */
public class Customer {
    private int customerId;
    private static String customerName;
    private static String address;
    private static String postalCode;
    private static String phone;
    private int divisionId;

    // Constructor
    public Customer(int customerId, String customerName, String address, String postalCode,  String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionId = divisionId;
    }


    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    // toString method for the combo box to return
    public String toString() {

        return (Integer.toString(customerId));
    }


    /** Get all customers method.
     @return the customers observable list. */

    public static ObservableList<Customer> getAllCustomers() {
        return DBQuery.getAllCustomers();
    }


    /** Add customer method.
     @param newCustomer Method for adding customers. */


    public static void addCustomer(Customer newCustomer) {

    }



    /** Deleted customer method.
     @param selectedCustomer Deletes selected customer. */

    public static boolean deleteCustomer (Customer selectedCustomer) {

        if (DBQuery.getAllCustomers().contains(selectedCustomer)) {
            DBQuery.getAllCustomers().remove(selectedCustomer);
            return true;
        }
        else {
            return false;
        }
    }


}
