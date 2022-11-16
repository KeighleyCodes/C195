package model;

public class Divisions {
    private int divisionId;
    private String divisionName;
    private int countryId;

    // Constructor
    public Divisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = division;
        this.countryId = countryId;
    }

    // Getters and setters
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

    // toString method for the combo box to return
    public String toString() {
        return divisionName;
    }
}
