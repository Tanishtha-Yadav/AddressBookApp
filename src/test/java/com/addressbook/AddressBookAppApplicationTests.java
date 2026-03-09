package com.addressbook;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookAppApplicationTests {

    @Test
    void testUpdateContactInJsonServer() {
        AddressBookService service = new AddressBookService();
        String jsonServerUrl = "http://localhost:3000/contacts";

        // Add a contact first
        ContactPerson contact = new ContactPerson(1, "Test", "User", "Old Street", "CityX", "StateX", "00000", "1111111111", "test@example.com");
        service.addMultipleContactsToJsonServer(jsonServerUrl, List.of(contact));

        // Update the contact
        ContactPerson updated = new ContactPerson(1, "Test", "User", "New Street", "CityX", "StateX", "00000", "1111111111", "test@example.com");
        service.updateContactInJsonServer(jsonServerUrl, 1, updated);

        // Verify in-memory update
        assertEquals("New Street", service.getContacts().get(0).getAddress());
    }
}