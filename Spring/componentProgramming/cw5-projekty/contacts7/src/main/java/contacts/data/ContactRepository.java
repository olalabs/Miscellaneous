package contacts.data;

import java.util.List;

import contacts.Contact;

public interface ContactRepository {

	List<Contact> findContacts(long max, int count);

	void insert(Contact contact);

	Contact findOne(long id);

	void update(Contact contact);

	void delete(long id);
}