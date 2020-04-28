package Hw1_HenShiryon;

public class RadiousException extends Exception {
	/*
	 * presenter:Hen Shiryon
	 */
	private static final long serialVersionUID = 1L;

	  public RadiousException(double radius) {
	        super(radius + " is illegal radius");
	    }
	 
	

}
