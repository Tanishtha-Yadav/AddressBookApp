# AddressBookApp - UC25: Delete ContactPerson in JSON Server

## Overview
UC25 extends UC24 by adding the **ability to delete an existing contact** on the JSON Server and keep the **in-memory Address Book** in sync.

Main features:

- Delete an existing contact by **ID** from JSON Server using RESTAssured
- Remove the deleted contact from **AddressBookService memory**
- JUnit test verifies the memory is updated correctly

---
## Notes

- UC25 **reuses UC23 & UC24 code**  
- Each `ContactPerson` must have an **`id`** field matching the JSON Server entry  
- Future UCs will integrate additional features like database persistence and multi-threading
