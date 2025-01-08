package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Users;

import java.util.Random;
import java.sql.*;

public class ApiTable {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db";



    public static void insertApi(API api) {
        String insertSQL = "INSERT INTO Api (id, userEmail, name, description, baseUrl) VALUES (?, ?, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, api.getId());
            pstmt.setString(2, api.getUserEmail());
            pstmt.setString(3, api.getName());
            pstmt.setString(4, api.getDescription());
            pstmt.setString(5, api.getBaseUrl());
            pstmt.executeUpdate();

            System.out.println("Account inserted successfully : " + api.getId() + " " + api.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
    }

    public static API getApiById(String id) {
        String selectSQL = "SELECT * FROM Api WHERE id = ? LIMIT 1";
        API api = new API(null, null, null, null, null);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String userEmail = rs.getString("userEmail");
                String description = rs.getString("description");
                String baseUrl = rs.getString("baseUrl");

                api.addAPI(id,userEmail,name,description,baseUrl);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving api: " + e.getMessage());
        }

        return api;
    }
}
