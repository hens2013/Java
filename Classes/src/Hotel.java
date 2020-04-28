
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 2
 */

public class Hotel {
	private int num__of_Used_Rooms;
	private Room[][] all_Rooms;
	private static LocalDateTime lastReservation=LocalDateTime.now();

	public Hotel(int floors, int num_of_rooms_in_floor) {
		this.all_Rooms = new Room[floors][num_of_rooms_in_floor];

		for (int i = 0; i < this.all_Rooms.length; i++) {
			for (int j = 0; j < this.all_Rooms[i].length; j++) {
				int num_of_beds = (int) (Math.random() * 4 + 1);
				Room room = new Room(num_of_beds);
				this.all_Rooms[i][j] = room;
			}
		}
	}

	public int addReservation(Guest[] guests) {
		int num_of_beds = guests.length;
		for (int i = 0; i < guests.length; i++) {
			for (int j = 0; j < guests.length; j++) {
				if (this.all_Rooms[i][j].getNum_of_beds() > num_of_beds) {
					this.all_Rooms[i][j].setAllGuests(guests);
					lastReservation = LocalDateTime.now();
					this.num__of_Used_Rooms++;
					return (1 + j) + (i + 1) * 100;
				}
			}
		}
		return -1;
	}

	public int findGuest(int passport) {
		for (int i = 0; i < all_Rooms.length; i++) {
			for (int j = 0; j < all_Rooms[i].length; j++) {
				Guest[] room_guests = this.all_Rooms[i][j].getAll_Guests();
				for (Guest guest : room_guests) {
					if (guest.getPassportNumber() == passport) {
						return (1 + j) + (i + 1) * 100;
					}
				}
			}
		}
		return -1;
	}

	public int findMaxRoom() {
		int floor_number = 0;
		int count = 0;

		for (int i = 0; i < all_Rooms.length; i++) {
			int temp_count = 0;
			for (int j = 0; j < all_Rooms[i].length; j++) {
				Guest[] guests = this.all_Rooms[i][j].getAll_Guests();
				if (guests == null) {
					temp_count++;
				}

			}
			if (temp_count > count) {
				count = temp_count;
				floor_number = i;

			}
		}
		return floor_number + 1;
	}

	public void printLastReservation() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		System.out.println(lastReservation.format(formatter));

	}

	public void present_Hotel_Capacity() {
		System.out.println("this is the hotel capacity");
		int count = 0;
		for (int i = 0; i < this.all_Rooms.length; i++) {
			if (num__of_Used_Rooms > count) {
				System.out.printf("In Floor Number %d:\n\n", (i + 1));
				for (int j = 0; j < this.all_Rooms[i].length; j++) {
					Guest[] guests = this.all_Rooms[i][j].getAll_Guests();
					if (guests != null) {
						System.out.printf("In Room Number %d Those Are The Guests:\n", (j + 1));
						for (Guest guest : guests) {
							if (guest != null) {
								System.out.println(guest);
								count++;
							}
						}
					}
				}
			} else {
				return;
			}

		}
	}

}
