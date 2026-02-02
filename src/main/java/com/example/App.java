package com.example;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/jdbc_demo";
        String user = "postgres";
        String password = "postgres123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to PostgreSQL successfully!");

            // INSERT
            String insertSQL = "INSERT INTO students (full_name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Georges Hajj");
                pstmt.setString(2, "georges.hajj@example.com");
                pstmt.executeUpdate();
            }

            // SELECT
            String selectSQL = "SELECT id, full_name, email FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("full_name") + " | " +
                                    rs.getString("email")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
