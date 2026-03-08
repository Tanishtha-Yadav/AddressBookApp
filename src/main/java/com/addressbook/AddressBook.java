package com.addressbook;

import java.util.ArrayList;
import java.util.Comparator;
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
        boolean exists = contacts.stream().anyMatch(c -> c.equals(contact));
        if (exists) {
            System.out.println("Duplicate contact! " + contact.getFirstName() + " " + contact.getLastName());
            return false;
        }
        contacts.add(contact);
        System.out.println("Contact added successfully: " + contact.getFirstName() + " " + contact.getLastName());
        return true;
    }

    // UC12: Sort by FirstName then LastName (reused from UC11)
    public List<ContactPerson> getSortedContacts() {
        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getFirstName)
                        .thenComparing(ContactPerson::getLastName))
                .collect(Collectors.toList());
    }

    // UC12: Sort by City
    public List<ContactPerson> getSortedByCity() {
        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getCity))
                .collect(Collectors.toList());
    }

    // UC12: Sort by State
    public List<ContactPerson> getSortedByState() {
        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getState))
                .collect(Collectors.toList());
    }

    // UC12: Sort by Zip
    public List<ContactPerson> getSortedByZip() {
        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getZip))
                .collect(Collectors.toList());
    }
}