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
    void testEditExistingContact() {
        ContactPerson c1 = new ContactPerson("Alice", "Smith", "12 Main St", "City", "State", "12345", "9999999999", "alice@example.com");
        addressBook.addContact(c1);

        ContactPerson newDetails = new ContactPerson("Alice", "Johnson", "34 Oak St", "City", "State", "67890", "8888888888", "alice.j@example.com");
        boolean result = addressBook.editContactByFirstName("Alice", newDetails);

        assertTrue(result, "Contact should be updated");
        List<ContactPerson> contacts = addressBook.getContacts();
        assertEquals("Johnson", contacts.get(0).getLastName());
        assertEquals("34 Oak St", contacts.get(0).getAddress());
    }

    @Test
    void testEditNonExistingContact() {
        ContactPerson newDetails = new ContactPerson("Bob", "Lee", "56 Pine St", "City", "State", "11223", "7777777777", "bob@example.com");
        boolean result = addressBook.editContactByFirstName("Bob", newDetails);
        assertFalse(result, "Contact should not be found");
    }
}