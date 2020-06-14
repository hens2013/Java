/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */package iterator;

 
/**
 * 
 *
 */
import java.util.Iterator;
import application.User;
import collection.SortedUserSet;

public class UserIterator implements Iterator<User> {

	private SortedUserSet list;
	private int index;
	
	public UserIterator(SortedUserSet list) {
		this.list = list;
		this.index=-1;
	}
	
	@Override
	public boolean hasNext() {
		return (index+1)==list.size();
	}

	@Override
	public User next() {
		if(hasNext())
			return list.get(++index);
		return null;
	}
	
	public void remove() {
		list.remove(index);
	}

}
