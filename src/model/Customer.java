package model;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Customer class creates customer objects. */
public class Customer {
    private int customerId;
    private String customerName;
    private String phone;
    private String address;
    private String postalCode;
    private int divisionId;

    // Constructor
    public Customer(int customerId, String customerName, String phone, String address, String postalCode, int divisionId) {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
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


   // ObservableList<Customer> allCustomers = DBQuery.getAllCustomers();



    /** Get all customers method.
     @return the customers observable list. */

    public static ObservableList<Customer> getAllCustomers() {
        return DBQuery.getAllCustomers();
    }


    /** Add customer method.
     @param newCustomer Method for adding customers. */

    /*
    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

     */



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
