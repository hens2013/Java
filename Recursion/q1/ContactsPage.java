package q1;

/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */
import java.io.PrintWriter;
import java.util.Scanner;

class ContactsPage {


	private char letter;
	private Contact[] allContacts;
	private int numOfContacts;

	public ContactsPage(char letter, int maxContactsPerPage) {
		this.letter = letter;
		this.allContacts = new Contact[maxContactsPerPage];
		numOfContacts = 0;
	}

	public ContactsPage(Scanner scanner) {
		letter = scanner.nextLine().charAt(0);
		allContacts = new Contact[scanner.nextInt()];
		numOfContacts = scanner.nextInt();
		scanner.nextLine();
		allContacts = new Contact[allContacts.length];
		for (int i = 0; i < numOfContacts; i++) {
			allContacts[i] = new Contact(scanner);
		}
	}

	public void save(PrintWriter pw) {
		pw.println(letter);
		pw.println(allContacts.length);
		pw.println(numOfContacts);
		for (int i = 0; i < numOfContacts; i++) {
			allContacts[i].save(pw);
		}
	}

	public boolean addContact(Contact contact) {
		if (numOfContacts == allContacts.length)
			return false;
		allContacts[numOfContacts++] = contact;
		return true;
	}

	public Contact getContactByName(String contactName) {
		for (int i = 0; i < numOfContacts; i++) {
			if (allContacts[i].getName().equals(contactName)) {
				return allContacts[i];
			}
		}
		return null;
	}

	public char getLetter() {
		return letter;
	}

	public int getSavedContactsCounter() {
		return numOfContacts;
	}

	public Contact[] getContacts() {
		return allContacts;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer(letter + ":\n");

		for (int i = 0; i < numOfContacts; i++) {
			builder.append(allContacts[i].toString() + "\n");

		}
		return builder.toString();
	}

}
