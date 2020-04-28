

/**
 * 
 * @author Hen Shiryon, 208469718
 *
 */
public class Course<T extends Comparable<T>> implements Comparable<Course<T>> {

	private T IdOrName;
	private float avg;

	public Course(T id, float avg) {
		IdOrName = id;
		this.avg = avg;
	}

	public T getIdOrName() {
		return IdOrName;
	}

	public float getAvg() {
		return avg;
	}

	@Override
	public String toString() {
		return " (\t" + IdOrName + ",\t" + avg + ")";
	}


	@Override
	public int compareTo(Course<T> o) {
		// TODO Auto-generated method stub
		return this.IdOrName.compareTo(o.IdOrName);
	}


}
