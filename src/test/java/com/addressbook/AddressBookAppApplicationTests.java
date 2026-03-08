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
    void testAddContact() {
        Contact c = new Contact(
                "John", "Doe", "123 Street", "City", "State",
                "11111", "9999999999", "john@example.com"
        );

        addressBook.addContact(c);

        List<Contact> contacts = addressBook.getContacts();
        assertEquals(1, contacts.size());
        assertEquals("John", contacts.get(0).getFirstName());
        assertEquals("Doe", contacts.get(0).getLastName());
    }
}