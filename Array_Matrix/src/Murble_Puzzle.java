import java.util.Scanner;

//name: Hen Shiryon;
//id: 208469418;
//drill: Murble_Puzzle;

public class Murble_Puzzle {

	//
	// function that get the array and the index and moving left in one cabin
	public static void Shift_Left(char[] arr, int index) {
		System.out.print("SL");
		System.out.print("   ");
		Print(arr);
		char temp = arr[index - 1];
		arr[index - 1] = arr[index];
		arr[index] = temp;

	}

	// function that get the array and the index and moving right in one cabin
	public static void Shift_Right(char[] arr, int index) {

		System.out.print("SR");
		System.out.print("   ");
		Print(arr);
		char temp = arr[index + 1];
		arr[index + 1] = arr[index];
		arr[index] = temp;

	}

	// function that get the array and the index and moving right in two cabins
	public static void Jump_Right(char[] arr, int index) {

		char temp = arr[index + 2];
		arr[index + 2] = arr[index];
		arr[index] = temp;

	}

	// function that get the array and the index and moving left in two cabins
	public static void Jump_Left(char[] arr, int index) {

		char temp = arr[index - 2];
		arr[index - 2] = arr[index];
		arr[index] = temp;
		if (index == 2) {
			return;
		}

	}

	// function that get the array and and counts how many r '0' there is in
	// sequence from the beginning ………
	public static int Sequence……………_checking_0(char[] arr) {
		int count = 1;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == '0' && arr[i + 1] == '0') {
				count++;
			}
		}
		return count;
	}

	// function that get the array and and counts how many r 'x' there is in
	// sequence from the end
	public static int Sequence……………_checking_X(char[] arr) {
		int count = 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == 'x' && arr[i - 1] == 'x') {
				count++;
			}
		}
		return count;
	}

	public static void End_to_begin(char[] arr, int size, int index) {
		int jump = size;
	

		while (jump > 0) {
			System.out.print("JL");
			System.out.print("   ");
			Print(arr);
			Jump_Left(arr, index);

			index -= 2;
			jump--;
			if (index == 1) {
				return;
			}
		
		}

	}

	public static void Begin_to_End(char[] arr, int size, int index) {
		int jump = size;
		//System.out.print("JR");
		//System.out.print("   ");
		while (jump > 0) {
			System.out.print("JR");
			System.out.print("   ");
			Print(arr);
			Jump_Right(arr, index);

			index += 2;
			jump--;

			if (index == arr.length - 2) {
				return;
			}
			
		}

	}

	private static char[] Building_Puzzle(int num) {
		char[] arr = new char[num * 2 + 1];
		for (int i = 0; i < arr.length - 1; i++) {
			if (i % 2 == 0) {
				arr[i] = '0';
			} else
				arr[i] = 'x';
		}
		arr[arr.length - 1] = ' ';
		return arr;
	}

	public static void Print(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(" " + arr[i] + " |");
		}
		System.out.println();
	}

	public static void Marvel_Puzzle(char[] arr, int size) {
		int temp = size - 1;
		Shift_Left(arr, arr.length - 1);

		while (temp > 0) {

			End_to_begin(arr, temp, (arr.length - 1) - Sequence……………_checking_X(arr));

			Shift_Right(arr, Sequence……………_checking_0(arr));
			int x = temp - 1;
			Begin_to_End(arr, x, Sequence……………_checking_0(arr));
			temp -= 2;
			Shift_Left(arr, (arr.length - 1) - Sequence……………_checking_X(arr));

		}
		if (size % 2 == 0) {

			Shift_Right(arr, Sequence……………_checking_0(arr));
		}
		System.out.print("     ");
		Print(arr);

	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter ur size (it has to be positive number)");
		int num = scanner.nextInt();
		while (num<=0) {
			System.out.println("please enter ur size (it has to be positive number)");
			num=scanner.nextInt();
		}
		scanner.close();
		char[] arr = Building_Puzzle(num);
		Marvel_Puzzle(arr, num);
	}

}
