package com.addressbook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private AddressBook addressBook;
    private final String jsonFile = "testAddressBook.json";

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook();
        addressBook.addContact(new ContactPerson("Alice", "Smith", "Addr1", "CityA", "StateX", "12345", "1111111111", "alice@example.com"));
        addressBook.addContact(new ContactPerson("Bob", "Jones", "Addr2", "CityB", "StateY", "67890", "2222222222", "bob@example.com"));
    }

    @AfterEach
    void cleanUp() {
        File file = new File(jsonFile);
        if (file.exists()) file.delete();
    }

    @Test
    void testWriteAndReadJSON() {
        addressBook.writeToJSON(jsonFile);
        AddressBook newBook = new AddressBook();
        newBook.readFromJSON(jsonFile);

        assertEquals(addressBook.getContacts().size(), newBook.getContacts().size());
        assertEquals("Alice", newBook.getContacts().get(0).getFirstName());
        assertEquals("Bob", newBook.getContacts().get(1).getFirstName());
    }
}