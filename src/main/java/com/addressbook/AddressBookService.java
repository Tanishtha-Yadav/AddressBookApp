package com.addressbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

    private final String jdbcURL;
    private final String username;
    private final String password;

    public AddressBookService(String jdbcURL, String username, String password) {
        this.jdbcURL = jdbcURL;
        this.username = username;
        this.password = password;
    }

    // UC18: Retrieve contacts added between startDate and endDate
    public List<ContactPerson> getContactsByPeriod(LocalDate startDate, LocalDate endDate) {
        List<ContactPerson> contacts = new ArrayList<>();
        String sql = "SELECT first_name, last_name, address, city, state, zip, phone_number, email, date_added " +
                     "FROM contact_person WHERE date_added BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactPerson contact = new ContactPerson(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("state"),
                            rs.getString("zip"),
                            rs.getString("phone_number"),
                            rs.getString("email")
                    );
                    contacts.add(contact);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving contacts by period: " + e.getMessage());
        }

        return contacts;
    }

    // Helper: Add a contact with date_added
    public boolean addContactWithDate(ContactPerson contact, LocalDate dateAdded) {
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