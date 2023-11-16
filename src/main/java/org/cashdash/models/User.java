package org.cashdash.models;

import org.cashdash.interfaces.Auth;
import org.cashdash.utils.Bcrypt;
import org.cashdash.utils.Database;

import java.sql.ResultSet;

public class User implements Auth {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private boolean admin;
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public User(String fullname, String username, String password) {
        this(username, password);
        this.fullname = fullname;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void login() {
        try {
            ResultSet result = Database.executeQuery("SELECT * FROM users WHERE username = ?", this.username);

            if (!result.next())
                throw new Exception("User not found");

            String hashedPassword = result.getString("password");

            if (!Bcrypt.checkPassword(this.password, hashedPassword))
                throw new Exception("Invalid password");

            System.out.println("TODO: User logged in successfully");

            this.id       = result.getInt("id");
            this.fullname = result.getString("fullname");
            this.admin    = result.getBoolean("is_admin");
            this.loggedIn = true;

        } catch (Exception exception) {
            System.out.println("User::login() error: " + exception.getMessage());
        }
    }

    public void logout() {
        this.loggedIn = false;
    }

    public boolean register() {
        try {
            String hashedPassword = Bcrypt.hashPassword(this.password);

            int affectedRows = Database.executeUpdate("INSERT INTO users (fullname, username, password) VALUES (?, ?, ?)",
                    this.fullname, this.username, hashedPassword);

            if (affectedRows > 0) {
                System.out.println("User registered successfully");
                return true;
            } else {
                throw new Exception("User couldn't be registered successfully");
            }

        } catch (Exception exception) {
            System.out.println("User::register() error: " + exception.getMessage());
        }

        return false;
    }
}
