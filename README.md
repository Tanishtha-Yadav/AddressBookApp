# AddressBookApp - UC17: Update Contact Information in DB

## Overview
UC17 extends previous UCs by **updating a ContactPerson in the database** using JDBC.  
- Uses **PreparedStatement** to safely update a contact  
- Ensures **in-memory data matches DB**  
- Supports **JUnit test for sync verification**  

---

## Notes

- UC17 follows **TDD approach**  
- Requires **JDBC connection** to DB  
- Contact equality is based on **first and last name**  
- Future UCs will add **delete, multi-addressbook, and advanced DB operations**
