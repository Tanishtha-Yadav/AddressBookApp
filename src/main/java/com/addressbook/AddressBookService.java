package com.addressbook;

import java.sql.*;
import java.time.LocalDate;
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

    public boolean addContactTransaction(ContactPerson contact, String bookName) {
        String insertPersonSQL = "INSERT INTO contact_person (first_name,last_name,address,city,state,zip,phone_number,email,date_added) VALUES (?,?,?,?,?,?,?,?,?)";
        String insertBookSQL = "INSERT INTO address_book (book_name, contact_id) VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password)) {
            conn.setAutoCommit(false);

            try (PreparedStatement psPerson = conn.prepareStatement(insertPersonSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psPerson.setString(1, contact.getFirstName());
                psPerson.setString(2, contact.getLastName());
                psPerson.setString(3, contact.getAddress());
                psPerson.setString(4, contact.getCity());
                psPerson.setString(5, contact.getState());
                psPerson.setString(6, contact.getZip());
                psPerson.setString(7, contact.getPhoneNumber());
                psPerson.setString(8, contact.getEmail());
                psPerson.setDate(9, Date.valueOf(LocalDate.now()));

                int rowsPerson = psPerson.executeUpdate();
                if (rowsPerson == 0) throw new SQLException("Failed to insert contact");

                ResultSet generatedKeys = psPerson.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int contactId = generatedKeys.getInt(1);
                    try (PreparedStatement psBook = conn.prepareStatement(insertBookSQL)) {
                        psBook.setString(1, bookName);
                        psBook.setInt(2, contactId);
                        int rowsBook = psBook.executeUpdate();
                        if (rowsBook == 0) throw new SQLException("Failed to insert into address_book");
                    }
                } else {
                    throw new SQLException("Failed to retrieve contact ID");
                }

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("DB connection error: " + e.getMessage());
            return false;
        }
    }

    public void addMultipleContactsThreaded(List<ContactPerson> contacts, String bookName) {
        contacts.forEach(contact -> new Thread(() -> {
            boolean success = addContactTransaction(contact, bookName);
            System.out.println("Added " + contact.getFirstName() + ": " + success);
        }).start());
    }
}