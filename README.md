# AddressBookApp - UC16: Retrieve All Contacts from DB

## Overview
UC16 extends previous UCs by **retrieving all AddressBook entries from a database** using JDBC.  
- Connects to the database  
- Retrieves all contacts as **ContactPerson objects**  

---

## Notes

- UC16 uses **JDBC** to connect to the DB  
- Supports **JUnit TDD approach** for testing  
- Assumes a table `contact_person` exists matching the ContactPerson fields  
- Future UCs may include **insert, update, delete operations via JDBC**
