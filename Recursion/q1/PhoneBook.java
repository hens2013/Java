package q1;

/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import q1.Contact.ePhoneNumberStatus;

public class PhoneBook {
	private final static int NUM_OF_LETTERS = 'z' - 'a' + 1;

	private ContactsPage[] allPages;

	PhoneBook(int maxContactsPerPage) {
		this.allPages = new ContactsPage[NUM_OF_LETTERS];
		for (char i = 'A'; i < 'Z'; i++) {
			this.allPages[i - 'A'] = new ContactsPage(i, maxContactsPerPage);
		}
	}

	public PhoneBook(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner s = new Scanner(file);
		allPages = new ContactsPage[NUM_OF_LETTERS];
		for (int i = 0; i < NUM_OF_LETTERS; i++) {
			allPages[i] = new ContactsPage(s);
		}
		s.close();

	}

	public Contact getContactByName(String contactName) {
		return allPages[Contact.getFirstLetterAsCapital(contactName) - 'A'].getContactByName(contactName);
	}

	public ePhoneNumberStatus addContact(String name, String phoneNumber) {
		Contact.ePhoneNumberStatus res = Contact.checkIfPhoneNumberIsValid(phoneNumber);
		if (res == Contact.ePhoneNumberStatus.OK) {
			allPages[Contact.getFirstLetterAsCapital(name) - 'A'].addContact(new Contact(name, phoneNumber));
		}
		return res;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("My Phone Book:\n");
		for (ContactsPage page : allPages) {
			if (page != null) {
				builder.append(page.toString()).append('\n');
			}
		}
		return builder.toString();
	}

	 public ContactsPage getFullestPage() {
		ContactsPage contactsPage = allPages[0];
		int max=allPages[0].getSavedContactsCounter();
		for (int i = 1; i <NUM_OF_LETTERS; i++) {
			if (allPages[i].getSavedContactsCounter()>max) {
				max=allPages[i].getSavedContactsCounter();
				contactsPage=allPages[i];
			}
		}
	
		return contactsPage;
	}

	

   public void save(String pathFile) throws FileNotFoundException {
		File file = new File(pathFile);
		PrintWriter writer = new PrintWriter(file);
          writer.println(NUM_OF_LETTERS); 
		for (int i=0; i<NUM_OF_LETTERS; i++) {
			allPages[i].save(writer);
		}
		writer.close();
	}

}
