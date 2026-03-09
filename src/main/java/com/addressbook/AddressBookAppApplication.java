package com.addressbook;

import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {

        // Example DB: H2 in-memory
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "Tanishtha@687";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);
        List<ContactPerson> contacts = service.getAllContacts();

        System.out.println("--- UC16: Retrieve All Contacts from DB ---");
        contacts.forEach(System.out::println);
    }
}