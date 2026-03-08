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
        addressBook.addContact(new ContactPerson("Charlie", "Brown", "Addr1", "CityB", "StateZ", "33333", "1111111111", "charlie@example.com"));
        addressBook.addContact(new ContactPerson("Alice", "Smith", "Addr2", "CityA", "StateX", "11111", "2222222222", "alice@example.com"));
        addressBook.addContact(new ContactPerson("Bob", "Jones", "Addr3", "CityC", "StateY", "22222", "3333333333", "bob@example.com"));
    }

    @Test
    void testSortedByCity() {
        List<ContactPerson> sorted = addressBook.getSortedByCity();
        assertEquals("CityA", sorted.get(0).getCity());
        assertEquals("CityB", sorted.get(1).getCity());
        assertEquals("CityC", sorted.get(2).getCity());
    }

    @Test
    void testSortedByState() {
        List<ContactPerson> sorted = addressBook.getSortedByState();
        assertEquals("StateX", sorted.get(0).getState());
        assertEquals("StateY", sorted.get(1).getState());
        assertEquals("StateZ", sorted.get(2).getState());
    }

    @Test
    void testSortedByZip() {
        List<ContactPerson> sorted = addressBook.getSortedByZip();
        assertEquals("11111", sorted.get(0).getZip());
        assertEquals("22222", sorted.get(1).getZip());
        assertEquals("33333", sorted.get(2).getZip());
    }
}