package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Database User. Contains SQL code pertaining to the Users table. */
public class DBUser {

    /** Get all users method.
        @return Creates observable list of all users from database. */

    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String username = rs.getString("User_name");
                String password = rs.getString("Password");
                Users usersObject = new Users(userId, username, password);
                allUsers.add(usersObject);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;
    }

    /** Selected username method.
        @param userId
        @return Creates an object from the user ID. */

    public static Users selectedUserName(int userId) {

        Users userObject = null;
        try {
            String sql = "SELECT * FROM users WHERE User_ID = " + userId;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("User_Name");
                userObject = new Users(userId, userName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userObject;
    }

}
