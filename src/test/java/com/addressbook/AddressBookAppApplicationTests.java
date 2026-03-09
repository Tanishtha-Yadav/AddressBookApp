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
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "Tanishtha@687";

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
                    "email VARCHAR(50))");

            stmt.execute("INSERT INTO contact_person (first_name,last_name,address,city,state,zip,phone_number,email) " +
                    "VALUES ('Alice','Smith','Addr1','CityA','StateX','12345','1111111111','alice@example.com')");
        }
    }

    @Test
    void testUpdateContactAndSync() {
        ContactPerson updated = new ContactPerson("Alice", "Smith", "NewAddr", "NewCity", "NewState", "99999", "9999999999", "alice@new.com");
        boolean updatedDB = service.updateContact("Alice", updated);
        assertTrue(updatedDB);

        ContactPerson fromDB = service.getContactByFirstName("Alice");
        assertNotNull(fromDB);
        assertEquals(updated.getAddress(), fromDB.getAddress());
        assertEquals(updated.getCity(), fromDB.getCity());
        assertEquals(updated.getState(), fromDB.getState());
        assertEquals(updated.getZip(), fromDB.getZip());
        assertEquals(updated.getPhoneNumber(), fromDB.getPhoneNumber());
        assertEquals(updated.getEmail(), fromDB.getEmail());

        // equals() check for firstName + lastName
        assertEquals(updated, fromDB);
    }
}