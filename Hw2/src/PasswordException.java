/**
 * 
 * @author Grigory Shaulov
 *
 */

public class PasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordException() {
		this("Not valid password!");
	}

	public PasswordException(String message) {
		super(message);
	}

}
