/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package comparator;


import java.util.Comparator;

import application.User;

public class SortById implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
		return u1.getId().compareTo(u2.getId());
	}

}
