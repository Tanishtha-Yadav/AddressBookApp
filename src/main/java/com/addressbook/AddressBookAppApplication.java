package com.addressbook;

import java.util.Scanner;

public class AddressBookAppApplication {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- UC13: File IO for AddressBook ---");

        boolean addMore = true;
        while (addMore) {
            System.out.print("First Name: "); String fn = sc.nextLine();
            System.out.print("Last Name: "); String ln = sc.nextLine();
            System.out.print("Address: "); String addr = sc.nextLine();
            System.out.print("City: "); String city = sc.nextLine();
            System.out.print("State: "); String state = sc.nextLine();
            System.out.print("Zip: "); String zip = sc.nextLine();
            System.out.print("Phone: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();

            ContactPerson contact = new ContactPerson(fn, ln, addr, city, state, zip, phone, email);
            addressBook.addContact(contact);

            System.out.print("Add another contact? (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (!ans.equals("yes")) addMore = false;
        }

        System.out.print("\nEnter file name to save AddressBook: ");
        String fileName = sc.nextLine().trim();
        addressBook.writeToFile(fileName);

        System.out.print("\nDo you want to read from the file? (yes/no): ");
        String readAns = sc.nextLine().trim().toLowerCase();
        if (readAns.equals("yes")) {
            addressBook.readFromFile(fileName);
            System.out.println("\nContacts from file:");
            addressBook.getContacts().forEach(System.out::println);
        }

        sc.close();
    }
}