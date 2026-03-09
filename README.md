# AddressBookApp - UC20: Add New Contact to Database (Transactional)

## Overview
UC20 adds the ability to **insert a new ContactPerson into the AddressBook database** using JDBC with transaction support.  
- Uses **PreparedStatement** for safe insertion  
- Supports **multiple tables** and ensures **rollback on failure**  
- JUnit tests verify **successful transaction**

---

## Notes

- UC20 continues **TDD approach**  
- Ensures **data consistency across multiple tables**  
- Future UCs may include **batch insert, advanced transaction handling, or stored procedures**
