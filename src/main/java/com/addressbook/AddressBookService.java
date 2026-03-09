package com.addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.restassured.RestAssured.given;

public class AddressBookService {

    private final List<ContactPerson> contacts = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(5); // 5 threads for async IO

    public List<ContactPerson> getContacts() { return contacts; }

    // Fetch all contacts asynchronously
    public void fetchContactsFromJsonServer(String jsonUrl) {
        executor.submit(() -> {
            try {
                String response = given()
                        .get(jsonUrl)
                        .then()
                        .statusCode(200)
                        .extract().asString();

                Type contactListType = new TypeToken<List<ContactPerson>>() {}.getType();
                List<ContactPerson> retrievedContacts = new Gson().fromJson(response, contactListType);

                synchronized (contacts) {
                    contacts.clear();
                    contacts.addAll(retrievedContacts);
                }

                System.out.println("Fetched " + contacts.size() + " contacts asynchronously from JSON Server.");

            } catch (Exception e) {
                System.out.println("Error fetching contacts: " + e.getMessage());
            }
        });
    }

    // Add multiple contacts asynchronously
    public void addMultipleContactsToJsonServer(String jsonUrl, List<ContactPerson> newContacts) {
        for (ContactPerson contact : newContacts) {
            executor.submit(() -> {
                try {
                    given()
                        .contentType(ContentType.JSON)
                        .body(contact)
                        .post(jsonUrl)
                        .then()
                        .statusCode(201);

                    synchronized (contacts) { contacts.add(contact); }

                    System.out.println("Added " + contact.getFirstName() + " asynchronously to JSON Server and memory.");

                } catch (Exception e) {
                    System.out.println("Failed to add " + contact.getFirstName() + ": " + e.getMessage());
                }
            });
        }
    }

    // Update a contact asynchronously
    public void updateContactInJsonServer(String jsonUrl, int contactId, ContactPerson updatedContact) {
        executor.submit(() -> {
            try {
                given()
                    .contentType(ContentType.JSON)
                    .body(updatedContact)
                    .put(jsonUrl + "/" + contactId)
                    .then()
                    .statusCode(200);

                synchronized (contacts) {
                    for (int i = 0; i < contacts.size(); i++) {
                        if (contacts.get(i).getId() == contactId) {
                            contacts.set(i, updatedContact);
                            break;
                        }
                    }
                }

                System.out.println("Updated contact asynchronously: " + updatedContact.getFirstName());

            } catch (Exception e) {
                System.out.println("Failed to update contact: " + e.getMessage());
            }
        });
    }

    // Delete a contact asynchronously
    public void deleteContactFromJsonServer(String jsonUrl, int contactId) {
        executor.submit(() -> {
            try {
                given()
                    .delete(jsonUrl + "/" + contactId)
                    .then()
                    .statusCode(200);

                synchronized (contacts) {
                    contacts.removeIf(contact -> contact.getId() == contactId);
                }

                System.out.println("Deleted contact asynchronously with ID " + contactId);

            } catch (Exception e) {
                System.out.println("Failed to delete contact: " + e.getMessage());
            }
        });
    }

    // Shutdown executor gracefully
    public void shutdown() {
        executor.shutdown();
    }
}