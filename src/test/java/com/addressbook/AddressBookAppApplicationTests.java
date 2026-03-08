package com.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
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
    void testPersonsByCity() {
        Map<String, List<ContactPerson>> cityMap = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getCity));

        assertEquals(3, cityMap.size());
        assertEquals(2, cityMap.get("CityA").size());
        assertEquals("Alice", cityMap.get("CityA").get(0).getFirstName());
        assertEquals("Charlie", cityMap.get("CityA").get(1).getFirstName());
    }

    @Test
    void testPersonsByState() {
        Map<String, List<ContactPerson>> stateMap = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getState));

        assertEquals(3, stateMap.size());
        assertEquals(2, stateMap.get("StateX").size());
        assertEquals("Alice", stateMap.get("StateX").get(0).getFirstName());
        assertEquals("David", stateMap.get("StateX").get(1).getFirstName());
    }
}