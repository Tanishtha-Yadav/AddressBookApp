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
        this.username = "root";
        this.password ="Tanishtha@687";
    }

    // UC16: Retrieve all entries from DB
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
}