package contact;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the ContactService class.
// Covers add, delete, update, and lookup behavior.
public class ContactServiceTest {

    // Test adding a new contact with a unique ID
    @Test
    void testAddContact_withUniqueId_addsSuccessfully() {
        ContactService service = new ContactService();
        Contact contact = new Contact("001", "Dom", "Smith", "1234567890", "123 Main");

        service.addContact(contact);

        Contact result = service.getContact("001");
        assertNotNull(result);
        assertEquals("Dom", result.getFirstName());
    }

    // Adding a contact with a duplicate ID should throw an exception
    @Test
    void testAddContact_withDuplicateId_throwsException() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("001", "Dom", "Smith", "1234567890", "123 Main");
        Contact contact2 = new Contact("001", "Alex", "Jones", "0987654321", "456 Oak");

        service.addContact(contact1);

        assertThrows(IllegalArgumentException.class,
                () -> service.addContact(contact2));
    }

    // Deleting an existing contact should remove it from the map
    @Test
    void testDeleteContact_withExistingId_deletesSuccessfully() {
        ContactService service = new ContactService();
        Contact contact = new Contact("001", "Dom", "Smith", "1234567890", "123 Main");

        service.addContact(contact);
        service.deleteContact("001");

        assertNull(service.getContact("001"));
    }

    // Deleting a non-existent contact should throw NoSuchElementException
    @Test
    void testDeleteContact_withInvalidId_throwsException() {
        ContactService service = new ContactService();

        assertThrows(NoSuchElementException.class,
                () -> service.deleteContact("999"));
    }

    // Updating an existing contact should change all fields as expected
    @Test
    void testUpdateContactFields_withValidId_updatesSuccessfully() {
        ContactService service = new ContactService();
        Contact contact = new Contact("001", "Dom", "Smith", "1234567890", "123 Main");

        service.addContact(contact);

        service.updateFirstName("001", "Alex");
        service.updateLastName("001", "Johnson");
        service.updatePhone("001", "0987654321");
        service.updateAddress("001", "789 Sunset Blvd");

        Contact updated = service.getContact("001");
        assertEquals("Alex", updated.getFirstName());
        assertEquals("Johnson", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("789 Sunset Blvd", updated.getAddress());
    }

    // Updating a non-existent contact should throw NoSuchElementException
    @Test
    void testUpdateFirstName_withInvalidId_throwsException() {
        ContactService service = new ContactService();

        assertThrows(NoSuchElementException.class,
                () -> service.updateFirstName("999", "Ghost"));
    }
}
