package com.addressbook;

import java.time.LocalDate;
import java.util.List;

public class AddressBookAppApplication {

    public static void main(String[] args) {
    	String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "Tanishtha@687";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);

        System.out.println("--- UC18: Retrieve Contacts by Period ---");

        // Example: Add contacts with specific dates
        service.addContactWithDate(new ContactPerson("Alice","Smith","Addr1","CityA","StateX","12345","1111111111","alice@example.com"), LocalDate.of(2026,3,1));
        service.addContactWithDate(new ContactPerson("Bob","Jones","Addr2","CityB","StateY","67890","2222222222","bob@example.com"), LocalDate.of(2026,3,5));
        service.addContactWithDate(new ContactPerson("Charlie","Brown","Addr3","CityC","StateZ","54321","3333333333","charlie@example.com"), LocalDate.of(2026,3,10));

        // Retrieve contacts added from 2026-03-02 to 2026-03-09
        LocalDate start = LocalDate.of(2026,3,2);
        LocalDate end = LocalDate.of(2026,3,9);

        List<ContactPerson> contacts = service.getContactsByPeriod(start, end);
        System.out.println("Contacts added between " + start + " and " + end + ":");
        contacts.forEach(System.out::println);
    }
}