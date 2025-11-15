package contact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Contact class.
// Focused on constructor validation and basic setters.
public class ContactTest {

    @Test
    void testContactConstructor_withValidValues_createsContact() {
        Contact contact = new Contact(
                "001",
                "Dom",
                "Smith",
                "1234567890",
                "123 Main Street"
        );

        assertEquals("001", contact.getContactId());
        assertEquals("Dom", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testConstructor_withNullValues_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Dom", "Smith", "1234567890", "123 Main"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", null, "Smith", "1234567890", "123 Main"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "Dom", null, "1234567890", "123 Main"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "Dom", "Smith", null, "123 Main"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "Dom", "Smith", "1234567890", null));
    }

    @Test
    void testConstructor_withInvalidLengths_throwsException() {
        // contactId too long
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Dom", "Smith", "1234567890", "123 Main"));

        // firstName too long
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "FirstnameTooLong", "Smith", "1234567890", "123 Main"));

        // lastName too long
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "Dom", "LastnameTooLong", "1234567890", "123 Main"));

        // phone not 10 characters
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("001", "Dom", "Smith", "123", "123 Main"));

        // address too long
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(
                        "001",
                        "Dom",
                        "Smith",
                        "1234567890",
                        "Address way too long to be accepted here"
                ));
    }
}
