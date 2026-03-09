package com.addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

    private final List<ContactPerson> contacts = new ArrayList<>();

    public List<ContactPerson> getContacts() {
        return contacts;
    }

    // UC22: Read from JSON Server and update in-memory AddressBook
    public void fetchContactsFromJsonServer(String jsonUrl) {
        try {
            URL url = new URL(jsonUrl);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            Type contactListType = new TypeToken<List<ContactPerson>>() {}.getType();
            List<ContactPerson> retrievedContacts = new Gson().fromJson(reader, contactListType);

            contacts.clear();
            contacts.addAll(retrievedContacts);

            System.out.println("Fetched " + contacts.size() + " contacts from JSON Server.");

        } catch (Exception e) {
            System.out.println("Error fetching contacts: " + e.getMessage());
        }
    }
}