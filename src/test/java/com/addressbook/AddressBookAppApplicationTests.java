package com.addressbook;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookAppApplicationTests {

    @Test
    void testDeleteContactFromJsonServer() {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        // Add contact first
        ContactPerson contact = new ContactPerson(1, "Test", "User", "Old Street", "CityX", "StateX", "00000", "1111111111", "test@example.com");
        service.addMultipleContactsToJsonServer(jsonServerUrl, List.of(contact));

        // Delete contact
        service.deleteContactFromJsonServer(jsonServerUrl, 1);

        // Verify in-memory list is empty
        assertEquals(0, service.getContacts().size());
    }
}