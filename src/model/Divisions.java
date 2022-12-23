package model;

/** Divisions class - provides division objects. */

// CONSTRUCTOR

public class Divisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    public Divisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = division;
        this.countryId = countryId;
    }

    // GETTERS AND SETTERS

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    // TO STRING METHOD FOR COMBO BOX TO RETURN

    public String toString() {
        return divisionName;
    }
}
