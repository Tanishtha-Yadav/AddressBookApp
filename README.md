# AddressBookApp - UC21: Add Multiple Contacts to Database with Threads

## Overview
UC21 extends the AddressBook System by **adding multiple contacts concurrently** to the database using JDBC.  
- Each contact insertion is **transactional**, ensuring rollback on failure  
- Java **Threads** are used to simulate simultaneous insertions  
- Supports multiple tables (e.g., `contact_person` + `address_book`)  

---

## Notes

- UC21 continues **TDD approach**  
- Demonstrates **thread-safe and transactional inserts**  
- Future UCs may include **batch processing, concurrency control, or performance testing**
