package com.addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    // Write AddressBook to CSV using OpenCSV
    public void writeToCSV(String fileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            for (ContactPerson c : contacts) {
                String[] line = {c.getFirstName(), c.getLastName(), c.getAddress(),
                                 c.getCity(), c.getState(), c.getZip(),
                                 c.getPhoneNumber(), c.getEmail()};
                writer.writeNext(line);
            }
            System.out.println("AddressBook written to CSV: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    // Read AddressBook from CSV using OpenCSV
    public void readFromCSV(String fileName) {
        contacts.clear();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] line;
            try {
				while ((line = reader.readNext()) != null) {
				    if (line.length == 8) {
				        ContactPerson c = new ContactPerson(line[0], line[1], line[2], line[3],
				                                            line[4], line[5], line[6], line[7]);
				        contacts.add(c);
				    }
				}
			} catch (CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("AddressBook read from CSV: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }
}