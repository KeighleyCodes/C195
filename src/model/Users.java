package model;

public class Users {
    private int userId;
    private String username;
    private String password;

    public static Users currentUser;

    // Constructors
    public Users(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Users(int userId, String userName) {
        this.userId = userId;
        this.username = userName;
    }

    // Getters and setters
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

    // toString method for the combo box to return
    public String toString() {
        return username;
    }
}