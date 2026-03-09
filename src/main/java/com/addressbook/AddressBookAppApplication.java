package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        AddressBookService service = new AddressBookService();

        String jsonServerUrl = "http://localhost:3000/contacts"; // Your JSON Server endpoint

        // Example: Fetch existing contacts
        service.fetchContactsFromJsonServer(jsonServerUrl);

        // Example: Add multiple contacts
        List<ContactPerson> newContacts = new ArrayList<>();
        newContacts.add(new ContactPerson("John", "Doe", "123 Street", "CityA", "StateA", "12345", "9999999999", "john@example.com"));
        newContacts.add(new ContactPerson("Jane", "Smith", "456 Avenue", "CityB", "StateB", "67890", "8888888888", "jane@example.com"));

        service.addMultipleContactsToJsonServer(jsonServerUrl, newContacts);

        System.out.println("All contacts in memory:");
        service.getContacts().forEach(System.out::println);
    }
}