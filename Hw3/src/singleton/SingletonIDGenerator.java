package singleton;



public class SingletonIDGenerator {

	private static SingletonIDGenerator instance = null;
	private int id;
 
	private SingletonIDGenerator() {
		
		this.id=0;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNext() {
		return id++;
	}
	
	@Override
	public String toString() {
		return "IDGenerator [Next ID: " + id + "]";
	}

	public static SingletonIDGenerator getInstance() {
		if(instance==null)
			instance = new SingletonIDGenerator();
		return instance;
	}

}
