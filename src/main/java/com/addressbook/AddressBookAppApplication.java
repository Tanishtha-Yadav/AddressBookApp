package com.addressbook;

public class AddressBookAppApplication {

    public static void main(String[] args) {
        AddressBookService service = new AddressBookService();

        // Example JSON Server URL (replace with actual running JSON server)
        String jsonUrl = "http://localhost:3000/contacts";

        service.fetchContactsFromJsonServer(jsonUrl);

        System.out.println("--- Contacts in Memory ---");
        service.getContacts().forEach(c -> 
            System.out.println(c.getFirstName() + " " + c.getLastName() + ", " + c.getEmail()));
    }
}