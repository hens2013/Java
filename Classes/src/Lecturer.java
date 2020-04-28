/**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 1
     */
public class Lecturer {
	private String name;
	private int numOfTimesPensFalls;
	private String favoriteIceCream;
	private int identification_number;
	private static int au_id = 1000;

	// constructor
	public Lecturer(String name, String favoriteIceCream, int NumOfTimesPensFalls) {
		setName(name);
		setNumOfTimesPensFalls(NumOfTimesPensFalls);
		setFavoriteIceCream(favoriteIceCream);
		setId();

	}

	public String toString() {
		return "\nLecturer{" + "name='" + name + '\'' + ", favoriteIceCream='" + favoriteIceCream + '\''
				+ ", numOfTimesPenFalls=" + numOfTimesPensFalls + ", id=" + identification_number + "}";

	}

	 /**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 1
     */
	private void setId() {
		this.identification_number = au_id;
		au_id++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfTimesPensFalls() {
		return numOfTimesPensFalls;
	}

	public void setNumOfTimesPensFalls(int numOfTimesPensFalls) {
		
		if (numOfTimesPensFalls>0) {
			this.numOfTimesPensFalls = numOfTimesPensFalls;
		}
		else {
			this.numOfTimesPensFalls = 0;
		}

	}

	public String getFavoriteIceCream() {
		return favoriteIceCream;
	}

	public void setFavoriteIceCream(String favoriteIceCream) {
		this.favoriteIceCream = favoriteIceCream;
	}

}
