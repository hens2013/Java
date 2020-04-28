import java.util.Arrays;
/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 1
 */
public class College {
	private String name;
	private int num_Of_Lecturers_Are_Teaching;
	private Lecturer[] alllecturers;
	private int position;

	public College(String name, int max_of_lecturers, int num_of_lectures) {
		this.setName(name);
		this.setNumOfLecturers(num_of_lectures);
		this.alllecturers = new Lecturer[max_of_lecturers];

	}

	public College(College otherCollege) {
		this.setName(otherCollege.getName());
		this.setNumOfLecturers(otherCollege.getNumOfLecturers());
		Lecturer[] alllecturers = otherCollege.getAllLecturers();
		this.setAllLecturers(Arrays.copyOf(alllecturers, alllecturers.length));
		this.position = otherCollege.getPosition();
	}
	  private void setAllLecturers(Lecturer[] all_Lecturers) {
	        this.alllecturers = all_Lecturers;
	    }

	public Lecturer[] getAllLecturers() {
		return alllecturers;
	}
	
	private void setNumOfLecturers(int numOfLecturers) {

		if (numOfLecturers > 0) {
			this.num_Of_Lecturers_Are_Teaching = numOfLecturers;
		} else {
			this.num_Of_Lecturers_Are_Teaching = 0;
		}
	}

	private int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfLecturers() {
		return num_Of_Lecturers_Are_Teaching;
	}
	 /**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 1
     */
	

	public boolean Add_Lecturer(Lecturer lecturer) {

		if (this.alllecturers.length - 1 > this.position) {
			this.alllecturers[this.position] = lecturer;
			this.position++;
			return true;
		} else
			return false;

	}

	public void Sorting_Lecturers_By_Pen_Falls() {
		Lecturer[] le = this.alllecturers;
		int number = this.position;
		for (int i = 0; i < number - 1; i++) {
			for (int j = 0; j < number - i - 1; j++) {
				if (le[j].getNumOfTimesPensFalls() < le[j + 1].getNumOfTimesPensFalls()) {
					Lecturer temp_now = le[j];
					le[j] = le[j + 1];
					le[j + 1] = temp_now;
				}
			}
		}

	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(");
		for (int i = 0; i < position; i++) {
			str.append(alllecturers[i]);
			if (i != position - 1) {
				str.append(",");
			}
		}
		str.append(")");

		return "\nCollgege [" + "name=" + name + '\'' + ",num of lecturers=" + num_Of_Lecturers_Are_Teaching
				+ ", alllectures=" + str.toString() + "]";
	}

	

}
