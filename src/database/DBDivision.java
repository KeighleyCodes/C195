package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class DBDivision {

    /**
     * Division name from ID method.
     * Creates observable list of all divisions from database.
     */
    // Pulls division list into observable list
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions divisionsObject = new Divisions(divisionId, division, countryId);
                allDivisions.add(divisionsObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allDivisions;
    }

    /**
     * Division name from ID method
     *
     * @param divisionId Provides division name object from the division ID in database.
     */
    public static Divisions divisionNameFromId(int divisionId) {

        Divisions divisionsObject = null;

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = " + divisionId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                divisionsObject = new Divisions(divisionId, division, countryId);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return divisionsObject;
    }

    /**
     * Division from country method
     *
     * @param divisionId Provides division name object from the division ID in database.
     */
    public static int divisionFromCountry(int divisionId) {

        int countryId = 0;

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = " + divisionId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countryId = rs.getInt("Country_ID");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countryId;
    }

    // ********************************** FIX ME **************************************************
    /**method.
     @param selectedCountryId Provides observable list of divisions from corresponding country in database.
     @return divisionsByCountryList. */

    public static ObservableList<Divisions> countryFromDivision(int selectedCountryId) {

        ObservableList<Divisions> divisionsByCountryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + selectedCountryId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions divisionsObject = new Divisions(divisionId, divisionName, countryId);
                divisionsByCountryList.add(divisionsObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return divisionsByCountryList;
    }
}