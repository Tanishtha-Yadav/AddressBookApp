package com.addressbook;

import java.util.Arrays;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String username = "sa";
        String password = "";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);

        List<ContactPerson> contacts = Arrays.asList(
            new ContactPerson("Bruce","Wayne","Wayne Manor","Gotham","StateG","11111","1234567890","bruce@example.com"),
            new ContactPerson("Peter","Parker","Queens St","NYC","StateN","22222","2345678901","peter@example.com"),
            new ContactPerson("Tony","Stark","Stark Tower","NYC","StateN","33333","3456789012","tony@example.com")
        );

        System.out.println("--- Adding Multiple Contacts using Threads ---");
        service.addMultipleContactsThreaded(contacts, "HeroesBook");
    }
}