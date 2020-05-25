import java.util.Comparator;

public class sortedByName implements Comparator<User> {
	//Comparing according to the user fll name	
	@Override
	public int compare(User u1, User u2) {

		return u1.compareTo(u2);
	}

}
