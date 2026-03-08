package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        // Sample AddressBooks with contacts
        Map<String, AddressBook> addressBooks = new HashMap<>();

        AddressBook familyBook = new AddressBook();
        familyBook.addContact(new ContactPerson("Alice", "Smith", "12 Main St", "CityA", "StateX", "12345", "9999999999", "alice@example.com"));
        familyBook.addContact(new ContactPerson("Bob", "Jones", "34 Oak St", "CityB", "StateY", "67890", "8888888888", "bob@example.com"));
        addressBooks.put("Family", familyBook);

        AddressBook friendsBook = new AddressBook();
        friendsBook.addContact(new ContactPerson("Charlie", "Brown", "56 Pine St", "CityA", "StateZ", "11111", "7777777777", "charlie@example.com"));
        friendsBook.addContact(new ContactPerson("David", "Lee", "78 Maple St", "CityC", "StateX", "22222", "6666666666", "david@example.com"));
        addressBooks.put("Friends", friendsBook);

        // Build City → Persons map
        Map<String, List<ContactPerson>> cityMap = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getCity));

        // Build State → Persons map
        Map<String, List<ContactPerson>> stateMap = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(ContactPerson::getState));

        Scanner sc = new Scanner(System.in);
        System.out.print("View by City or State? (city/state): ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("city")) {
            System.out.println("\nPersons by City:");
            cityMap.forEach((city, persons) -> {
                System.out.println("City: " + city);
                persons.forEach(p -> System.out.println("  " + p));
            });
        } else if (choice.equals("state")) {
            System.out.println("\nPersons by State:");
            stateMap.forEach((state, persons) -> {
                System.out.println("State: " + state);
                persons.forEach(p -> System.out.println("  " + p));
            });
        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}