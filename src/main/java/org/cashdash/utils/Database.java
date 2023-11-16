package org.cashdash.utils;

import java.sql.*;

public class Database {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/cashdash";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception exception) {
            System.out.println("Schema::Schema() error: " + exception.getMessage());
        }

        return connection;
    }

    public static ResultSet executeQuery(String... args) {
        ResultSet result = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(args[0]);

            for (int i = 1; i < args.length; i++)
                statement.setString(i, args[i]);

            result = statement.executeQuery();

        } catch (Exception exception) {
            System.out.println("Schema::executeQuery() error: " + exception.getMessage());
        }
        return result;
    }

    public static int executeUpdate(String... args) {
        int affectedRows = 0;
        try {
            PreparedStatement statement = getConnection().prepareStatement(args[0]);

            for (int i = 1; i < args.length; i++)
                statement.setString(i, args[i]);

            affectedRows = statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Schema::executeUpdate() error: " + exception.getMessage());
        }
        return affectedRows;
    }
}