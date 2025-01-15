package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.Endpoints;

import java.sql.*;
import java.util.ArrayList;
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




    public static List<Endpoints> getEndpointByApiId(String ApiId) {
        String selectSQL = "SELECT * FROM Endpoints WHERE apiId = ?";
        List<Endpoints> endpoint = new ArrayList<>(List.of());

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, ApiId);
            ResultSet rs = pstmt.executeQuery();

            endpoint.clear();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                String method = rs.getString("method");

                endpoint.add(new Endpoints(Integer.toString(id),ApiId,name,url,method));

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving api: " + e.getMessage());
        }
        return endpoint;
    }

    public static Endpoints getEndpointById(String id) {
        String selectSQL = "SELECT * FROM Endpoints WHERE id = ?";
        Endpoints endpoint = new Endpoints(id," ","null",null,null);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String apiId = rs.getString("apiId");
                String name = rs.getString("name");
                String url = rs.getString("url");
                String method = rs.getString("method");

                endpoint.setApiId(apiId);
                endpoint.setName(name);
                endpoint.setUrl(url);
                endpoint.setMethod(method);
                System.out.println(id);
                System.out.println(endpoint);

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving api: " + e.getMessage());
        }
        return endpoint;
    }
}
