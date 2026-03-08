package com.addressbook;

import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC3: Edit ContactPerson in AddressBook ---");

        // Add initial contacts
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

        // Edit existing contact
        System.out.print("\nEnter the first name of the contact to edit: ");
        String searchName = sc.nextLine();

        System.out.println("Enter new details for " + searchName + ":");
        System.out.print("First Name: "); String newFn = sc.nextLine();
        System.out.print("Last Name: "); String newLn = sc.nextLine();
        System.out.print("Address: "); String newAddr = sc.nextLine();
        System.out.print("City: "); String newCity = sc.nextLine();
        System.out.print("State: "); String newState = sc.nextLine();
        System.out.print("Zip: "); String newZip = sc.nextLine();
        System.out.print("Phone: "); String newPhone = sc.nextLine();
        System.out.print("Email: "); String newEmail = sc.nextLine();

        ContactPerson newDetails = new ContactPerson(newFn, newLn, newAddr, newCity, newState, newZip, newPhone, newEmail);
        boolean updated = addressBook.editContactByFirstName(searchName, newDetails);
        if (!updated) {
            System.out.println("Contact not found: " + searchName);
        }

        System.out.println("\nContacts in AddressBook:");
        for (ContactPerson contact : addressBook.getContacts()) {
            System.out.println(contact);
        }

        sc.close();
    }
}