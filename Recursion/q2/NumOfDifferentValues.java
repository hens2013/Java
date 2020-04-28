package q2;
/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */
import java.util.Arrays;

public class NumOfDifferentValues {

	public static int numOfDifferentValues(int[] arr, int size) {
		if (size == 0) {
			return 0;
		}
		int tempNum = arr[size - 1];
		int counter = 1;
		for (int i = 0; i < arr.length - 1; i++) {
			if (tempNum == arr[i]) {
				counter++;
			}
		}
		int[] newArray = new int[size - counter];
		int addedCounter = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (tempNum != arr[i]) {
				newArray[addedCounter] = arr[i];
				addedCounter++;
			}
		}
		return numOfDifferentValues(newArray, newArray.length) + 1;
	}

	public static void main(String[] args) {
		int[] arr = new int[5];

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < arr.length; i++)
				arr[i] = (int) (Math.random() * 10);
		
			System.out.println(Arrays.toString(arr) + " --> " + numOfDifferentValues(arr, arr.length));
		}

	}

}
