package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        // Fetch existing contacts
        service.fetchContactsFromJsonServer(jsonServerUrl);

        // Add a new contact
        List<ContactPerson> newContacts = new ArrayList<>();
        newContacts.add(new ContactPerson(1, "John", "Doe", "123 Street", "CityA", "StateA", "12345", "9999999999", "john@example.com"));
        service.addMultipleContactsToJsonServer(jsonServerUrl, newContacts);

        // Update the contact
        ContactPerson updated = new ContactPerson(1, "John", "Doe", "456 New Street", "CityA", "StateA", "12345", "9999999999", "john@example.com");
        service.updateContactInJsonServer(jsonServerUrl, 1, updated);

        System.out.println("Contacts in memory:");
        service.getContacts().forEach(System.out::println);
    }
}