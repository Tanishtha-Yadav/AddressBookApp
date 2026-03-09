package com.addressbook;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String username = "sa";
        String password = "";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);

        // Add sample contacts
        service.addContactWithDate(new ContactPerson("Alice","Smith","Addr1","CityA","StateX","12345","1111111111","alice@example.com"), LocalDate.of(2026,3,1));
        service.addContactWithDate(new ContactPerson("Bob","Jones","Addr2","CityB","StateY","67890","2222222222","bob@example.com"), LocalDate.of(2026,3,5));
        service.addContactWithDate(new ContactPerson("Charlie","Brown","Addr3","CityA","StateX","54321","3333333333","charlie@example.com"), LocalDate.of(2026,3,10));

        Map<String,Integer> cityCounts = service.getCountByCity();
        System.out.println("Contacts count by City:");
        cityCounts.forEach((city,count) -> System.out.println(city + ": " + count));

        Map<String,Integer> stateCounts = service.getCountByState();
        System.out.println("\nContacts count by State:");
        stateCounts.forEach((state,count) -> System.out.println(state + ": " + count));
    }
}