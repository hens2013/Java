package Hw1_HenShiryon;

public abstract class Shape {
	/*
	 * presenter:Hen Shiryon
	 */
	private String color;
	private final String[] COLORS = { "black", "brown", "purple", "green" };

	public Shape(String color) throws Color_Exception {
		String c = color.toLowerCase();
		for (String invColor : COLORS) {
			if (invColor.equals(c)) {
				throw new Color_Exception(c);
			}
		}
		this.color = c;

	}

	public abstract double GetArea();

	public String getColor() {
		return color;
	}
	
}
