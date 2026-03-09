package com.addressbook;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AddressBookService {

    private final String jdbcURL;
    private final String username;
    private final String password;

    public AddressBookService(String jdbcURL, String username, String password) {
        this.jdbcURL = jdbcURL;
        this.username = username;
        this.password = password;
    }

    // UC19: Count contacts by City
    public Map<String, Integer> getCountByCity() {
        Map<String, Integer> cityCount = new HashMap<>();
        String sql = "SELECT city, COUNT(*) AS total FROM contact_person GROUP BY city";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cityCount.put(rs.getString("city"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.out.println("Error counting contacts by city: " + e.getMessage());
        }

        return cityCount;
    }

    // UC19: Count contacts by State
    public Map<String, Integer> getCountByState() {
        Map<String, Integer> stateCount = new HashMap<>();
        String sql = "SELECT state, COUNT(*) AS total FROM contact_person GROUP BY state";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stateCount.put(rs.getString("state"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.out.println("Error counting contacts by state: " + e.getMessage());
        }

        return stateCount;
    }

    // Helper: Add contact (reuse from previous UCs)
    public boolean addContactWithDate(ContactPerson contact, java.time.LocalDate dateAdded) {
        String sql = "INSERT INTO contact_person (first_name,last_name,address,city,state,zip,phone_number,email,date_added) " +
                     "VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getAddress());
            ps.setString(4, contact.getCity());
            ps.setString(5, contact.getState());
            ps.setString(6, contact.getZip());
            ps.setString(7, contact.getPhoneNumber());
            ps.setString(8, contact.getEmail());
            ps.setDate(9, Date.valueOf(dateAdded));

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding contact: " + e.getMessage());
            return false;
        }
    }
}