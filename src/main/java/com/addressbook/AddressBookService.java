package com.addressbook;

import java.sql.*;
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

    public List<ContactPerson> getAllContacts() {
        List<ContactPerson> contacts = new ArrayList<>();
        String sql = "SELECT first_name, last_name, address, city, state, zip, phone_number, email FROM contact_person";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

        } catch (SQLException e) {
            System.out.println("Error retrieving contacts: " + e.getMessage());
        }
        return contacts;
    }

    // UC17: Update Contact in DB and ensure in-memory sync
    public boolean updateContact(String firstName, ContactPerson updatedContact) {
        String sql = "UPDATE contact_person SET address=?, city=?, state=?, zip=?, phone_number=?, email=? " +
                     "WHERE first_name=?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedContact.getAddress());
            ps.setString(2, updatedContact.getCity());
            ps.setString(3, updatedContact.getState());
            ps.setString(4, updatedContact.getZip());
            ps.setString(5, updatedContact.getPhoneNumber());
            ps.setString(6, updatedContact.getEmail());
            ps.setString(7, firstName);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating contact: " + e.getMessage());
            return false;
        }
    }

    // Retrieve specific contact for sync check
    public ContactPerson getContactByFirstName(String firstName) {
        String sql = "SELECT first_name, last_name, address, city, state, zip, phone_number, email " +
                     "FROM contact_person WHERE first_name=?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ContactPerson(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("state"),
                            rs.getString("zip"),
                            rs.getString("phone_number"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving contact: " + e.getMessage());
        }
        return null;
    }
}