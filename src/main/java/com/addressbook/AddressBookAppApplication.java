package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        // Add a contact first
        List<ContactPerson> contactsToAdd = new ArrayList<>();
        ContactPerson contact = new ContactPerson(1, "Alice", "Smith", "123 Street", "CityA", "StateA", "12345", "9999999999", "alice@example.com");
        contactsToAdd.add(contact);
        service.addMultipleContactsToJsonServer(jsonServerUrl, contactsToAdd);

        // Update the contact
        ContactPerson updatedContact = new ContactPerson(1, "Alice", "Smith", "456 New Street", "CityA", "StateA", "12345", "9999999999", "alice@example.com");
        service.updateContactInJsonServer(jsonServerUrl, 1, updatedContact);

        // Display contacts in memory
        System.out.println("Contacts in memory:");
        service.getContacts().forEach(System.out::println);
    }
}