/**
     * Author: Hen Shiryon
     * ID: 208469718
     * Exercise: 2
     */
public class Room {
	private int num_of_beds;
	private Guest[] all_Guests;

	public Room(int numOfBeds) {
		this.num_of_beds=numOfBeds;
	}


	public int getNum_of_beds() {
		return num_of_beds;
	}
	public Guest[] getAll_Guests() {
		return all_Guests;
	}

	public void setAllGuests(Guest[] allGuests) {
		this.all_Guests = allGuests;
	}

	
	

}
