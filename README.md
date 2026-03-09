# AddressBookApp - UC22: Retrieve Entries from JSON Server

## Overview
UC22 adds the ability to **read Address Book contacts from a JSON Server** and update the in-memory Address Book.  
- Uses **REST Assured** for REST API calls  
- Parses JSON into **ContactPerson objects**  
- Updates **AddressBookService memory** with retrieved contacts  

---

## Notes
- Requires JSON Server running with `/contacts` endpoint  
- Ensures **memory sync** with latest server data  
- Supports TDD with **JUnit + REST Assured** tests  
- Future UCs may include **update, delete, or push back to JSON Server**
