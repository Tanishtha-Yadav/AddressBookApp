# AddressBookApp - UC24: Update Entry in JSONServer and Sync Memory

## Overview
UC24 adds the **ability to update a contact entry** in the Address Book **JSONServer** and ensures **in-memory sync**.  
Main features:

- Update a contact by `id` using **RESTAssured**  
- Sync updated contact in **application memory**  
- Supports **Open/Closed Principle** for future data sources (DB, CSV, JSON file, etc.)  
- Designed to allow new data sources without modifying existing update logic  

## Notes

- UC24 **reuses UC23 methods** for adding and fetching contacts  
- JSONServer must be running (default: `http://localhost:3000/contacts`)  
- Contacts are uniquely identified by `id`  
- Exception handling ensures failed updates are reported  
