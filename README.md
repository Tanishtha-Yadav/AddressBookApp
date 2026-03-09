# AddressBookApp - UC24: Update ContactPerson in JSON Server

## Overview
UC24 extends UC23 by adding the **ability to update an existing contact** on the JSON Server and keep the **in-memory Address Book** in sync.

Main features:

- Update an existing contact by **ID** on JSON Server using RESTAssured
- Sync the updated contact in **AddressBookService memory**
- JUnit test verifies the memory is updated correctly

---
## Notes

- UC24 **reuses UC23 code**  
- Each `ContactPerson` must have an **`id`** field matching the JSON Server entry  
- Future UCs will integrate additional features like DB persistence and multi-threading
