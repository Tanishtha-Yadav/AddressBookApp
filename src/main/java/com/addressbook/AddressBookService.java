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

    public List<ContactPerson> getContacts() { return contacts; }

    // Add multiple contacts (UC23)
    public void addMultipleContactsToJsonServer(String jsonUrl, List<ContactPerson> newContacts) {
        for (ContactPerson contact : newContacts) {
            try {
                given()
                    .contentType(ContentType.JSON)
                    .body(contact)
                    .post(jsonUrl)
                    .then()
                    .statusCode(201);
                contacts.add(contact);
            } catch (Exception e) {
                System.out.println("Failed to add " + contact.getFirstName() + ": " + e.getMessage());
            }
        }
    }

    // Update contact (UC24)
    public void updateContactInJsonServer(String jsonUrl, int contactId, ContactPerson updatedContact) {
        try {
            given()
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .put(jsonUrl + "/" + contactId)
                .then()
                .statusCode(200);

            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId() == contactId) {
                    contacts.set(i, updatedContact);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to update contact: " + e.getMessage());
        }
    }

    // Delete contact (UC25)
    public void deleteContactFromJsonServer(String jsonUrl, int contactId) {
        try {
            given()
                .delete(jsonUrl + "/" + contactId)
                .then()
                .statusCode(200);

            contacts.removeIf(contact -> contact.getId() == contactId);
        } catch (Exception e) {
            System.out.println("Failed to delete contact: " + e.getMessage());
        }
    }

    // Fetch all contacts
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
        } catch (Exception e) {
            System.out.println("Error fetching contacts: " + e.getMessage());
        }
    }
}