package contacts.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import contacts.Contact;

@Component
public class ContactsRepositoryImpl implements ContactsRepository {
	private static final List<Contact> contacts = new ArrayList<Contact>() {{
		add(new Contact("AAAAA", "AAAAA", "aaaaa.aaaaa@mail.com", "000000000"));
		add(new Contact("BBBBB", "BBBBB", "bbbbb.bbbbb@mail.com", "111111111"));
		add(new Contact("CCCCC", "CCCCC", "ccccc.ccccc@mail.com", "222222222"));
		add(new Contact("DDDDD", "DDDDD", "ddddd.ddddd@mail.com", "333333333"));
		add(new Contact("EEEEE", "EEEEE", "eeeee.eeeee@mail.com", "444444444"));
		add(new Contact("FFFFF", "FFFFF", "fffff.fffff@mail.com", "555555555"));
		add(new Contact("GGGGG", "GGGGG", "ggggg.ggggg@mail.com", "666666666"));
		add(new Contact("HHHHH", "HHHHH", "hhhhh.hhhhh@mail.com", "777777777"));
		add(new Contact("IIIII", "IIIII", "iiiii.iiiii@mail.com", "888888888"));
		add(new Contact("JJJJJ", "JJJJJ", "jjjjj.jjjjj@mail.com", "999999999"));
		add(new Contact("KKKKK", "KKKKK", "kkkkk.kkkkk@mail.com", "000000000"));
		add(new Contact("LLLLL", "LLLLL", "lllll.lllll@mail.com", "111111111"));
		add(new Contact("MMMMM", "MMMMM", "mmmmm.mmmmm@mail.com", "222222222"));
		add(new Contact("NNNNN", "NNNNN", "nnnnn.nnnnn@mail.com", "333333333"));
		add(new Contact("OOOOO", "OOOOO", "ooooo.ooooo@mail.com", "444444444"));
		add(new Contact("PPPPP", "PPPPP", "ppppp.ppppp@mail.com", "555555555"));
		add(new Contact("QQQQQ", "QQQQQ", "qqqqq.qqqqq@mail.com", "666666666"));
		add(new Contact("RRRRR", "RRRRR", "rrrrr.rrrrr@mail.com", "777777777"));
		add(new Contact("SSSSS", "SSSSS", "sssss.sssss@mail.com", "888888888"));
		add(new Contact("TTTTT", "TTTTT", "ttttt.ttttt@mail.com", "999999999"));
	}};

	@Override
	public List<Contact> findContacts(long max, int count) {
		return contacts;
	}

}
