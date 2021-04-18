Name: kweku Ofori
Lab: Moon/Wen 9:00AM
Partners: None
CSC 172 Project 1

I had two(Gameboard and Project1 ) classes and used OOP in the implementation of my text based 2048 game using only keys q,r,a,s,d and w as inputs

**Gameboard class**
This class contains the backbone of the project containing all the important functions that sums arranges and adds similar numbers in the 4*4 2D array. This class also contains the implementation of important functions such as randomPositionGenerator which populates the randomly generated ints to empty spaces in the 2D array anytime the game is started and continued. The summing function basically adds values of a kind either to the left, right, up or down the matrix whenever there is a user input using a lot of nested loops. The is the isBoardFull function which checks to see if there are more valid moves available before the board is populated with the randomly generated int values. There is also the updateBoard function which always prints a new game board whenever the user enters a valid key during game play. 

**Project1 class**
This class contains the user interface which prints the information and gets the user input and does the rest of the operations from the GameBoard class. 

