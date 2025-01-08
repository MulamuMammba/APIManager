package com.mammba.APIManager.Repository;

import java.sql.*;

public class Database {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db";

    public static void createAccountTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "email TEXT PRIMARY KEY, "
                + "password TEXT, "
                + "name TEXT, "
                + "surname TEXT, "
                + "profession TEXT, "
                + "phoneNum TEXT)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    public static void createApiTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Api ("
                + "id TEXT PRIMARY KEY CHECK(length(id) = 8), "
                + "userEmail TEXT, "
                + "name TEXT, "
                + "description TEXT, "
                + "baseUrl TEXT)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Api table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    public static void createEndpointsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Endpoints ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "apiId TEXT, "
                + "name TEXT, "
                + "url TEXT, "
                + "method TEXT)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Endpoint table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

}