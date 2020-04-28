import java.util.Scanner;

public class mainHotel {
	/**
	 * Author: Hen Shiryon ID: 208469718 Exercise: 2
	 */
	public static void main(String[] args) {
		Hotel hotel = new Hotel(6, 99);
		int input = 0;
		Scanner scanner = new Scanner(System.in);
		 while (input != 6) {
	            do {
	                System.out.println("\nHello Sir - Welcome To Our Hotel! \n " +
	                        "Please select from the following actions:\n" +
	                        "1) Reserve A Room\n" +
	                        "2) Find A Guest\n" +
	                        "3) Present Hotel Capacity\n" +
	                        "4) Most Available Floor\n" +
	                        "5) Last Reservation Time\n" +
	                        "6) Exit Menu");
	                input = scanner.nextInt();

	            } while (0 > input || input > 6);

	            switch (input) {
	                case 1:
	                    int beds;
	                    do {
	                        System.out.println("How Many Beds Do You Want To Reserve (1-4):");
	                        beds = scanner.nextInt();

	                    } while (0 > beds || beds > 4);

	                    Guest[] guests = new Guest[beds];

	                    for (int i = 0; i < guests.length; i++) {

	                        String name;
	                        do {
	                            System.out.println("What Is The Name Of Guest No." + (i + 1));
	                            name = scanner.next();

	                        } while (name == null);

	                        int passportNumber;
	                        do {
	                            System.out.println("What Is The Passport Number Of Guest No." + (i + 1));
	                            passportNumber = scanner.nextInt();

	                        } while (passportNumber <= 0);

	                        guests[i] = new Guest(name, passportNumber);
	                    }

	                    int reserved = hotel.addReservation(guests);

	                    if (reserved != -1) {
	                        System.out.println("The Guests Will Stay In Room No." + reserved);
	                    } else {
	                        System.out.println("Sorry, But We Are Out Of Place Right Now.");
	                    }
	                    break;

	                case 2:
	                    int passportNumber;
	                    do {
	                        System.out.println("Please Enter The Passport Number Of The Guest:");
	                        passportNumber = scanner.nextInt();

	                    } while (passportNumber <= 0);
	                    int roomNumber = hotel.findGuest(passportNumber);
	                    if (roomNumber != -1) {
	                        System.out.println("The Guest Stays In Room No." + roomNumber);
	                    } else {
	                        System.out.println("Sorry, But This Person Doesn't Exist In Our Hotel");
	                    }
	                    break;

	                case 3:
	                    hotel.present_Hotel_Capacity();
	                    break;

	                case 4:
	                    System.out.println("Most Available Floor is " + hotel.findMaxRoom());
	                    break;

	                case 5:
	                    hotel.printLastReservation();
	                    break;
	            }
	        }


		 scanner.close();

	}

}
