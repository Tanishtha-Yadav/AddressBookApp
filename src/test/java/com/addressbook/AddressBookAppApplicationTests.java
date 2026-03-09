package com.addressbook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AddressBookAppApplicationTests {

    @Test
    void testAddMultipleContacts() {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        List<ContactPerson> contacts = new ArrayList<>();
        contacts.add(new ContactPerson("Test", "User", "Street", "City", "State", "00000", "1111111111", "test@example.com"));

        service.addMultipleContactsToJsonServer(jsonServerUrl, contacts);

        assert service.getContacts().size() == contacts.size();
    }
}