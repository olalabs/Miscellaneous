package contacts;

import java.util.Collections;

public class GenContacts {
	public static void main(String[] args) {
		for (int i = 0; i < 120; i++) {
			String s = String.join("", Collections.nCopies(5, String.valueOf((char)(65 + i % 26))));
			String s1 = s.toLowerCase();
			String t = String.join("", Collections.nCopies(9, String.valueOf((char)(48 + i % 10))));
			System.out.printf("add(new Contact(\"%s\", \"%s\", \"%s.%s@%s.%s\", \"%s\"));%n", s, s, s1, s1, "mail", "com", t);
		}
	}
}
