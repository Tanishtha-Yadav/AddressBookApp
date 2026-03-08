package com.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private List<ContactPerson> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public List<ContactPerson> getContacts() {
        return contacts;
    }

    public boolean addContact(ContactPerson contact) {
        boolean exists = contacts.stream()
                .anyMatch(c -> c.equals(contact));
        if (exists) {
            System.out.println("Duplicate contact! " + contact.getFirstName() + " " + contact.getLastName());
            return false;
        }
        contacts.add(contact);
        System.out.println("Contact added successfully: " + contact.getFirstName() + " " + contact.getLastName());
        return true;
    }

    // UC8: Search by city
    public List<ContactPerson> searchByCity(String city) {
        return contacts.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .toList();
    }

    // UC8: Search by state
    public List<ContactPerson> searchByState(String state) {
        return contacts.stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .toList();
    }
}