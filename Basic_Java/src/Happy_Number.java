
class Happy_Number {

	// Hen Shiryon
	// id 208469718
	// Ex 4
	public static void main(String[] args) {

		// the first number in the
		int num = 10;
		int happy_number1 = 0;
		int happy_number2 = 0;
		int happy_number3 = 0;
		int count = 0;

		int test_num = 0;
		int position_on_the_number_axis = 1;
		// running until there is there happy number
		while (count < 3) {

			test_num = num;
// calculating the digits of the number
			while (test_num > 9) {
				int value = 0;
				while (test_num > 0) {
					int b = test_num % 10;
					value += b * b;
					test_num /= 10;
				}
				test_num = value;
			}
			// checking if the number has already got to 1
			if (test_num == 1) {

				System.out.println(position_on_the_number_axis + ")" + num + " is a happy number");
				count++;
				position_on_the_number_axis++;
			} else {
				count = 0;
			}
			if (count == 1) {
				happy_number1 = num;
			} else if (count == 2) {
				happy_number2 = num;
			} else if (count == 3) {
				happy_number3 = num;
			}
			num++;

		}
		System.out.print("The three numbers are:" + happy_number1 + "," + happy_number2 + "," + happy_number3);
	}

}
