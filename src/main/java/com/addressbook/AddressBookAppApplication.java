package com.addressbook;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String username = "sa";
        String password = "";

        AddressBookService service = new AddressBookService(jdbcURL, username, password);

        ContactPerson newContact = new ContactPerson("Diana","Prince","Themyscira St","CityD","StateD","77777","4444444444","diana@example.com");

        boolean success = service.addContactTransaction(newContact, "HeroesBook");
        System.out.println("Transaction success: " + success);
    }
}