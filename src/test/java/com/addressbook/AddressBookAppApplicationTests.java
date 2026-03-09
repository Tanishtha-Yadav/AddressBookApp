package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

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

            service.addContactWithDate(new ContactPerson("Alice","Smith","Addr1","CityA","StateX","12345","1111111111","alice@example.com"), LocalDate.of(2026,3,1));
            service.addContactWithDate(new ContactPerson("Bob","Jones","Addr2","CityB","StateY","67890","2222222222","bob@example.com"), LocalDate.of(2026,3,5));
            service.addContactWithDate(new ContactPerson("Charlie","Brown","Addr3","CityA","StateX","54321","3333333333","charlie@example.com"), LocalDate.of(2026,3,10));
        }
    }

    @Test
    void testGetCountByCity() {
        Map<String,Integer> cityCounts = service.getCountByCity();
        assertEquals(2, cityCounts.get("CityA"));
        assertEquals(1, cityCounts.get("CityB"));
    }

    @Test
    void testGetCountByState() {
        Map<String,Integer> stateCounts = service.getCountByState();
        assertEquals(2, stateCounts.get("StateX"));
        assertEquals(1, stateCounts.get("StateY"));
    }
}