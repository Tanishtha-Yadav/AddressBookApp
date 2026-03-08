package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private Map<String, AddressBook> addressBooks;

    @BeforeEach
    void setUp() {
        addressBooks = new HashMap<>();
        AddressBook book1 = new AddressBook();
        book1.addContact(new ContactPerson("Alice", "Smith", "12 Main St", "CityA", "StateX", "12345", "9999999999", "alice@example.com"));
        book1.addContact(new ContactPerson("Bob", "Jones", "34 Oak St", "CityB", "StateY", "67890", "8888888888", "bob@example.com"));

        AddressBook book2 = new AddressBook();
        book2.addContact(new ContactPerson("Charlie", "Brown", "56 Pine St", "CityA", "StateZ", "11111", "7777777777", "charlie@example.com"));
        book2.addContact(new ContactPerson("David", "Lee", "78 Maple St", "CityC", "StateX", "22222", "6666666666", "david@example.com"));

        addressBooks.put("Family", book1);
        addressBooks.put("Friends", book2);
    }

    @Test
    void testSearchByCity() {
        List<ContactPerson> results = addressBooks.get("Family").searchByCity("CityA");
        assertEquals(1, results.size());
        assertEquals("Alice", results.get(0).getFirstName());

        results = addressBooks.get("Friends").searchByCity("CityA");
        assertEquals(1, results.size());
        assertEquals("Charlie", results.get(0).getFirstName());
    }

    @Test
    void testSearchByState() {
        List<ContactPerson> results = addressBooks.get("Family").searchByState("StateX");
        assertEquals(1, results.size());
        assertEquals("Alice", results.get(0).getFirstName());

        results = addressBooks.get("Friends").searchByState("StateX");
        assertEquals(1, results.size());
        assertEquals("David", results.get(0).getFirstName());
    }
}