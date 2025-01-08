package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;

import java.sql.*;

public class EndpointsTable {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db";


    public static void insertEndpoint(Endpoints endpoint) {
        String insertSQL = "INSERT INTO Endpoints ( apiId, name, url, method) VALUES ( ?, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

//            pstmt.setInt(1, endpoint.getId());
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

    public static Endpoints getEndpointById(String id) {
        String selectSQL = "SELECT * FROM Endpoints WHERE id = ? LIMIT 1";
        Endpoints endpoint = new Endpoints("0", null, null, null, null);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String apiId = rs.getString("apiId");
                String name = rs.getString("name");
                String url = rs.getString("url");
                String method = rs.getString("method");

                endpoint.AddEndpoint(id,apiId,name,url,method);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving api: " + e.getMessage());
        }

        return endpoint;
    }
}
