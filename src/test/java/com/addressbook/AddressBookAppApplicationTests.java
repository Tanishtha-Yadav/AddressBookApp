package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private Map<String, AddressBook> addressBooks;

    @BeforeEach
    void setUp() {
        addressBooks = new HashMap<>();
    }

    @Test
    void testAddMultipleAddressBooks() {
        AddressBook book1 = new AddressBook();
        AddressBook book2 = new AddressBook();

        ContactPerson c1 = new ContactPerson("Alice", "Smith", "12 Main St", "City", "State", "12345", "9999999999", "alice@example.com");
        ContactPerson c2 = new ContactPerson("Bob", "Jones", "34 Oak St", "City", "State", "67890", "8888888888", "bob@example.com");

        book1.addContact(c1);
        book2.addContact(c2);

        addressBooks.put("Family", book1);
        addressBooks.put("Friends", book2);

        assertEquals(2, addressBooks.size());
        assertEquals("Alice", addressBooks.get("Family").getContacts().get(0).getFirstName());
        assertEquals("Bob", addressBooks.get("Friends").getContacts().get(0).getFirstName());
    }

    @Test
    void testDuplicateAddressBookName() {
        AddressBook book1 = new AddressBook();
        addressBooks.put("Family", book1);

        boolean duplicate = addressBooks.containsKey("Family");
        assertTrue(duplicate, "Duplicate AddressBook name detected");
    }
}