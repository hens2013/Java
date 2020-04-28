package Hw1_HenShiryon;
/*
 * presenter:Hen Shiryon
 */
public class Color_Exception extends Exception {

	private static final long serialVersionUID = 1L;

	public Color_Exception(String color) {
		super(color + " is invalid color!");
	}

}
