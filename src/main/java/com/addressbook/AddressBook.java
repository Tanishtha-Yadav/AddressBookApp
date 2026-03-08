package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<ContactPerson> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public List<ContactPerson> getContacts() {
        return contacts;
    }

    // Add contact with duplicate check
    public boolean addContact(ContactPerson contact) {
        boolean exists = contacts.stream()
                .anyMatch(c -> c.equals(contact)); // Uses equals override

        if (exists) {
            System.out.println("Duplicate contact! " + contact.getFirstName() + " " + contact.getLastName());
            return false;
        }

        contacts.add(contact);
        System.out.println("Contact added successfully: " + contact.getFirstName() + " " + contact.getLastName());
        return true;
    }
}