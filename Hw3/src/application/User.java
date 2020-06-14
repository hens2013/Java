/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package application;

/**
 * 
 *
 */
import java.io.Serializable;
import java.util.Random;
import java.util.regex.Pattern;
import exception.*;

public class User implements Comparable<User>, Serializable {

	/**
	 * variables
	 */
	private static final long serialVersionUID = -6544111092540867923L;
	private String id;
	private String fName;
	private String lName;
	private String eMail;
	private String encryptedPassword;

	public User(String id, String fName, String lName, String eMail, String password)
			throws PasswordException, EMailException, IDException {
		setId(id);
		setFName(fName);
		setLName(lName);
		setEMail(eMail);
		setEncryptedPassword(password);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) throws IDException {
		if (id == null || !isValidId(id))
			throw new IDException();
		this.id = id;
	}
	
	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) throws EMailException {
		if (eMail == null || !isValidEMail(eMail))
			throw new EMailException();
		this.eMail = eMail;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String password) throws PasswordException {
		if (password == null || !isValidPassword(password))
			throw new PasswordException();
		this.encryptedPassword = encrypt(password);
	}

	public boolean isEncryptedPassword(String password) {
		return password.equals(decrypt(encryptedPassword));
	}

	// private methods

	private String encrypt(String password) {
		String enPass = "p";
		int len = password.length();
		enPass += (char) (len * 10);
		Random rnd = new Random();
		for (int i = 0; i < password.length(); i++)
			enPass += (char) ((int) password.charAt(i) + i + i);
		for (int i = 0; i < 15 - password.length(); i++)
			enPass += (char) ((int) rnd.nextInt(100) + 20);
		return enPass;
	}

	private String decrypt(String enPass) {
		String pass = "";
		int len = (int) (enPass.charAt(1) / 10);
		enPass = enPass.substring(2);
		for (int i = 0; i < len; i++)
			pass += (char) ((int) enPass.charAt(i) - i - i);
		return pass;
	}

	private boolean isValidEMail(String eMail) {
		//return Pattern.matches("^[A-Za-z0-9]+([-._]{1}[A-Za-z0-9]+){0,3}@[A-Za-z0-9]+([-._]{1}[A-Za-z0-9]+){0,3}$",
				//eMail);
		return true;
	}

	private boolean isValidPassword(String password) {
		return Pattern.matches("(?=^.{4,}$)(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$", password);
	}

	@Override
	public String toString() {
		return String.format("%-5s%-15s\t%-15s\t%-15s\t%-25s\t%-25s\n", "", id, fName, lName, eMail, encryptedPassword);
	}
	
	private boolean isValidId(String id) {
		return !id.contains("@");
	}

	@Override
	public int compareTo(User user2) {
		return (fName + lName).compareTo(user2.fName + user2.lName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		}
		return eMail.equals(other.eMail) || id == other.id;
	}
}
