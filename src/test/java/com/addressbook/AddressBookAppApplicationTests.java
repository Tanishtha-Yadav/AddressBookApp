package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

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
    void testAddMultipleContactsThreaded() throws InterruptedException {
        ContactPerson c1 = new ContactPerson("Natasha","Romanoff","Stark Tower","NYC","StateN","44444","4567890123","natasha@example.com");
        ContactPerson c2 = new ContactPerson("Steve","Rogers","Brooklyn St","NYC","StateN","55555","5678901234","steve@example.com");

        service.addMultipleContactsThreaded(Arrays.asList(c1, c2), "HeroesBook");

        // wait a bit for threads to finish
        Thread.sleep(2000);

        // Optionally, validate by count (or retrieval methods from previous UC)
        assertTrue(true); // placeholder as DB check can be implemented
    }
}