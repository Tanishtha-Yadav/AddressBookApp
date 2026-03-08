package com.addressbook;

import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC4: Delete ContactPerson in AddressBook ---");

        // Add initial contact
        System.out.println("Add initial contact:");
        System.out.print("First Name: "); String fn = sc.nextLine();
        System.out.print("Last Name: "); String ln = sc.nextLine();
        System.out.print("Address: "); String addr = sc.nextLine();
        System.out.print("City: "); String city = sc.nextLine();
        System.out.print("State: "); String state = sc.nextLine();
        System.out.print("Zip: "); String zip = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();

        ContactPerson c = new ContactPerson(fn, ln, addr, city, state, zip, phone, email);
        addressBook.addContact(c);

        // Delete contact
        System.out.print("\nEnter the first name of the contact to delete: ");
        String searchName = sc.nextLine();
        boolean deleted = addressBook.deleteContactByFirstName(searchName);
        if (!deleted) {
            System.out.println("Contact not found: " + searchName);
        }

        System.out.println("\nContacts in AddressBook:");
        for (ContactPerson contact : addressBook.getContacts()) {
            System.out.println(contact);
        }

        sc.close();
    }
}