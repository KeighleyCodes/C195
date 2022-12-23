package model;

/** Countries class - provides country objects. */

// CONSTRUCTOR

public class Countries {

    private int countryId;
    private String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country =  country;
    }

    // GETTERS AND SETTERS

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

    // TO STRING METHOD FOR COMBO BOX TO RETURN

    public String toString() {
        return country;
    }

}
