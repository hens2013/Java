package Hw1_HenShiryon;

public class Circle extends Shape implements Radious_Check {

	private int radious;

	public Circle(String color, int radious) throws RadiousException,Color_Exception  {
		super(color);
		this.radious = radious;
		radiouscheck(radious);
	}


	@Override
	public double GetArea() {
		return (Math.PI * this.radious * this.radious);
	}

	@Override
	public String toString() {
		return super.getClass().getSimpleName() + "," + super.getColor() + "," + this.radious + ", area = " + GetArea();
	}
	@Override
	public void radiouscheck(int radius)  throws RadiousException{
		if (radious>500 || radious<0) {
			throw new RadiousException(radious);
		}
	}


	


}
