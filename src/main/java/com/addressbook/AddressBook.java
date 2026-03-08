package com.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<ContactPerson> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(ContactPerson contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully: " + contact.getFirstName() + " " + contact.getLastName());
    }

    public List<ContactPerson> getContacts() {
        return contacts;
    }
}