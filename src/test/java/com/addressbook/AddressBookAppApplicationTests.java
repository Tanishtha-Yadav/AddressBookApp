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
    void testAddMultipleContacts() {
        ContactPerson c1 = new ContactPerson("Alice", "Smith", "12 Main St", "City", "State", "12345", "9999999999", "alice@example.com");
        ContactPerson c2 = new ContactPerson("Bob", "Jones", "34 Oak St", "City", "State", "67890", "8888888888", "bob@example.com");

        addressBook.addContact(c1);
        addressBook.addContact(c2);

        List<ContactPerson> contacts = addressBook.getContacts();
        assertEquals(2, contacts.size());
        assertEquals("Alice", contacts.get(0).getFirstName());
        assertEquals("Bob", contacts.get(1).getFirstName());
    }
}