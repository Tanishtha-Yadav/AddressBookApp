package com.addressbook;

import java.io.*;
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

    // UC13: Write AddressBook to file
    public void writeToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (ContactPerson c : contacts) {
                String line = String.join(",",
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getState(),
                        c.getZip(),
                        c.getPhoneNumber(),
                        c.getEmail());
                bw.write(line);
                bw.newLine();
            }
            System.out.println("AddressBook written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // UC13: Read AddressBook from file
    public void readFromFile(String fileName) {
        contacts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    ContactPerson c = new ContactPerson(
                            data[0], data[1], data[2], data[3],
                            data[4], data[5], data[6], data[7]
                    );
                    contacts.add(c);
                }
            }
            System.out.println("AddressBook read from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}