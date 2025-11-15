package contact;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// ContactService manages Contact objects in memory.
// It acts like a simple service layer that provides
// basic CRUD operations and enforces unique IDs.
public class ContactService {

    // In-memory storage for contacts using contact ID as the key
    private final Map<String, Contact> contacts = new HashMap<>();

    // Adds a new contact to the service.
    // The contact must not be null and the ID must be unique.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }

        contacts.put(id, contact);
    }

    // Deletes a contact by ID.
    // Throws a clear exception if the contact does not exist.
    public void deleteContact(String contactId) {
        requireExistingContact(contactId);
        contacts.remove(contactId);
    }

    // Updates the contact's first name.
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = requireExistingContact(contactId);
        contact.setFirstName(firstName);
    }

    // Updates the contact's last name.
    public void updateLastName(String contactId, String lastName) {
        Contact contact = requireExistingContact(contactId);
        contact.setLastName(lastName);
    }

    // Updates the contact's phone number.
    public void updatePhone(String contactId, String phone) {
        Contact contact = requireExistingContact(contactId);
        contact.setPhone(phone);
    }

    // Updates the contact's address.
    public void updateAddress(String contactId, String address) {
        Contact contact = requireExistingContact(contactId);
        contact.setAddress(address);
    }

    // Retrieves a contact by ID.
    // Returns null if not found (read-only lookup).
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    // Helper method that either returns an existing contact
    // or throws a NoSuchElementException with a clear message.
    private Contact requireExistingContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new NoSuchElementException("No contact found with ID: " + contactId);
        }
        return contact;
    }
}
