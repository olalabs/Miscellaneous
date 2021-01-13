package contacts.data;

import java.util.List;

import contacts.Contact;

public interface ContactRepository {

	List<Contact> findContacts(long max, int count);

}