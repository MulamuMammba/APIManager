package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Services.Security.Generator;

import java.util.List;
import java.sql.*;

public class ApiTable {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db";

    public static void insertApi(API api) {
        String insertSQL = "INSERT INTO Api (id, userEmail, name, description, baseUrl) VALUES (?, ?, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {


            pstmt.setString(1, Generator.generateApiId());
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

    public static void RemoveApi(String apiId) {
        String deleteSQL = "DELETE FROM Api WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, Integer.parseInt(apiId));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("API removed successfully: " + apiId);
            } else {
                System.out.println("No API found with id: " + apiId);
            }
        } catch (SQLException e) {
            System.out.println("Error removing API: " + e.getMessage());
        }
    }
    public static void removeUserByApi(String userEmail) {
        String deleteSQL = "DELETE FROM Api WHERE userEmail = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, userEmail);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("API entries removed successfully for userEmail: " + userEmail);
            } else {
                System.out.println("No API entries found for userEmail: " + userEmail);
            }
        } catch (SQLException e) {
            System.out.println("Error removing API entries: " + e.getMessage());
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

    public static List<API> getApiByUser(String userEmail) {
        String selectSQL = "SELECT * FROM Api WHERE userEmail = ?";
        List<API> api = new java.util.ArrayList<>(List.of());
        api.add( new API(null, null, null, null, null));

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            api.clear();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String baseUrl = rs.getString("baseUrl");

                api.add(new API(id,userEmail,name,description,baseUrl));


            }
        } catch (SQLException e) {
            System.out.println("Error retrieving api: " + e.getMessage());
        }

        return api;
    }
}
