package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    // Pulls user list into observable list
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
}
