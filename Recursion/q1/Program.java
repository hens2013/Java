package q1;

/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import q1.Contact.ePhoneNumberStatus;

public class Program {
	private final static String PHONE_BOOK_FILE_NAME = "PhoneBook.txt";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);
		
		PhoneBook pb = null;
		
		File f = new File(PHONE_BOOK_FILE_NAME);
		if (f.exists()) {
			System.out.println("PhoneBook is created form file");
			pb = new PhoneBook(PHONE_BOOK_FILE_NAME);
			
			System.out.println("PhoneBook details:");
			System.out.println(pb.toString());
		}
		else {
			System.out.println("PhoneBook is created manually");
			pb = new PhoneBook(10);
		}
		
		boolean fContinue = true;
		
		do {
			System.out.println("\nEnter contact? ");
			char answer = s.next().charAt(0);
			if (answer == 'n' || answer == 'N')
				fContinue = false;
			else if (answer == 'y' || answer == 'Y') {
				s.nextLine(); // clean buffer
				System.out.println("Enter contact name: ");
				String name = s.nextLine();
				
				System.out.println("Enter phone number: ");
				String phoneNumber = s.nextLine();
				
				Contact.ePhoneNumberStatus res = pb.addContact(name, phoneNumber);
				if (res == Contact.ePhoneNumberStatus.OK)
					System.out.println("Contact is added successfully");
				else
					System.out.println("Failed adding the contact, phone is invalid: " + res.name());
			}
			else
				System.out.println("Invalid answer");
		} while (fContinue);
		
		System.out.println("PhoneBook details:");
		System.out.println(pb.toString());
		
		System.out.println("\nEnter the name of the contact to whom you want to edit phone number");
		s.nextLine(); // clean the buffer
		String name = s.nextLine();
		Contact c = pb.getContactByName(name);
		if (c == null)
			System.out.println("No such contact");
		else {
			System.out.println("Enter new phone number: ");
			String number = s.nextLine();
			Contact.ePhoneNumberStatus res = c.setPhoneNumber(number);
			if (res != Contact.ePhoneNumberStatus.OK)
				System.out.println("Failed changing the number: " + res.name());
		}
		
		System.out.println("\nPhoneBook details again:");
		System.out.println(pb.toString());
		
		System.out.println("The fullest page is the letter " + pb.getFullestPage().getLetter());
		
		pb.save(PHONE_BOOK_FILE_NAME);
	}

}
