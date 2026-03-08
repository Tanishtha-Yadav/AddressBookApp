package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        Map<String, AddressBook> addressBooks = new HashMap<>();

        AddressBook familyBook = new AddressBook();
        familyBook.addContact(new ContactPerson("Alice", "Smith", "12 Main St", "CityA", "StateX", "12345", "9999999999", "alice@example.com"));
        familyBook.addContact(new ContactPerson("Bob", "Jones", "34 Oak St", "CityB", "StateY", "67890", "8888888888", "bob@example.com"));
        addressBooks.put("Family", familyBook);

        AddressBook friendsBook = new AddressBook();
        friendsBook.addContact(new ContactPerson("Charlie", "Brown", "56 Pine St", "CityA", "StateZ", "11111", "7777777777", "charlie@example.com"));
        friendsBook.addContact(new ContactPerson("David", "Lee", "78 Maple St", "CityC", "StateX", "22222", "6666666666", "david@example.com"));
        addressBooks.put("Friends", friendsBook);

        // Count by City
        Map<String, Long> countByCity = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getCity, Collectors.counting()));

        // Count by State
        Map<String, Long> countByState = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getState, Collectors.counting()));

        System.out.println("--- Count of Persons by City ---");
        countByCity.forEach((city, count) -> System.out.println(city + ": " + count));

        System.out.println("\n--- Count of Persons by State ---");
        countByState.forEach((state, count) -> System.out.println(state + ": " + count));
    }
}