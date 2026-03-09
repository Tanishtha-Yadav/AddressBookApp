package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private AddressBookService service;

    @BeforeEach
    void setUp() throws Exception {
        String jdbcURL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String username = "sa";
        String password = "";

        service = new AddressBookService(jdbcURL, username, password);

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS contact_person (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "first_name VARCHAR(50)," +
                    "last_name VARCHAR(50)," +
                    "address VARCHAR(100)," +
                    "city VARCHAR(50)," +
                    "state VARCHAR(50)," +
                    "zip VARCHAR(20)," +
                    "phone_number VARCHAR(20)," +
                    "email VARCHAR(50)," +
                    "date_added DATE)");

            stmt.execute("CREATE TABLE IF NOT EXISTS address_book (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "book_name VARCHAR(50)," +
                    "contact_id INT," +
                    "FOREIGN KEY (contact_id) REFERENCES contact_person(id))");
        }
    }

    @Test
    void testAddContactTransaction() {
        ContactPerson contact = new ContactPerson("Clark","Kent","Metropolis St","CityC","StateC","88888","5555555555","clark@example.com");
        boolean success = service.addContactTransaction(contact, "HeroesBook");

        assertTrue(success);
    }
}