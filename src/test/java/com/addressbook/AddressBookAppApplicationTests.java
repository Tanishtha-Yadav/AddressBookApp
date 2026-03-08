package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private Map<String, AddressBook> addressBooks;

    @BeforeEach
    void setUp() {
        addressBooks = new HashMap<>();

        AddressBook familyBook = new AddressBook();
        familyBook.addContact(new ContactPerson("Alice", "Smith", "12 Main St", "CityA", "StateX", "12345", "9999999999", "alice@example.com"));
        familyBook.addContact(new ContactPerson("Bob", "Jones", "34 Oak St", "CityB", "StateY", "67890", "8888888888", "bob@example.com"));
        addressBooks.put("Family", familyBook);

        AddressBook friendsBook = new AddressBook();
        friendsBook.addContact(new ContactPerson("Charlie", "Brown", "56 Pine St", "CityA", "StateZ", "11111", "7777777777", "charlie@example.com"));
        friendsBook.addContact(new ContactPerson("David", "Lee", "78 Maple St", "CityC", "StateX", "22222", "6666666666", "david@example.com"));
        addressBooks.put("Friends", friendsBook);
    }

    @Test
    void testCountByCity() {
        Map<String, Long> countByCity = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getCity, Collectors.counting()));

        assertEquals(3, countByCity.size());
        assertEquals(2L, countByCity.get("CityA"));
        assertEquals(1L, countByCity.get("CityB"));
        assertEquals(1L, countByCity.get("CityC"));
    }

    @Test
    void testCountByState() {
        Map<String, Long> countByState = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getState, Collectors.counting()));

        assertEquals(3, countByState.size());
        assertEquals(2L, countByState.get("StateX"));
        assertEquals(1L, countByState.get("StateY"));
        assertEquals(1L, countByState.get("StateZ"));
    }
}