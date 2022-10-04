package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Customer class creates customer objects. */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private int divisionId;

    // Constructor
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, LocalDateTime createDate,
                    String createdBy, Timestamp lastUpdate, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.divisionId = divisionId;
    }

   // public static ObservableList<Customer> getAllCustomers= FXCollections.observableArrayList();

    /** Get all parts method.
     @return the parts observable list. */
   /* public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    */


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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Observable list
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /** Add part method.
     @param newCustomer Method for adding parts. */
    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    /** Deleted customer method.
     @param selectedCustomer Deletes selected part. */
    public static boolean deleteCustomer (Customer selectedCustomer) {

        if (allCustomers.contains(selectedCustomer)) {
            allCustomers.remove(selectedCustomer);
            return true;
        }
        else {
            return false;
        }
    }

}
