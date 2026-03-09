package com.addressbook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressBookService {

    private final String jdbcURL;
    private final String username;
    private final String password;

    public AddressBookService(String jdbcURL, String username, String password) {
        this.jdbcURL = jdbcURL;
        this.username = username;
        this.password = password;
    }

    // UC20: Add contact to DB with transaction
    public boolean addContactTransaction(ContactPerson contact, String bookName) {
        String insertPersonSQL = "INSERT INTO contact_person (first_name,last_name,address,city,state,zip,phone_number,email,date_added) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        String insertBookSQL = "INSERT INTO address_book (book_name, contact_id) VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password)) {
            conn.setAutoCommit(false); // begin transaction

            try (PreparedStatement psPerson = conn.prepareStatement(insertPersonSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psPerson.setString(1, contact.getFirstName());
                psPerson.setString(2, contact.getLastName());
                psPerson.setString(3, contact.getAddress());
                psPerson.setString(4, contact.getCity());
                psPerson.setString(5, contact.getState());
                psPerson.setString(6, contact.getZip());
                psPerson.setString(7, contact.getPhoneNumber());
                psPerson.setString(8, contact.getEmail());
                psPerson.setDate(9, Date.valueOf(java.time.LocalDate.now()));

                int rowsPerson = psPerson.executeUpdate();
                if (rowsPerson == 0) throw new SQLException("Failed to insert contact");

                // Get generated contact ID
                ResultSet generatedKeys = psPerson.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int contactId = generatedKeys.getInt(1);

                    // Insert into address_book table
                    try (PreparedStatement psBook = conn.prepareStatement(insertBookSQL)) {
                        psBook.setString(1, bookName);
                        psBook.setInt(2, contactId);
                        int rowsBook = psBook.executeUpdate();
                        if (rowsBook == 0) throw new SQLException("Failed to insert into address_book");
                    }
                } else {
                    throw new SQLException("Failed to retrieve contact ID");
                }

                conn.commit(); // commit transaction
                return true;

            } catch (SQLException e) {
                conn.rollback(); // rollback on any failure
                System.out.println("Transaction failed: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("DB connection error: " + e.getMessage());
            return false;
        }
    }
}