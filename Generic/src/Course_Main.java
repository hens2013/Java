

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Hen Shiryon, 208469718
 *
 */
public class Course_Main {

	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		ArrayList<Course<?>> course_list = insert();
		s.close();
		Print(course_list);
		Course<?>[] c2 = fromArrayListtoArray(course_list);
		System.out.println("the 'regular array of coures, after sorting with Arrays.sort");
		Arrays.sort(c2);
		Print(c2);
		System.out.println("the 'regular array of coures, after my sorting method");
		bubbleSort(c2);

		Print(c2);

	}

	public static ArrayList<Course<?>> insert() {
		System.out.print("Please enter how many courses:");
		final int NUMBEROFCOURSES = s.nextInt();
		System.out.print("Please press 1 to insert the courses by names, 2 by id:");
		int selection = s.nextInt();

		ArrayList<Course<?>> course_list = new ArrayList<>();
		if (selection == 1) {
			for (int i = 0; i < NUMBEROFCOURSES; i++) {

				System.out.print("Please enter name: ");
				String name = s.next();
				System.out.print("Please enter avg: ");
				float avg = s.nextFloat();
				course_list.add(new Course<>(name, avg));

			}
		} else {
			for (int i = 0; i < NUMBEROFCOURSES; i++) {
				System.out.print("Please enter id: ");
				int id = s.nextInt();
				System.out.print("Please enter avg: ");
				float avg = s.nextFloat();
				course_list.add(new Course<>(id, avg));
			}
		}

		return course_list;
	}

	public static <T> void Print(ArrayList<T> list) {
		System.out.println(list.toString());
	}

	public static Course<?>[] fromArrayListtoArray(ArrayList<?> list) {
		Course<?>[] courses = new Course<?>[list.size()];
		courses = list.toArray(courses);
		return courses;
	}

	public static <T> void Print(T[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i].toString());
		}
	}

	public static <T extends Comparable<T>> void bubbleSort(T[] values) {
		int length = values.length;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length - 1 - i; j++) {
				if (values[j].compareTo(values[j + 1]) > 0) {
					T temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;

				}
			}
		}
	}

}
