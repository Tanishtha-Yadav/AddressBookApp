package com.addressbook;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppApplicationTests {

    private final AddressBookService service = new AddressBookService();

    @BeforeAll
    static void setupServer() {
        // JSON server should be running at http://localhost:3000 with /contacts endpoint
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    void testFetchContactsFromJsonServer() {
        // Ensure /contacts endpoint is reachable
        given().when().get("/contacts").then().statusCode(200);

        // Fetch contacts into memory
        service.fetchContactsFromJsonServer("http://localhost:3000/contacts");

        assertFalse(service.getContacts().isEmpty(), "Contacts should be fetched into memory");
    }
}