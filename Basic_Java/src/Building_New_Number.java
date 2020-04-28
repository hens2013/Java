import java.util.Scanner;

class Building_New_Number {

	
	// Hen Shiryon
	// id 208469718
	// EX 3
	public static void main(String[] args) {

		int maxnumber = 0;
		int minnumber = 0;
		int count = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter ur number");
		int number = scanner.nextInt();
		scanner.close();

		int testnumber = number;
		// counting the digits in the number
		while (testnumber > 0) {
			testnumber /= 10;
			count += 1;
		}
		testnumber = number;
		// running of the number
		for (int i = 0; i < count - 1; i++) {
			int test_b;
			int test_a = testnumber % 10; // getting the first digit of the number
			testnumber /= 10;
              
			for (int j = 0; j < count; j++) {
				test_b = testnumber % 10;  // getting the second digit of the number
				testnumber /= 10;

				if (test_b > test_a) {   // if the second number is bigger than the one before
					                     //  than we replace them 
					int a = test_a;
					test_a = test_b;
					test_b = a;
				}

				maxnumber = test_a + maxnumber * 10;  // building the max number
				test_a = test_b;
			}
            
			if (i < count - 2) {
				int local = maxnumber;
				maxnumber = 0;

				for (int k = 0; k < count; k++) {
					int temp1 = local % 10;
					local /= 10;
					maxnumber = temp1 + maxnumber * 10;

				}
				testnumber = maxnumber;
				maxnumber = 0;
			}
		}

		System.out.println("The max number is: " + maxnumber);
        // building the small number
		boolean flag = false;
		for (int m = 0; m < count; m++) {
			int temp1 = maxnumber % 10;
			if (temp1 == 0) {
				flag = true;
			}
			maxnumber /= 10;
			if (m == 2 && flag) {
				minnumber = temp1 + minnumber * 100;
			} else {
				minnumber = temp1 + minnumber * 10;

			}
		}
		System.out.println("The min number is: " + minnumber);

	}
}
