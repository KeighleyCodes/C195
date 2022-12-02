package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivision {
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
}