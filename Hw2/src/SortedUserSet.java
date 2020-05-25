/*
 * Hen Shiryon
 * 
 * 
 * */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SortedUserSet  extends LinkedList<User> implements Iterable<User>{
	
	private static final long serialVersionUID = 1L;
	
	
	
	
	public SortedUserSet() {
	super();
		
	}
	//method for checking if there is already an exist user
	public User isUser(String text, String text2) throws EMailException , PasswordException ,LoginException 
	{
		
		  User u1 = new User(null, null, text, text2);
	        SortedByEMail emailcomper = new SortedByEMail();
	        System.out.println(this);
	        System.out.println("------------------");
	      for(User u :this) {
	    	 
	    	  System.out.println(u);
	    	
	            if (emailcomper.compare(u1, u)==0){
	                if(!u.isEncryptedPassword(text2))
	                    throw new PasswordException("Wrong Password!!");
	                return u;
	            }
	      }
	        
		return null;
	}
		
				
	//adding method to the linkedlist
	public boolean add(User user) {
		this.addLast(user);
		Collections.sort(this,new sortedByName());
		return true;
		
		
	}
	//Returning the size 
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}


	//itartor class
	@Override
	 public Iterator<User> iterator() {
     
        return new UserIterator();
    }
	
	class UserIterator implements Iterator<User> {
		int index;

		private UserIterator() {
			this.index = -1;
		}

		@Override
		public boolean hasNext() {
			return (index + 1) < size();
		}

		@Override
		public User next() {
			if (hasNext())
                return get(++index);
			return null;
		}

	}

}
 
 


