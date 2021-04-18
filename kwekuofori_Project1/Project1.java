/*
 * Kweku Ofori
 */
import java.util.Scanner;

public class Project1 {
		
	public static void main(String[] args) {
		int [][] array = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}; // populates 2D 4*4 array with zeros initially
		
		//calling game board class
		Gameboard board = new Gameboard(0,array);
		board.printGameBoard(); // prints game board
		
		//gets user input
		Scanner user = new Scanner(System.in);
		System.out.println("\nWhich way do you want to add? Enter a,d,w,s for summing left,right,up,down respectively."
				+ "\nEnter q or r to quit and restart game respectively");
		
		//getting user input and checks for valid input
		while(user.hasNext() && !board.quitGame()) {
			String input = user.next().toLowerCase();
			char key = input.charAt(0);
			System.out.println("\nThe key pressed is " + key);
			
			if(key == 'q' || key == 'r' || key == 'a' || key == 's' || key == 'd' || key == 'w') {
				if(key == 'q') {
					System.out.println("Enter q again to confirm quit");
					input = user.next().toLowerCase();;
					key = input.charAt(0);
					
					//confirms quit key input
					if(key == 'q') {
						System.out.println("\nGame Over... Your score is " + board.getScore());
						break;// break code whenever the code is q is stopped
					}
				}
				//confirms restart key input
				else if(key == 'r') {
					System.out.println("Enter r again to confirm restart");
					input = user.next().toLowerCase();
					key = input.charAt(0);
					
					//prints many new lines whenever the game is restarted
					if(key == 'r') {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						int [][] newArray = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
						board= new Gameboard(0,newArray);
					}
				// calls prints a new game board when the appropriate key is entered	
				}else {
					board.updateBoard(key);
					board.printGameBoard();
					System.out.println();
					System.out.println("Your key makes a valid move");
					System.out.println("The number of valid moves made is " + board.validMoves);
				}
				System.out.println("Your score is " + board.getScore());
				//System.out.println("The number of valid moves made is " + board.validMoves);
				System.out.println("\nWhich way way do you want to add? Enter a,d,w,s for summing left,right,up,down respectively."
						+ "\nEnter q or r to quit and restart game respectively");				
			}else {
				System.out.println("\nWrong input!\nWhich way way do you want to add? Enter a,d,w,s for summing left,right,up,down respectively."
						+ "\nEnter q or r to quit and restart game respectively");				
				}
		}
		
		//user wins if a score of 2048 is attained and prints information about the game play
		if(board.getScore() == 2048) {
			System.out.println("\nCongratulations!!! You win!");
			System.out.println("Your score is " + board.getScore());
			//System.out.println("The number of valid moves made is " + board.validMoves);
			
			//quits game if when no valid moves are available
		}else {
			System.out.println("\nTry again next time");
			System.out.println("Your score is " + board.getScore());
			System.out.println("The number of valid moves made is " + board.validMoves);
		}
		
	}
}
