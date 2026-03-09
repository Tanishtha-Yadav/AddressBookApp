package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        // Add contact
        List<ContactPerson> contacts = new ArrayList<>();
        contacts.add(new ContactPerson(1, "Alice", "Smith", "123 Street", "CityA", "StateA", "12345", "9999999999", "alice@example.com"));
        service.addMultipleContactsToJsonServer(jsonServerUrl, contacts);

        // Update contact
        service.updateContactInJsonServer(jsonServerUrl, 1,
                new ContactPerson(1, "Alice", "Smith", "New Street", "CityA", "StateA", "12345", "9999999999", "alice@example.com"));

        // Delete contact
        service.deleteContactFromJsonServer(jsonServerUrl, 1);

        System.out.println("Contacts in memory:");
        service.getContacts().forEach(System.out::println);
    }
}