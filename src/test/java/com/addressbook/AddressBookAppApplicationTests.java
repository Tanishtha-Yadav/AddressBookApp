package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

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
                    "email VARCHAR(50)," +
                    "date_added DATE)");

            service.addContactWithDate(new ContactPerson("Alice","Smith","Addr1","CityA","StateX","12345","1111111111","alice@example.com"), LocalDate.of(2026,3,1));
            service.addContactWithDate(new ContactPerson("Bob","Jones","Addr2","CityB","StateY","67890","2222222222","bob@example.com"), LocalDate.of(2026,3,5));
            service.addContactWithDate(new ContactPerson("Charlie","Brown","Addr3","CityC","StateZ","54321","3333333333","charlie@example.com"), LocalDate.of(2026,3,10));
        }
    }

    @Test
    void testGetContactsByPeriod() {
        LocalDate start = LocalDate.of(2026,3,2);
        LocalDate end = LocalDate.of(2026,3,9);

        List<ContactPerson> contacts = service.getContactsByPeriod(start, end);

        assertEquals(1, contacts.size());
        assertEquals("Bob", contacts.get(0).getFirstName());
    }
}