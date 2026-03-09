package com.addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

    public boolean addContact(ContactPerson contact) {
        boolean exists = contacts.stream().anyMatch(c -> c.equals(contact));
        if (exists) {
            System.out.println("Duplicate contact! " + contact.getFirstName() + " " + contact.getLastName());
            return false;
        }
        contacts.add(contact);
        return true;
    }

    // UC15: Write AddressBook to JSON
    public void writeToJSON(String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(contacts, writer);
            System.out.println("AddressBook written to JSON: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    // UC15: Read AddressBook from JSON
    public void readFromJSON(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Type contactListType = new TypeToken<List<ContactPerson>>() {}.getType();
            List<ContactPerson> readContacts = gson.fromJson(reader, contactListType);
            contacts.clear();
            if (readContacts != null) {
                contacts.addAll(readContacts);
            }
            System.out.println("AddressBook read from JSON: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }
}