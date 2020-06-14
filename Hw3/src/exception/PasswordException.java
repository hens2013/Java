/**
 * @author Grigory Shaulov
 * 
 * Implementation: Exception, Singleton, Memento, Iterator, Collecton, Thread, Comparator 
 */
package exception;


public class PasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordException() {
		this("Not valid password!");
	}

	public PasswordException(String message) {
		super(message);
	}

}
