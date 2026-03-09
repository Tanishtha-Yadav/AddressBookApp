# AddressBookApp - UC23: Add Multiple Contacts to JSONServer

## Overview
UC23 extends the Address Book system to **add multiple contacts to a JSONServer** and **sync them with in-memory Address Book**.  
Main features:

- Add multiple contacts via a **list**  
- Use **RESTAssured** for REST API calls to JSONServer  
- Sync contacts added to **application memory**  
- **Exception handling** ensures failed inserts are reported  
- Designed for **non-blocking operations**  

## Notes

- JSONServer must be running (default: `http://localhost:3000/contacts`)  
- Contacts are uniquely identified by `id` for update/delete purposes  
- UC23 **reuses UC22 fetch functionality** to keep memory in sync  
- Threading or async can be added to avoid blocking the main thread
  
