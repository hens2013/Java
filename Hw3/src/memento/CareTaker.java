/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package memento;

import java.util.LinkedList;
import java.util.List;

public class CareTaker {
	List<ReplaceMemento> stack = new LinkedList<ReplaceMemento>();


	public void saveMemento(ReplaceMemento replaceMemento) {
		stack.add(0,replaceMemento);
		
	}


	public ReplaceMemento restoreMemento() {
	return stack.remove(0);

	}

}