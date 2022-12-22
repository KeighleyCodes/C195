package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Database Countries. Contains SQL code pertaining to the Countries table. */

public class DBCountries {

    /** Countries observable list.
       @return Creates observable list of all countries in database. */

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                Countries countryObject = new Countries(countryId, country);
                allCountries.add(countryObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allCountries;
    }


    /**
     * @param countryId
     * @return
     */
    // Creates country object name from ID
    public static Countries selectedCountryName(int countryId) {

        Countries countryObject = null;
        try {
            String sql = "SELECT * FROM countries WHERE Country_ID = " + countryId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String country = rs.getString("Country");
                countryObject = new Countries(countryId, country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }

        return countryObject;
    }

}
