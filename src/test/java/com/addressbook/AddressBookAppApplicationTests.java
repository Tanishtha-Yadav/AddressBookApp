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
        addressBook.addContact(new ContactPerson("Charlie", "Brown", "Address1", "CityA", "StateX", "11111", "1111111111", "charlie@example.com"));
        addressBook.addContact(new ContactPerson("Alice", "Smith", "Address2", "CityB", "StateY", "22222", "2222222222", "alice@example.com"));
        addressBook.addContact(new ContactPerson("Bob", "Jones", "Address3", "CityC", "StateZ", "33333", "3333333333", "bob@example.com"));
    }

    @Test
    void testSortedContacts() {
        List<ContactPerson> sorted = addressBook.getSortedContacts();
        assertEquals("Alice", sorted.get(0).getFirstName());
        assertEquals("Bob", sorted.get(1).getFirstName());
        assertEquals("Charlie", sorted.get(2).getFirstName());
    }
}