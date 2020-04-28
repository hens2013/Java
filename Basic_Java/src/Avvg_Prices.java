import java.util.Scanner;

class Avvg_Prices {

	
	// Hen Shiryon
	// id 208469718
	// Ex 1
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double Markting_Precent = 0.15;
		final double Packing_Precent = 0.05;
		final double Manufacturer_Cost = 0.8;
		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter the price (sould be different of 0");
		int price1 = scanner.nextInt();
		int price2 = scanner.nextInt();
		int price3 = scanner.nextInt();
		int price4 = scanner.nextInt();
		int price5 = scanner.nextInt();
		double avg = 0;
		System.out.println("enter the markting price");
		int marking_price = scanner.nextInt();
		System.out.println("enter the packing price");
		int packing_price = scanner.nextInt();
	
		int count=0;
		
		
		//checking that there is no two zero prices
		if (price1 == 0) {
			count++;
		}
		if (price2 == 0) {
			count++;
		}
		if (price3 == 0) {
			count++;
		}
		if (price4 == 0) {
			count++;
		}
		if (price5 == 0) {
			count++;
		}
		if (count >2) {
			System.out.println("Error in input, You should enter minimum 3 prices\r\n" + "Bye Bye");
			
		}
		if(price1<0||price2<0||price3<0||price4<0||price5<0||marking_price<0||packing_price<0)
		{
			System.out.println("Error in input, You should enter minimum 3 prices\r\n" + "Bye Bye");
		}
		
		
		else {// sorting the price from the smallest to the highest
				// while price 5 gets the highest and price1 gets the smallest
			int temp = 0;
			for (int i = 0; i < 4; i++) {
				if (price1 < price2) {
					temp = price2;
					price2 = price1;
					price1 = temp;
				}
				if (price2 < price3) {
					temp = price2;
					price2 = price3;
					price3 = temp;
				}
				if (price3 < price4) {
					temp = price3;
					price3 = price4;
					price4 = temp;
				}
				if (price4 < price5) {

					temp = price4;
					price4 = price5;
					price5 = temp;
				}

			}
			
			// checking the average price
			if (price4 == 0) {
				avg = price2;
			}
			else if (price5 == 0) {
				avg = (price2 + price3) / 2;
			} else {
				avg = (price2 + price3 + price4) / 3;
			}
			if (marking_price == 0 && packing_price == 0) {
				avg = avg;
			}
			else if (marking_price != 0 && packing_price == 0) {

				avg = avg* Manufacturer_Cost  +marking_price * Markting_Precent + avg * Packing_Precent;
			}
			else if (marking_price == 0 && packing_price != 0) {

				avg = avg* Manufacturer_Cost +avg * Markting_Precent + packing_price * Packing_Precent;
			}
			else {
				
				avg=avg* Manufacturer_Cost  +marking_price * Markting_Precent + packing_price * Packing_Precent;
			}
			System.out.println("Total price is:"+avg);
			System.out.println("bye bye");
			
			

		}

	}

}
