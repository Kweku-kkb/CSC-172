/*
 * Kweku Ofori
 */
import java.util.Random;

public class Gameboard {
	int[][] gameBoard; 
	int score;
	int validMoves;

	public int[][] getGameBoard() {
		return gameBoard;
	}
	
	//gets score from 
	public int getScore() {
		return this.score;
	}
	
	//constructor with implementation
	public Gameboard(int score, int[][] board) {
		this.score = score;
		this.gameBoard = board;

		//populates game board initially with two numbers at random locations of the 4*4 matrix
		int num1 = randomNumber();
		int num2 = randomNumber();
		int pos1 = randomPositionGenerator();
		int pos2 = randomPositionGenerator();

		// randomly populates game board with no entries 
		while (this.getGameBoard()[pos1][pos2] != 0) {
			pos1 = randomPositionGenerator();
			pos2 = randomPositionGenerator();
		}

		this.getGameBoard()[pos1][pos2] = num1;
		pos1 = randomPositionGenerator();
		pos2 = randomPositionGenerator();

		while (this.getGameBoard()[pos1][pos2] != 0) {
			pos1 = randomPositionGenerator();
			pos2 = randomPositionGenerator();
		}

		this.getGameBoard()[pos1][pos2] = num2;
	}
	
	//function returns the max value at in 4*4 2D array
	public int calculateScore() {
		int maxValue = getGameBoard()[0][0];
		for(int i = 0; i < this.getGameBoard().length; i++) {
			for(int j = 0; j < this.getGameBoard().length; j++) {
				if(getGameBoard()[i][j] > maxValue)
					maxValue = getGameBoard()[i][j];
			}
		}
		return maxValue;
	}

	//function generates random position for random numbers
	public int randomPositionGenerator() {
		Random rand = new Random();
		return rand.nextInt(4);
	}

	/*
	 * Generates 2 and 4 randomly with 80% and 20% chance of occurrence
	 */
	public int randomNumber() {
		return (Math.random() >= 0.8 ? 4 : 2);
	}

	//prints game board populating it with * initially aligned 
	public void printGameBoard() {
		for (int i = 0; i < this.getGameBoard().length; i++) {
			for (int j = 0; j < this.getGameBoard()[i].length; j++) {
				if (this.getGameBoard()[i][j] != 0) {
					System.out.print(this.getGameBoard()[i][j]);
					System.out.print("\t");

				} else {
					System.out.print('*');
					System.out.print("\t");
				}

			}
			System.out.println();
		}
	}

	//function moves and sums identical numbers to the right of the of the 2D array
	public void sumRight(int[][] array) {
		for (int row = 0; row < array.length; row++) {
			int countColumn = array[row].length - 1;
			int previous = 0;
			for (int column = array[row].length - 1; column >= 0; column--) {
				if (array[row][column] != 0) {
					if (previous == 0) {
						previous = array[row][column];
						array[row][column] = 0;
					} else {
						if (previous == array[row][column]) {
							array[row][countColumn] = array[row][column] * 2;
							array[row][column] = 0;
							countColumn--;
							previous = 0;
						} else {
							array[row][countColumn] = previous;
							countColumn--;
							previous = array[row][column];
							array[row][column] = 0;
						}
					}
				}
			}
			if (previous != 0) {
				array[row][countColumn] = previous;
			}
		}
	}
	
	//function moves and sums identical numbers to the left of the of the 2D array
	public void sumLeft(int[][] array) {
		for (int row = 0; row < array.length; row++) {
			int countColumn = 0;
			int previous = 0;
			for (int column = 0; column < array[row].length; column++) {
				if (array[row][column] != 0) {
					if (previous == 0) {
						previous = array[row][column];
						array[row][column] = 0;
					} else {
						if (previous == array[row][column]) {
							array[row][countColumn] = array[row][column] * 2;
							array[row][column] = 0;
							countColumn++;
							previous = 0;
						} else {
							array[row][countColumn] = previous;
							countColumn++;
							previous = array[row][column];
						}
					}
				}
			}
			if (previous != 0) {
				array[row][countColumn] = previous;
			}
		}
	}

	//function moves and sums identical numbers to the top of the of the 2D array
	public void sumUp(int[][] array) {
		for (int column = 0; column < array[0].length; column++) {
			int countRow = 0;
			int previous = 0;
			for (int row = 0; row < array.length; row++) {
				if (array[row][column] != 0) {
					if (previous == 0) {

						previous = array[row][column];
						array[row][column] = 0;
					} else {
						if (previous == array[row][column]) {

							array[countRow][column] = array[row][column] * 2;

							array[row][column] = 0;
							countRow++;
							previous = 0;
						} else {
							array[countRow][column] = previous;
							countRow++;
							previous = array[row][column];
							array[row][column] = 0;
						}
					}
				}
			}
			if (previous != 0) {
				array[countRow][column] = previous;
			}
		}
	}

	//function moves and sums identical numbers to the bottom of the of the 2D array
	public void sumDown(int[][] array) {
		for (int column = 0; column < array[0].length; column++) {
			int countRow = array[0].length - 1;
			int previous = 0;
			for (int row = array.length - 1; row >= 0; row--) {
				if (array[row][column] != 0) {
					if (previous == 0) {
						previous = array[row][column];
						array[row][column] = 0;
					} else {
						if (previous == array[row][column]) {
							array[countRow][column] = array[row][column] * 2;
							array[row][column] = 0;
							countRow--;
							previous = 0;
						} else {
							array[countRow][column] = previous;
							countRow--;
							previous = array[row][column];
							array[row][column] = 0;
						}
					}
				}
			}
			if (previous != 0) {
				array[countRow][column] = previous;
			}
		}
	}
	
	
	//returns true after checking for empty spot on board
	public boolean isBoardFull() {
		//checks if board is empty
		for(int i = 0; i < this.getGameBoard().length; i++) {
			for (int j = 0 ; j < this.getGameBoard()[i].length; j++) {
				if(this.getGameBoard()[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	//ends the game on 2 conditions, 2048 score or no valid move
	public boolean quitGame() {
		if(this.getScore() == 2048 || isBoardFull()) {
			return true;
		}
		
		return false;
				
	}

	//prints a new board after a valid input is entered
	public void updateBoard(char key) {
		// sum left
		if (key == 'a') {
			sumLeft(this.getGameBoard());
		}
		// summing right
		else if (key == 'd') {
			sumRight(this.getGameBoard());
		}
		// summing up
		else if (key == 'w') {
			sumUp(this.getGameBoard());
		}
		// summing down
		else if (key == 's') {
			sumDown(this.getGameBoard());
		}
		
		//populates 2D array with 2 or 4 at a random position of the array
		int num1 = randomNumber();
		int pos1 = randomPositionGenerator();
		int pos2 = randomPositionGenerator();
		
		while (this.getGameBoard()[pos1][pos2] != 0) {
			pos1 = randomPositionGenerator();
			pos2 = randomPositionGenerator();
		}
		this.getGameBoard()[pos1][pos2] = num1;
		score = this.calculateScore();
		System.out.println();
		validMoves += 1; // number of valid moves
	}	
}
