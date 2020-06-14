/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import application.*;
import comparator.*;
import exception.*;
import iterator.*;

public class SortedUserSet extends LinkedList<User> implements Iterable<User> {


	private static final long serialVersionUID = -4095908783738143771L;
	private Comparator<User> comparator;

	public SortedUserSet() {
		super();
		this.comparator = new SortByName();
	}

	public User get(int index) {
		if (index >= 0 && index < size())
			return super.get(index);
		return null;
	}

	public boolean add(User user) {
		if (user == null) {
			return false;
		}
		Collections.sort(this, new SortByEmail());
		int index = Collections.binarySearch(this, user, new SortByEmail());
		if (index >= 0)
			super.set(index, user);
		else
			super.add(Math.abs(index + 1), user);
		sort();
		return true;
	}

	public User remove(int index) {
		if (index >= 0 && index < size())
			return super.remove(index);
		return null;
	}

	@Override
	public Iterator<User> iterator() {
		return new UserIterator(this);
	}

	public void sort() {
		Collections.sort(this, comparator);
	}

	public void setComparator(Comparator<User> comp) {
		this.comparator = comp;
	}

	public Comparator<User> getComparator() {
		return this.comparator;
	}

	public User isUser(String eMail, String password) throws EMailException, PasswordException, LoginException, IDException {
		if (eMail == null || eMail.equals(""))
			throw new EMailException();
		if (password == null || password.equals(""))
			throw new PasswordException();
		User user = null;
		if (eMail.contains("@")) 
			user = userBinSearch(new User("1", "", "", eMail, password), new SortByEmail());
		else
			user = userBinSearch(new User(eMail, "", "", "a@a.com", password), new SortById());
		
		if (user != null)
			if (!user.isEncryptedPassword(password))
				throw new LoginException();
		return user;
	}

	private User userBinSearch(User u, Comparator<User> sortedBy) {
		User user = null;
		Collections.sort(this, sortedBy);
		int index = Collections.binarySearch(this, u, sortedBy);
		if (index >= 0)
			user = get(index);
		sort();
		return user;
	}

	@Override
	public String toString() {
		String str = String.format("%-5s%-10s\t%5s\t%-15s\t%-15s\t%-25s\t%-25s\n", "", "ID", "", "First Name",
				"Last Name", "EMail", "Password(Encrypted)");
		String ch = "";
		for (int i = 0; i < size(); i++) {
			if (this.comparator instanceof SortByName && !get(i).getFName().substring(0, 1).equalsIgnoreCase(ch)) {
				ch = get(i).getFName().substring(0, 1).toUpperCase();
				str += ch + ":\n";
			}
			str += get(i);
		}
		if (size() == 0)
			str += "List Is Empty.\n";
		return str;
	}

}
