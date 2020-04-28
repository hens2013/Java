package Hw1_HenShiryon;
/*
 * presenter:Hen Shiryon
 */
public class Rect extends Shape {
	private int width;
	private int height;

	public Rect(String color, int width, int height) throws Color_Exception {
		super(color);
		this.width = width;
		this.height = height;
	}

	@Override
	public double GetArea() {
		return this.height * this.width;
	}

	@Override
	public String toString() {
		return super.getClass().getSimpleName() + "," + super.getColor() + "," + this.width + "," + this.height + ", area = "
				+ GetArea();
	}


}
