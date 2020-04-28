import java.util.Scanner;

//name: Hen Shiryon;
//id: 208469418;
//drill: Murble_Puzzle;

class Drill1 {

	public static void main(String[] args) {
		playMaze();

	}

	public static void playMaze() {

		Scanner scanner = new Scanner(System.in);

		char[][] pathMatrix = Building_maze(scanner, inputNumberOfRows(scanner), inputNumberOfColumns(scanner));

		char[][] matrix = copyMatrix(pathMatrix);

		final char DOWN = 'V';
		final char UP = '^';
		final char LEFT = '<';
		final char RIGHT = '>';
		final char CROSS_SECTION = '+';
		final char ALREADY_BEEN_HERE = '!';
		char MINE = '*';
		final int NUMBER_OF_COLUMNS = matrix[0].length;
		final int NUMBER_OF_ROWS = matrix.length;

		// input for life
		int lifeBank = Disqualifications_in_the_game(scanner);

		// getting the entrance for the maze
		int[] mazeEntrance = getMazeEntrance(pathMatrix);

		// putting the entrance point in parameters
		int row_entrance = mazeEntrance[0];
		int column_entrance = mazeEntrance[1];
		String route = "(" + row_entrance + "," + column_entrance + ") -> ";

		System.out.println("Starting at (" + row_entrance + "," + column_entrance + ") with " + lifeBank + " lives...");

		boolean EXIT_ISV = false;
		char cell_now;

		do {
			cell_now = matrix[row_entrance][column_entrance];
			if (Checking_step(cell_now)) {
				matrix[row_entrance][column_entrance] = ALREADY_BEEN_HERE;
				route = route.concat("(" + row_entrance + "," + column_entrance + ") -> ");
			}

			switch (cell_now) {
			case DOWN:
				if (row_entrance == NUMBER_OF_ROWS - 1) {
					System.out.println("Thanks god, we are out, good job…");
					EXIT_ISV = true;
					break;
				}
				System.out.println("Moving down");
				row_entrance++;
				break;

			case UP:
				if (row_entrance == 0) {
					System.out.println("Thanks god, we are out, good job…");
					EXIT_ISV = true;
					break;
				}
				System.out.println("Moving up");
				row_entrance--;
				break;

			case LEFT:
				if (column_entrance == 0) {
					EXIT_ISV = true;
					System.out.println("Thanks god, we are out, good job…");
					break;
				}
				System.out.println("Moving left");
				column_entrance--;
				break;

			case RIGHT:
				if (column_entrance == NUMBER_OF_COLUMNS - 1) {
					System.out.println("Thanks god, we are out, good job…");
					EXIT_ISV = true;
					break;
				}
				System.out.println("Moving right");
				column_entrance++;
				break;

			case CROSS_SECTION: // clock direction

				System.out.println("I have reached a junction");
				System.out.println("I'm going to check the area...");

				// check right
				if ((column_entrance != NUMBER_OF_COLUMNS - 1)) {
					System.out.printf("Looking to (%d,%d)\n", row_entrance, column_entrance + 1);
					cell_now = matrix[row_entrance][column_entrance + 1];
					if (Checking_step(cell_now)) {
						System.out.println("CLEAR!!!");
						System.out.println("Let's go...");
						column_entrance++;

						break;
					} else if (cell_now == MINE) {
						if (lifeBank == 0) {
							char decision = User_decision(scanner);
							switch (decision) {
							case 'n':
								System.out.println("You lose because you are a CHICKEN!");
								EXIT_ISV = true;
								break;

							case DOWN:
								if (row_entrance == NUMBER_OF_ROWS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance + 1][column_entrance] != MINE) {
									System.out.println("Moving down");
									row_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case UP:
								if (row_entrance == 0) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance - 1][column_entrance] != MINE) {
									System.out.println("Moving up");
									row_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case LEFT:
								if (column_entrance == 0) {
									EXIT_ISV = true;
									System.out.println("You Fall Off The Maze. Game Over!");
									break;
								} else if (matrix[row_entrance][column_entrance - 1] != MINE) {
									System.out.println("Moving left");
									column_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case RIGHT:
								if (column_entrance == NUMBER_OF_COLUMNS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance][column_entrance + 1] != MINE) {
									System.out.println("Moving right");
									column_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									lifeBank--;
									EXIT_ISV = true;
									break;
								}
							}

							break;
						} else {
							System.out.println("BOOOOOM!!!");
							lifeBank--;
							System.out.printf("One life is gone, %d more left...\n", lifeBank);
						}
					}
				} else {
					EXIT_ISV = true;
					System.out.println("You Fall Off The Maze. Game Over!");
					break;
				}
				// check down
				if (row_entrance != NUMBER_OF_ROWS - 1) {
					System.out.printf("Looking to (%d,%d)\n", row_entrance + 1, column_entrance);
					cell_now = matrix[row_entrance + 1][column_entrance];
					if (Checking_step(cell_now)) {
						System.out.println("CLEAR!!!");
						System.out.println("Let's go...");
						row_entrance++;

						break;

					} else if (cell_now == MINE) {
						if (lifeBank == 0) {
							char decision = User_decision(scanner);
							switch (decision) {
							case 'n':
								System.out.println("You lose because you are a CHIKEN!");
								EXIT_ISV = true;
								break;

							case DOWN:
								if (row_entrance == NUMBER_OF_ROWS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance + 1][column_entrance] != MINE) {
									System.out.println("Moving down");
									row_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case UP:
								if (row_entrance == 0) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance - 1][column_entrance] != MINE) {
									System.out.println("Moving up");
									row_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case LEFT:
								if (column_entrance == 0) {
									EXIT_ISV = true;
									System.out.println("You Fall Off The Maze. Game Over!");
									break;
								} else if (matrix[row_entrance][column_entrance - 1] != MINE) {
									System.out.println("Moving left");
									column_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case RIGHT:
								if (column_entrance == NUMBER_OF_COLUMNS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance][column_entrance + 1] != MINE) {
									System.out.println("Moving right");
									column_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									lifeBank--;
									EXIT_ISV = true;
									break;
								}
							}

							break;
						} else {
							System.out.println("BOOOOOM!!!");
							lifeBank--;
							System.out.printf("One life is gone, %d more left...\n", lifeBank);
						}
					}
				} else {
					EXIT_ISV = true;
					System.out.println("You Fall Off The Maze. Game Over!");
					break;
				}
				// check left
				if (column_entrance != 0) {
					System.out.printf("Looking to (%d,%d)\n", row_entrance, column_entrance - 1);
					cell_now = matrix[row_entrance][column_entrance - 1];
					if (Checking_step(cell_now)) {
						System.out.println("CLEAR!!!");
						System.out.println("Let's go...");
						column_entrance--;

						break;
					} else if (cell_now == MINE) {
						if (lifeBank == 0) {
							char decision = User_decision(scanner);
							switch (decision) {
							case 'n':
								System.out.println("You lose because you are a CHIKEN!");
								EXIT_ISV = true;
								break;

							case DOWN:
								if (row_entrance == NUMBER_OF_ROWS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance + 1][column_entrance] != MINE) {
									System.out.println("Moving down");
									row_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case UP:
								if (row_entrance == 0) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance - 1][column_entrance] != MINE) {
									System.out.println("Moving up");
									row_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case LEFT:
								if (column_entrance == 0) {
									EXIT_ISV = true;
									System.out.println("You Fall Off The Maze. Game Over!");
									break;
								} else if (matrix[row_entrance][column_entrance - 1] != MINE) {
									System.out.println("Moving left");
									column_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case RIGHT:
								if (column_entrance == NUMBER_OF_COLUMNS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance][column_entrance + 1] != MINE) {
									System.out.println("Moving right");
									column_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									lifeBank--;
									EXIT_ISV = true;
									break;
								}
							}

							break;
						} else {
							System.out.println("BOOOOOM!!!");
							lifeBank--;
							System.out.printf("One life is gone, %d more left...\n", lifeBank);
						}

					}
				} else {
					EXIT_ISV = true;
					System.out.println("You Fall Off The Maze. Game Over!");
					break;
				}
				// check up
				if (row_entrance != 0) {
					System.out.printf("Looking to (%d,%d)\n", row_entrance - 1, column_entrance);
					cell_now = matrix[row_entrance - 1][column_entrance];
					if (Checking_step(cell_now)) {
						System.out.println("CLEAR!!!");
						System.out.println("Let's go...");
						row_entrance--;

						break;

					} else if (cell_now == MINE) {
						if (lifeBank == 0) {
							char decision = User_decision(scanner);
							switch (decision) {
							case 'n':
								System.out.println("You lose because you are a CHIKEN!");
								EXIT_ISV = true;
								break;

							case DOWN:
								if (row_entrance == NUMBER_OF_ROWS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance + 1][column_entrance] != MINE) {
									System.out.println("Moving down");
									row_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case UP:
								if (row_entrance == 0) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance - 1][column_entrance] != MINE) {
									System.out.println("Moving up");
									row_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case LEFT:
								if (column_entrance == 0) {
									EXIT_ISV = true;
									System.out.println("You Fall Off The Maze. Game Over!");
									break;
								} else if (matrix[row_entrance][column_entrance - 1] != MINE) {
									System.out.println("Moving left");
									column_entrance--;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									EXIT_ISV = true;
									lifeBank--;
									break;
								}

							case RIGHT:
								if (column_entrance == NUMBER_OF_COLUMNS - 1) {
									System.out.println("You Fall Off The Maze. Game Over!");
									EXIT_ISV = true;
									break;
								} else if (matrix[row_entrance][column_entrance + 1] != MINE) {
									System.out.println("Moving right");
									column_entrance++;
									break;
								} else {
									System.out.println("BOOOM!!! Game Over");
									lifeBank = 0;
									EXIT_ISV = true;
									break;
								}
							}

							break;
						} else {
							System.out.println("BOOOOOM!!!");
							lifeBank--;
							System.out.printf("One life is gone, %d more left...\n", lifeBank);
						}
					}
				} else {
					EXIT_ISV = true;
					System.out.println("You Fall Off The Maze. Game Over!");
					break;
				}

				break;

			default:
				EXIT_ISV = true;
				break;
			}
		} while (!EXIT_ISV);

		// PRINTS THE EXIT COORDINATES
		if (lifeBank >= 0) {
			System.out.printf("Exit on (%d,%d)\n", row_entrance, column_entrance);
		}
		// ADD EXIT COORDINATE TO ROUTE
		route = route.concat("(" + row_entrance + "," + column_entrance + ")");

		// PRINTS THE FINAL ROUTE
		System.out.println(route);

		// PRINT THE FINAL MATRIX
		printMatrixDelta(pathMatrix, matrix);

		// CLOSING RESOURCES - IMPORTANT
		scanner.close();
	}

	private static int[] getMazeEntrance(char[][] matrix) {
		// FINAL VARIABLES
		final char DOWN = 'V';
		final char UP = '^';
		final char LEFT = '<';
		final char RIGHT = '>';

		int[] entranceCoordinate = new int[2]; // 0=ROW,1=COLUMN
		int row_entrance = 0;
		int column_entrance = 0;

		int longestRouteSteps = 0;
		int row;
		int column;

		// check the first row
		row = 0;
		for (column = 0; column < matrix[row].length; column++) {
			if (matrix[row][column] == DOWN) {
				char[][] traceMatrix = Finding_trace(matrix, row, column);
				int stepCount = countSteps(traceMatrix);
				if (stepCount > longestRouteSteps) {
					longestRouteSteps = stepCount;
					row_entrance = row;
					column_entrance = column;
				}
			}
		}
		// check the first column
		column = 0;
		for (row = 0; row < matrix.length; row++) {
			if (matrix[row][column] == RIGHT) {
				char[][] traceMatrix = Finding_trace(matrix, row, column);
				int stepCount = countSteps(traceMatrix);
				if (stepCount > longestRouteSteps) {
					longestRouteSteps = stepCount;
					row_entrance = row;
					column_entrance = column;
				}
			}
		}

		// checking the last row
		row = matrix.length - 1;
		for (column = 0; column < matrix[row].length; column++) {
			if (matrix[row][column] == UP) {
				char[][] traceMatrix = Finding_trace(matrix, row, column);
				int stepCount = countSteps(traceMatrix);
				if (stepCount > longestRouteSteps) {
					longestRouteSteps = stepCount;
					row_entrance = row;
					column_entrance = column;
				}
			}
		}

		// check the last column
		column = matrix[0].length - 1;
		for (row = 0; row < matrix.length; row++) {
			if (matrix[row][column] == LEFT) {
				char[][] traceMatrix = Finding_trace(matrix, row, column);
				int stepCount = countSteps(traceMatrix);
				if (stepCount > longestRouteSteps) {
					longestRouteSteps = stepCount;
					row_entrance = row;
					column_entrance = column;
				}
			}
		}

		// putting the coordinate into array
		entranceCoordinate[0] = row_entrance;
		entranceCoordinate[1] = column_entrance;

		// CHECK IF LONGEST ROUTE EXISTS
		if (longestRouteSteps == 0) {
			System.err.println("The Matrix you entered is not playable.");
		}

		return entranceCoordinate;
	}

	// this method counting the steps in the route
	private static int countSteps(char[][] matrix) {
		final char ALREADY_BEEN_HERE = '!';
		int stepCounter = 0;
		for (char[] chars : matrix) {
			for (int column = 0; column < chars.length; column++) {
				if (chars[column] == ALREADY_BEEN_HERE) {
					stepCounter++;
				}
			}
		}
		return stepCounter;
	}

	// this method if for finding trace in the matrix
	private static char[][] Finding_trace(char[][] pathMatrix, int row_entrance, int column_entrance) {
		char[][] matrix = copyMatrix(pathMatrix);
		final char DOWN = 'V';
		final char UP = '^';
		final char LEFT = '<';
		final char RIGHT = '>';
		final char CROSS_SECTION = '+';
		final char ALREADY_BEEN_HERE = '!';
		char GRASS = 'O';
		char MINE = '*';
		final int NUMBER_OF_COLUMNS = matrix[0].length;
		final int NUMBER_OF_ROWS = matrix.length;

		boolean EXIT_ISV = false;
		char cell_now;

		do {
			cell_now = matrix[row_entrance][column_entrance];
			if (Checking_step(cell_now)) {
				matrix[row_entrance][column_entrance] = ALREADY_BEEN_HERE;
			}

			switch (cell_now) {
			case DOWN:
				if (row_entrance == NUMBER_OF_ROWS - 1) {
					EXIT_ISV = true;
					break;
				}
				row_entrance++;
				break;

			case UP:
				if (row_entrance == 0) {
					EXIT_ISV = true;
					break;
				}
				row_entrance--;
				break;

			case LEFT:
				if (column_entrance == 0) {
					EXIT_ISV = true;
					break;
				}
				column_entrance--;
				break;

			case RIGHT:
				if (column_entrance == NUMBER_OF_COLUMNS - 1) {
					EXIT_ISV = true;
					break;
				}
				column_entrance++;
				break;

			case CROSS_SECTION: // clock direction

				// check right
				if ((column_entrance != NUMBER_OF_COLUMNS - 1)) {
					cell_now = matrix[row_entrance][column_entrance + 1];
					if ((cell_now != GRASS) && (cell_now != MINE) && (cell_now != ALREADY_BEEN_HERE)) {
						column_entrance++;
						break;
					}
				} else {
					EXIT_ISV = true;
					break;
				}
				// check down
				if (row_entrance != NUMBER_OF_ROWS - 1) {
					cell_now = matrix[row_entrance + 1][column_entrance];
					if ((cell_now != GRASS) && (cell_now != MINE) && (cell_now != ALREADY_BEEN_HERE)) {
						row_entrance++;
						break;
					}
				} else {
					EXIT_ISV = true;
					break;
				}
				// check left
				if (column_entrance != 0) {
					cell_now = matrix[row_entrance][column_entrance - 1];
					if ((cell_now != GRASS) && (cell_now != MINE) && (cell_now != ALREADY_BEEN_HERE)) {
						column_entrance--;
						break;
					}
				} else {
					EXIT_ISV = true;
					break;
				}
				// check up
				if (row_entrance != 0) {
					cell_now = matrix[row_entrance - 1][column_entrance];
					if ((cell_now != GRASS) && (cell_now != MINE) && (cell_now != ALREADY_BEEN_HERE)) {
						row_entrance--;
						break;
					}
				} else {
					EXIT_ISV = true;
					break;
				}

				break;
			case ALREADY_BEEN_HERE:
				// ANTI SELF LOOPING VALIDATION
				return pathMatrix;

			default:
				EXIT_ISV = true;
				break;
			}
		} while (!EXIT_ISV);

		return matrix;
	}

	// printing the matrix
	private static void printMatrixDelta(char[][] pathMatrix, char[][] matrix) {
		// FINAL COLOR VARIABLES
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_RESET = "\u001B[0m";

		// PRINT OUT A MATRIX - WITH CONSIDERATION TO THE DELTA
		System.out.println();
		for (int row = 0; row < matrix.length; row++) {
			System.out.print("|");
			for (int column = 0; column < matrix[0].length; column++) {

				if (matrix[row][column] != pathMatrix[row][column]) {
					// DELTA
					System.out.print(ANSI_RED + pathMatrix[row][column] + ANSI_RESET);
				} else {
					// NO DELTA
					System.out.print(pathMatrix[row][column]);
				}
				if (column < matrix[0].length - 1) {
					// SPACING
					System.out.print(",");
				}
			}
			System.out.println("|");
		}
	}

	private static char User_decision(Scanner scanner) {

		char decision;

		do {
			System.out.println("Will you take a chance to guess a direction?");
			System.out.println("n for no");
			System.out.println("V for down");
			System.out.println("^ for up");
			System.out.println("< for left");
			System.out.println("> for right");

			decision = scanner.next().charAt(0);
		} while ((decision != 'n') && (decision != 'V') && (decision != '^') && (decision != '>') && (decision != '<'));

		return decision;
	}

	// checking step method
	private static boolean Checking_step(char cell_now) {

		return (cell_now != 'O') && (cell_now != '*') && (cell_now != '!');
	}

	private static char[][] copyMatrix(char[][] matrix) {
		char[][] copy = new char[matrix.length][matrix[0].length];

		for (int row = 0; row < copy.length; row++) {
			for (int column = 0; column < copy[0].length; column++) {
				char content = matrix[row][column];
				copy[row][column] = content;
			}
		}

		return copy;
	}

	private static char[][] Building_maze(Scanner scanner, int rows, int columns) {

		final char DOWN = 'V';
		final char UP = '^';
		final char LEFT = '<';
		final char RIGHT = '>';
		final char CROSS_SECTION = '+';
		final char GRASS = 'O';
		final char MINE = '*';

		char[][] pathMatrix = new char[rows][columns];

		char cell;

		// FLAG KEY FOR VALID INPUT
		boolean valid;

		for (int row = 0; row < pathMatrix.length; row++) {
			for (int column = 0; column < pathMatrix[row].length; column++) {
				do {
					// FLAG KEY FOR VALID INPUT
					valid = false;

					System.out.printf("Please Provide A Valid Char For Cell (%d,%d):", column, row);
					cell = scanner.next().charAt(0);
					// CHECK VALID INPUT BEFORE ASSIGNING
					if (cell == UP || cell == DOWN || cell == RIGHT || cell == LEFT || cell == GRASS || cell == MINE
							|| cell == CROSS_SECTION) {
						valid = true;
					}
					pathMatrix[row][column] = cell;
				} while (!valid);
			}
		}

		return pathMatrix;
	}

	// getting the life from the user
	private static int Disqualifications_in_the_game(Scanner scanner) {
		int life = -1;
		while (life < 0 || life > 100) {
			System.out.println("please enter life between 1 to 100");
			life = scanner.nextInt();
		}

		return life;
	}

	// getting the number of the columns
	private static int inputNumberOfColumns(Scanner scanner) {
		  int numberOfColumns;
	        do {
	            System.out.println("Please Enter A Number Of Columns Between 5 to 15:");
	            numberOfColumns = scanner.nextInt();
	        }
	        while ((numberOfColumns < 0) || (numberOfColumns > 15));

	        return numberOfColumns;

	}

	// getting the number of the rows
	private static int inputNumberOfRows(Scanner scanner) {

		  int numberOfRows;
	        do {
	            System.out.println("Please Enter A Number Of COLUM Between 5 to 15:");
	            numberOfRows = scanner.nextInt();
	        }
	        while ((numberOfRows < 0) || (numberOfRows > 15));

	        return numberOfRows;

	}

}
