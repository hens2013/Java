import java.util.Comparator;

public class SortedByEMail implements Comparator<User> {
	//compare according to the email
	@Override
	public int compare(User u1, User u2) {
	
		
		return u1.getEMail().compareTo(u2.getEMail());

	}
}
