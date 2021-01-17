package contacts.data;

import contacts.Contact;

import java.util.List;

public interface ContactsFind {
    List<Contact> findContacts(long max, int count);
}
