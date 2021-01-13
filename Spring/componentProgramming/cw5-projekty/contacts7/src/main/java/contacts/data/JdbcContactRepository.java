package contacts.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import contacts.Contact;


@Repository
public class JdbcContactRepository implements ContactRepository {

	private JdbcOperations jdbc;

	@Autowired
	public JdbcContactRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Contact> findContacts(long max, int count) {
		return jdbc.query("SELECT id, first_name, last_name, email, phone" + 
							" FROM Contact" + 
							" WHERE id > ?" + 
							" ORDER by id asc limit 20", 
						  new ContactRowMapper(), max);
	}

	private static class ContactRowMapper implements RowMapper<Contact> {

		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
