package com.addressbook;

import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC2: Add ContactPerson to AddressBook ---");

        boolean addMore = true;
        while (addMore) {
            System.out.print("First Name: "); String firstName = sc.nextLine();
            System.out.print("Last Name: "); String lastName = sc.nextLine();
            System.out.print("Address: "); String address = sc.nextLine();
            System.out.print("City: "); String city = sc.nextLine();
            System.out.print("State: "); String state = sc.nextLine();
            System.out.print("Zip: "); String zip = sc.nextLine();
            System.out.print("Phone: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();

            ContactPerson contact = new ContactPerson(firstName, lastName, address, city, state, zip, phone, email);
            addressBook.addContact(contact);

            System.out.print("Add another contact? (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (!ans.equals("yes")) addMore = false;
        }

        System.out.println("\nContacts in AddressBook:");
        for (ContactPerson c : addressBook.getContacts()) {
            System.out.println(c);
        }

        sc.close();
    }
}