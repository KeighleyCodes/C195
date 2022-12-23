package model;

/** Users class - provides user objects. */

// CONSTRUCTOR

public class Users {
    private int userId;
    private String username;
    private String password;
    public static Users currentUser;

    public Users(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Users(int userId, String userName) {
        this.userId = userId;
        this.username = userName;
    }

    // GETTERS AND SETTERS

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TO STRING METHOD FOR COMBO BOX TO RETURN

    public String toString() {
        return username;
    }
}