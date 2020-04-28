package q3;

/**
 * Author: Hen Shiryon
 * ID: 208469718
 * Exercise: 3
 */

public class IsDiagonalHasLettersSequence {

	public static boolean isDiagonalLettersSequence(char[][] mat, int rows)
	{
		if (rows==1) {
			return true;
		}
		  if (mat[rows-1][rows-1]-mat[rows-2][rows-2]==1) {
			  return isDiagonalLettersSequence(mat, rows-1);
		}
		  return false;
		  
	}
	

	

	public static void main(String[] args) {
		char[][] mat = new char[5][5];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++)
				mat[i][j] = (char) (int) ((Math.random() * 26) + 'a');
		}

		for (int i = 0; i < mat.length; i++)
			mat[i][i] = (char) ('a' + i);
	//	mat[2][2] = 'b';
		//mat[4][4]='y';
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
		
		System.out.println(isDiagonalLettersSequence(mat,mat.length));
	}
}

	
	


