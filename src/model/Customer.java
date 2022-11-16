package model;


import database.DBCustomer;
import javafx.collections.ObservableList;

/** Customer class creates customer objects. */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
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

    // toString method for the combo box to return
    public String toString() {
        return customerName;
    }




    /** Get all customers method.
     @return the customers observable list. */

    public static ObservableList<Customer> customerList() {
        return DBCustomer.getAllCustomers();
    }


    /** Add customer method.
     @param newCustomer Method for adding customers. */


    public static void addCustomer(Customer newCustomer) {

    }




//***** FIX ME ******

    /** Deleted customer method.
     *
     * @param customer
     * @return
     * @throws SQLException
     */

/*
    public static int deleteCustomer(Customer customer) throws SQLException {

        PreparedStatement ps = DBCustomer.prepareStatement("DELETE FROM customers WHERE Customer_ID=?");

        ps.setInt(1, customer.getCustomerId());

        ps.executeUpdate();

        return 0;

    }

 */
    }





