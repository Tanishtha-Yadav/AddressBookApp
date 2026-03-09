package com.addressbook;

import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {

        // Example DB: H2 in-memory
        String jdbcURL = "jdbc:h2:~/addressbookdb";
        String username = "sa";
        String password = "";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);
        List<ContactPerson> contacts = service.getAllContacts();

        System.out.println("--- UC16: Retrieve All Contacts from DB ---");
        contacts.forEach(System.out::println);
    }
}