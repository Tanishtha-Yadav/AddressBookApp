package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private AddressBook addressBook;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook();
    }

    @Test
    void testDeleteExistingContact() {
        ContactPerson c1 = new ContactPerson("Alice", "Smith", "12 Main St", "City", "State", "12345", "9999999999", "alice@example.com");
        addressBook.addContact(c1);

        boolean result = addressBook.deleteContactByFirstName("Alice");
        assertTrue(result, "Contact should be deleted");

        List<ContactPerson> contacts = addressBook.getContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    void testDeleteNonExistingContact() {
        ContactPerson c1 = new ContactPerson("Bob", "Lee", "34 Oak St", "City", "State", "67890", "8888888888", "bob@example.com");
        addressBook.addContact(c1);

        boolean result = addressBook.deleteContactByFirstName("Alice");
        assertFalse(result, "Contact should not be found");

        List<ContactPerson> contacts = addressBook.getContacts();
        assertEquals(1, contacts.size());
    }
}