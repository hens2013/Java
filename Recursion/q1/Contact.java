package q1;

/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */
import java.io.PrintWriter;
import java.util.Scanner;


public class Contact {
	private String name;
	private String phoneNumber;

	public enum ePhoneNumberStatus {
		OK, PREFIX_INVALID_LEN, PREFIX_NOT_START_WITH_0, NUMBER_INVALID_LEN, NOT_ALL_DIGITS
	}

	Contact(Scanner scanner) {
		name = scanner.nextLine();
		phoneNumber = scanner.nextLine();
	}

	Contact(String name, String phoneNumber) {
		setName(name);
		setPhoneNumber(phoneNumber);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return name + "  -->  " + phoneNumber;
	}

	

	public ePhoneNumberStatus setPhoneNumber(String phoneNumber) {
		ePhoneNumberStatus status = checkIfPhoneNumberIsValid(phoneNumber);
		if (status == ePhoneNumberStatus.OK)
			this.phoneNumber = phoneNumber;

		return status;
	}

	public static ePhoneNumberStatus checkIfPhoneNumberIsValid(String phoneNumber) {
		String[] parts = phoneNumber.split("-");
		if (parts[0].length() != 3) {
			return Contact.ePhoneNumberStatus.PREFIX_INVALID_LEN;
		} else {
			if (parts[1].length() != 7) {
				return Contact.ePhoneNumberStatus.PREFIX_INVALID_LEN;
			} else {
				if (!isAllDigits(parts[0]) || !isAllDigits(parts[1])) {
					return Contact.ePhoneNumberStatus.PREFIX_NOT_START_WITH_0;
				} else {
					if (parts[0].charAt(0) != '0') {
						return Contact.ePhoneNumberStatus.NOT_ALL_DIGITS;
					} else {
						return Contact.ePhoneNumberStatus.OK;
					}
				}
			}
		}

	}

	private static boolean isAllDigits(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	static char getFirstLetterAsCapital(String name) {
		return name.charAt(0)>='a' && name.charAt(0)<='z'?
				(char)(name.charAt(0)-32): name.charAt(0);
	}

   public 	void save(PrintWriter writer) {
		writer.println(name);
		writer.println(phoneNumber);
	}

}