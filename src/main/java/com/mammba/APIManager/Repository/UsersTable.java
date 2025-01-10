package com.mammba.APIManager.Repository;

import com.mammba.APIManager.Model.Users;

import java.sql.*;

public class UsersTable {
    private static final String DATABASE_URL = "jdbc:sqlite:database.db";

    public static void createUser(Users user) {
        String insertSQL = "INSERT INTO Users (email, password, name,surname,profession,phoneNum) VALUES (?, ?, ?,?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getSurname());
            pstmt.setString(5, user.getProfession());
            pstmt.setString(6, user.getPhoneNum());
            pstmt.executeUpdate();

            System.out.println("Account inserted successfully with email: " + user.getEmail());
        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
    }

    public static void deleteUser(String email) {
        String deleteSQL = "DELETE FROM Users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, email);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully with email: " + email);
            } else {
                System.out.println("No account found with the email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

            public static Users getUserByEmail(String email) {
        String selectSQL = "SELECT * FROM Users WHERE email = ? LIMIT 1";
        Users user = new Users(null, null, null, null, null, null);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String surname = rs.getString("surname");
                String profession = rs.getString("profession");
                String phoneNum = rs.getString("phoneNum");

                user.addUsers(email, password, name, surname, profession, phoneNum);


            }
        } catch (SQLException e) {
            System.out.println("Error retrieving account: " + e.getMessage());
        }

        return user;
    }

}
