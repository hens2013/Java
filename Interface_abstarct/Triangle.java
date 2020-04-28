package Hw1_HenShiryon;
/*
 * presenter:Hen Shiryon
 */
public class Triangle extends Shape {

	private int base;
	private int height;

	public Triangle(String color, int base, int height) throws Color_Exception {
		super(color);
		this.base = base;
		this.height = height;
	}

	@Override
	public double GetArea() {
		return ((this.base * this.height) / 2);
	}
	@Override
	public String toString() {
		return super.getClass().getSimpleName()+","+super.getColor()+","+this.base+"," +this.height+", area = " + GetArea();
	}
}
