package org.example;

import java.sql.*;

public class CountryDAO {

    public void create(String name, String code, int continentId) {
        String sql = "INSERT INTO countries(name, code, continent_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, code);
            stmt.setInt(3, continentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Country insert error: " + e.getMessage());
        }
    }

    public String findByName(String name) {
        String sql = "SELECT * FROM countries WHERE name = ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Country: " + rs.getString("name") +
                        ", Code: " + rs.getString("code") +
                        ", Continent ID: " + rs.getInt("continent_id");
            }
        } catch (SQLException e) {
            System.err.println("Country find error: " + e.getMessage());
        }
        return null;
    }
}
