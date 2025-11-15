package contact;

// The Contact class represents a single contact entry.
// It enforces simple validation rules for all fields.
public class Contact {

    // contactId cannot change once set
    private final String contactId;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    // Constructor creates a contact with all required fields.
    // Validation is handled through helper methods so the rules
    // stay in one place and are easy to adjust later.
    public Contact(String contactId,
                   String firstName,
                   String lastName,
                   String phone,
                   String address) {

        this.contactId = validateId(contactId);
        this.firstName = validateName(firstName, 10, "first name");
        this.lastName = validateName(lastName, 10, "last name");
        this.phone = validatePhone(phone);
        this.address = validateName(address, 30, "address");
    }

    // Getter for contactId (no setter because ID is immutable)
    public String getContactId() {
        return contactId;
    }

    // Getter and setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, 10, "first name");
    }

    // Getter and setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, 10, "last name");
    }

    // Getter and setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }

    // Getter and setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = validateName(address, 30, "address");
    }

    // ----------------- validation helpers -----------------

    // Validates the contact ID: not null and max 10 characters
    private String validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID");
        }
        return id;
    }

    // Validates simple string fields by length
    private String validateName(String value, int maxLength, String label) {
        if (value == null || value.length() > maxLength) {
            throw new IllegalArgumentException("Invalid " + label);
        }
        return value;
    }

    // Phone must be exactly 10 characters
    private String validatePhone(String phone) {
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return phone;
    }
}
