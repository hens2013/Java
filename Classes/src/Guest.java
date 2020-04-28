 /**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 2
     */

public class Guest {
	private String name;
	private int passportNumber;

	public Guest(String name, int passportNumber) {
		setName(name); 
		setPassportNumber(passportNumber);
	}

	public String toString() {
		return "\nGuest{" + "name='" + name + '\'' + ", passportNumber=" + passportNumber + '}';
	}

	public String getName() {
		return name;
	}

	private  void setName(String name) {
		this.name = name;
	}

	public int getPassportNumber() {
		return passportNumber;
	}

	private  void setPassportNumber(int passportNumber) {
		if (passportNumber > 0) {
			this.passportNumber = passportNumber;
		} else {
			System.out.println("Illegal Password Number!");
		}
	}

}
