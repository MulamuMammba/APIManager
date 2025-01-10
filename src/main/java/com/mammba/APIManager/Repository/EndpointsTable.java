package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;

import java.sql.*;
import java.util.List;

public class EndpointsTable {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db";


    public static void insertEndpoint(Endpoints endpoint) {
        String insertSQL = "INSERT INTO Endpoints ( apiId, name, url, method) VALUES ( ?, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, endpoint.getApiId());
            pstmt.setString(2, endpoint.getName());
            pstmt.setString(3, endpoint.getUrl());
            pstmt.setString(4, endpoint.getMethod());
            pstmt.executeUpdate();

            System.out.println("Account inserted successfully : " + endpoint.getId() + " " + endpoint.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
    }
    public static void removeEndpointById(String id) {
        String deleteSQL = "DELETE FROM Endpoints WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, Integer.parseInt(id));
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Endpoint with ID " + id + " removed successfully.");
            } else {
                System.out.println("No endpoint found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error removing endpoint by ID: " + e.getMessage());
        }
    }

    public static void removeEndpointByApiId(String apiId) {
        String deleteSQL = "DELETE FROM Endpoints WHERE apiId = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, apiId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Endpoint with ApiId " + apiId + " removed successfully.");
            } else {
                System.out.println("No endpoint found with ApiId " + apiId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error removing endpoint by ApiId: " + e.getMessage());
        }
    }


    public static Endpoints getEndpointById(String id) {
        return null;
    }

    public static List<Endpoints> getEndpointByUserEmail(String email) {
    }
}
