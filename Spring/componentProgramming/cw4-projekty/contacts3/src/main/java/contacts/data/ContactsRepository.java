package contacts.data;

import java.util.List;

import contacts.Contact;

public interface ContactsRepository {

	public List<Contact> findContacts(long max, int count);

}
