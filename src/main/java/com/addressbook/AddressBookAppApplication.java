package com.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        Map<String, AddressBook> addressBooks = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC8: Search Person in City or State Across Multiple AddressBooks ---");

        // Example setup: adding two AddressBooks with sample contacts
        AddressBook familyBook = new AddressBook();
        familyBook.addContact(new ContactPerson("Alice", "Smith", "12 Main St", "CityA", "StateX", "12345", "9999999999", "alice@example.com"));
        familyBook.addContact(new ContactPerson("Bob", "Jones", "34 Oak St", "CityB", "StateY", "67890", "8888888888", "bob@example.com"));
        addressBooks.put("Family", familyBook);

        AddressBook friendsBook = new AddressBook();
        friendsBook.addContact(new ContactPerson("Charlie", "Brown", "56 Pine St", "CityA", "StateZ", "11111", "7777777777", "charlie@example.com"));
        friendsBook.addContact(new ContactPerson("David", "Lee", "78 Maple St", "CityC", "StateX", "22222", "6666666666", "david@example.com"));
        addressBooks.put("Friends", friendsBook);

        System.out.print("Search by City or State? (city/state): ");
        String choice = sc.nextLine().trim().toLowerCase();
        System.out.print("Enter name of " + choice + ": ");
        String location = sc.nextLine().trim();

        System.out.println("\nSearch Results:");
        addressBooks.forEach((bookName, book) -> {
            List<ContactPerson> results;
            if (choice.equals("city")) {
                results = book.searchByCity(location);
            } else {
                results = book.searchByState(location);
            }

            if (!results.isEmpty()) {
                System.out.println("AddressBook: " + bookName);
                results.forEach(c -> System.out.println("  " + c));
            }
        });

        sc.close();
    }
}