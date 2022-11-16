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
                Divisions divisionsObject = new Divisions(divisionId, division,countryId);
                allDivisions.add(divisionsObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allDivisions;
    }

    public static int getDivisionId(String divisionName) {

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(2, divisionName);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

}

