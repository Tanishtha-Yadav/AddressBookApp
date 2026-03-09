package com.addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class AddressBookService {

    private final List<ContactPerson> contacts = new ArrayList<>();

    public List<ContactPerson> getContacts() {
        return contacts;
    }

    // Fetch contacts from JSON Server
    public void fetchContactsFromJsonServer(String jsonUrl) {
        try {
            String response = given()
                    .get(jsonUrl)
                    .then()
                    .statusCode(200)
                    .extract().asString();

            Type contactListType = new TypeToken<List<ContactPerson>>() {}.getType();
            List<ContactPerson> retrievedContacts = new Gson().fromJson(response, contactListType);

            contacts.clear();
            contacts.addAll(retrievedContacts);
            System.out.println("Fetched " + contacts.size() + " contacts from JSON Server.");
        } catch (Exception e) {
            System.out.println("Error fetching contacts: " + e.getMessage());
        }
    }

    // Add multiple contacts to JSON Server (UC23)
    public void addMultipleContactsToJsonServer(String jsonUrl, List<ContactPerson> newContacts) {
        newContacts.forEach(contact -> {
            try {
                given()
                        .contentType(ContentType.JSON)
                        .body(contact)
                        .post(jsonUrl)
                        .then()
                        .statusCode(201);

                contacts.add(contact); // Sync memory
                System.out.println("Added " + contact.getFirstName() + " to JSON Server and memory.");
            } catch (Exception e) {
                System.out.println("Failed to add " + contact.getFirstName() + ": " + e.getMessage());
            }
        });
    }

    // UC24: Update a contact in JSON Server and sync memory
    public void updateContactInJsonServer(String jsonUrl, int contactId, ContactPerson updatedContact) {
        try {
            given()
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .put(jsonUrl + "/" + contactId)
                .then()
                .statusCode(200);

            // Update in-memory
            contacts.replaceAll(contact -> contact.getId() == contactId ? updatedContact : contact);
            System.out.println("Updated contact with ID " + contactId + " in JSON Server and memory.");
        } catch (Exception e) {
            System.out.println("Failed to update contact: " + e.getMessage());
        }
    }
}