package Hw1_HenShiryon;

import java.util.Scanner;
/*
 * presenter:Hen Shiryon
 */
public class Main_class {
	
	// scanner for the input
	public static Scanner s = new Scanner(System.in);

	// show method using StringBuffer
	public static StringBuffer Show(Shape[] sh) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < sh.length; i++) {
			s.append(sh[i].toString() + "\n");
		}

		return s;

	}

	// sorting the array using bubble sort
	public static void Sort(Shape[] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length - i - 1; j++) {
				if (s[j].GetArea() > s[j + 1].GetArea()) {
					Shape temp = s[j];
					s[j] = s[j + 1];
					s[j + 1] = temp;
				}
			}
		}
	}

	// method for the first circle input and using exception
	public static Circle Userinputforthefirstcircle() {

		String color;
		int radius;
		do {
			System.out.println("Please enter color and then radius for the first circle:");
			try {
				color = s.nextLine();
				radius = s.nextInt();
				s.nextLine();

				Circle circle = new Circle(color, radius);
				s.close();
				return circle;
			} catch (Color_Exception e) {
				System.out.println(e.getMessage());
			} catch (RadiousException e2) {
				System.out.println(e2.getMessage());
			} catch (Exception e) {
				System.out.println("Only integer is allowed here..");
				s.nextLine();
			}

		} while (true);

	}

	public static void main(String[] args) throws RadiousException, Exception {

		Shape[] shapes = new Shape[7];

		shapes[0] = Userinputforthefirstcircle();
		shapes[1] = new Circle("yellow", 10);
		shapes[2] = new Triangle("red", 10, 20);
		shapes[3] = new Rect("blue", 3, 5);
		shapes[4] = new Circle("blue", 100);
		shapes[5] = new Triangle("red", 1, 2);
		shapes[6] = new Rect("white", 6, 2);

		System.out.println(Show(shapes));
		System.out.println("after sorting:");
		System.out.println("==========================");
		Sort(shapes);
		System.out.println(Show(shapes));

	}

}
