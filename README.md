# AddressBookApp - UC23: Add Multiple Entries to JSON Server

## Overview
UC23 enables adding **multiple contacts** to a JSON Server and **syncing the Address Book memory**.  
- Uses **REST Assured** to POST contacts to JSON Server  
- Updates in-memory contacts list in **AddressBookService**  
- Supports **multiple additions in a loop**  

---

## Notes
- Requires **JSON Server running with /contacts endpoint**  
- Ensures **memory is always synced** with server after additions  
- JUnit tests verify **memory sync and server response**  
- Builds on UC22 fetching capability
