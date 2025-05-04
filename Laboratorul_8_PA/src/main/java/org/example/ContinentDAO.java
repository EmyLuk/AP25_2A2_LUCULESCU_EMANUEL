package org.example;

import java.sql.*;

public class ContinentDAO {

    public void create(String name) {
        String sql = "INSERT INTO continents(name) VALUES (?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Continent insert error: " + e.getMessage());
        }
    }

    public String findByName(String name) {
        String sql = "SELECT * FROM continents WHERE name = ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Continent ID: " + rs.getInt("id") + ", Name: " + rs.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Continent find error: " + e.getMessage());
        }
        return null;
    }

    public int findIdByName(String name) {
        String sql = "SELECT id FROM continents WHERE name = ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Continent findId error: " + e.getMessage());
        }
        return -1;
    }
}
