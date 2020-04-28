
public class mainCollege {
	/**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 1
     */

	public static void main(String[] args) {

		College college = new College("Afeka College", 60, 80);
		Lecturer lecturer1 = new Lecturer("Victor", "Ben&Jerrys", 6);
		Lecturer lecturer2 = new Lecturer("alex", "Anita", 1);

		college.Add_Lecturer(lecturer1);
		college.Add_Lecturer(lecturer2);

		System.out.println(college);

		college.Sorting_Lecturers_By_Pen_Falls();
		System.out.println(college);

	}

}
