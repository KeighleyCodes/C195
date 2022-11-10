package model;

public class Countries {
    private int countryId;
    private String country;

    // Constructor
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country =  country;

    }

    // Getters and setters
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // toString method for the combo box to return
    public String toString() {
        return country;
    }


}
