package com.addressbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        Map<String, AddressBook> addressBooks = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC6: Multiple AddressBooks System ---");

        boolean addMoreBooks = true;
        while (addMoreBooks) {
            System.out.print("Enter AddressBook name: ");
            String bookName = sc.nextLine().trim();

            if (addressBooks.containsKey(bookName)) {
                System.out.println("AddressBook with this name already exists! Try again.");
                continue;
            }

            AddressBook newBook = new AddressBook();
            addressBooks.put(bookName, newBook);
            System.out.println("AddressBook '" + bookName + "' created successfully.");

            boolean addMoreContacts = true;
            while (addMoreContacts) {
                System.out.println("Add contact to '" + bookName + "':");
                System.out.print("First Name: "); String fn = sc.nextLine();
                System.out.print("Last Name: "); String ln = sc.nextLine();
                System.out.print("Address: "); String addr = sc.nextLine();
                System.out.print("City: "); String city = sc.nextLine();
                System.out.print("State: "); String state = sc.nextLine();
                System.out.print("Zip: "); String zip = sc.nextLine();
                System.out.print("Phone: "); String phone = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();

                ContactPerson contact = new ContactPerson(fn, ln, addr, city, state, zip, phone, email);
                newBook.addContact(contact);

                System.out.print("Add another contact to '" + bookName + "'? (yes/no): ");
                String ans = sc.nextLine().trim().toLowerCase();
                if (!ans.equals("yes")) addMoreContacts = false;
            }

            System.out.print("Add another AddressBook? (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (!ans.equals("yes")) addMoreBooks = false;
        }

        // Display all AddressBooks
        System.out.println("\nAll AddressBooks:");
        for (String name : addressBooks.keySet()) {
            System.out.println("AddressBook: " + name);
            for (ContactPerson c : addressBooks.get(name).getContacts()) {
                System.out.println("  " + c);
            }
        }

        sc.close();
    }
}