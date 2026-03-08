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

    // UC3: Edit existing contact by first name
    public boolean editContactByFirstName(String firstName, ContactPerson newDetails) {
        for (ContactPerson c : contacts) {
            if (c.getFirstName().equalsIgnoreCase(firstName)) {
                c.setFirstName(newDetails.getFirstName());
                c.setLastName(newDetails.getLastName());
                c.setAddress(newDetails.getAddress());
                c.setCity(newDetails.getCity());
                c.setState(newDetails.getState());
                c.setZip(newDetails.getZip());
                c.setPhoneNumber(newDetails.getPhoneNumber());
                c.setEmail(newDetails.getEmail());
                System.out.println("Contact updated successfully for: " + firstName);
                return true;
            }
        }
        return false; // contact not found
    }
}