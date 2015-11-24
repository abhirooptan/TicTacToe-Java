/**
 * @file TicTacToe.java
 * @author kmurphy
 * @practical lab-07
 * @brief
 */

import java.awt.Font;

public class TicTacToe {

	public static void main(String[] args) {

		// This flag is to control the level of debug messages generated
		final boolean DEBUG = true;

		// Allocate identifiers to represent game state
		//
		// Using an array of int so that summing along a row, a column or a
		// diagonal is a easy test for a win
		final int EMPTY = 0;
		final int X_SHAPE = 1;
		final int O_SHAPE = -1;
		int[][] board = new int[3][3];

		// Setup graphics and draw empty board
		StdDraw.setPenRadius(0.04);							// draw thicker lines
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);

		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 124));

		// Game loop - repeat until win or board is full
		int move = 0;										// number of moves played
		while (move < 9) {

			if (DEBUG) System.out.println("move = " + move);

			int row = 0;									// row to place move
			int col = 0;									// column to place move

			if (move % 2 == 0) 
			{							// Computer's move
				
				if (DEBUG) System.out.println("\tComputer finding move ...");

				// strategy - first free slot found at random
				while (true) 
				{
					boolean turnTaken = false;	//variable to check if turn has been taken or not
					
					// checking each column 
					for(int i = 0; i < 3; i++)
					{
						/*
						 *  using the absolute value as it works for both because if the
						 *  sum is positive, the computer will go for the winning move
						 *  and if the sum is negative then the computer will go for the 
						 *  blocking move
						 */
						if (Math.abs(board[i][0] + board[i][1] + board[i][2]) == 2)
						{
							for(int j = 0; j < 3; j++)
							{
								if(board[i][j] == EMPTY)
								{
									// storing the empty and right position 
									row = i;
									col = j;
									break; // getting out of this loop
								}
								
							}
							turnTaken = true;
							board[row][col] = X_SHAPE;	//placing the X in position
							StdOut.println("Using AI");
							break;	//getting out of the checking columns loop
						}
					}
					
					// checking each row
					if(!turnTaken)
					{
						for(int j = 0; j < 3; j++)
						{
							// again using the absolute value to block or attack
							if (Math.abs(board[0][j] + board[1][j] + board[2][j]) == 2)
							{
								for(int i = 0; i < 3; i++)
								{
									if(board[i][j] == EMPTY)
									{
										//storing the position to attack/block
										row = i;
										col = j;
										break;	//getting out of this loop
									}
									
								}
								turnTaken = true;
								board[row][col] = X_SHAPE;	//placing the X in position
								StdOut.println("Using AI");
								break;	//getting out of the loop
							}
						}
					}
					
					// checking the diagonals
					if(!turnTaken)
					{
						// using the absolute value to check along one diagonal
						if (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 2)
						{
							for(int i = 0; i < 3; i++)
							{
								if(board[i][i] == EMPTY)
								{
									//storing position
									row = i;
									col = i;
									break;
								}
								
							}
							turnTaken = true;
							board[row][col] = X_SHAPE;
							StdOut.println("Using AI");
							break;
						}
						
						//checking the other diagonal
						if (Math.abs(board[0][2] + board[1][1] + board[2][0]) == 2)
						{
							for(int i = 0; i < 3; i++)
							{
								if(board[i][2-i] == EMPTY)
								{
									row = i;
									col = 2-i;
									break;
								}
								
							}
							turnTaken = true;
							board[row][col] = X_SHAPE;
							StdOut.println("Using AI");
							break;
						}
							
					}
					
					//using the random number generator to get a random position
					if(!turnTaken)
					{
						StdOut.println("Using Random");
						while(!turnTaken)
						{
							row = (int) (Math.random() * 3);
							col = (int) (Math.random() * 3);
							if (board[row][col] == EMPTY) 
							{			// valid move (empty slot)
								board[row][col] = X_SHAPE;
								StdOut.println("place found at " + row + " " + col);
								turnTaken = true;
							}
						}
					}
					// to break through the whole loop once computer has taken his turn
					if(turnTaken)
							break;
				}

			} 
			
			// the code below is for the human turn
			else {

				if (DEBUG) System.out.println("\tHuman move ...");

				// use mouse position to get slot
				while (true) {
					if (StdDraw.mousePressed()) {
						col = (int) (StdDraw.mouseX() * 3);						
 						row = (int) (StdDraw.mouseY() * 3);
						if (board[row][col] == EMPTY) {		// valid move (empty slot)
							board[row][col] = O_SHAPE;
							break;
						}
					}
				}
			}

			if (DEBUG) System.out.println("\tMove at row = " + row + "\t col = " + col);
			
			// output the board
			if (DEBUG) {			
				for (int r=2; r>=0; r--) {
					System.out.print("\t\t");
					for (int c=0; c<3; c++) {
						System.out.print(" " + board[r][c]);
					}
					System.out.println();
				}
			}

			// Update screen to reflect board change
			double x = col * .33 + 0.15;
			double y = row * .33 + 0.15;
			StdDraw.text(x, y, (move % 2 == 0 ? "X" : "O"));

			/*
			 * Check for win 
			 * again using the absolute value to end the game 
			 * if a wining move is played
			 * who is the winner depends on whose turn was it
			 */
			if (	   (Math.abs(board[0][0] + board[0][1] + board[0][2]) == 3)
					|| (Math.abs(board[1][0] + board[1][1] + board[1][2]) == 3)
					|| (Math.abs(board[2][0] + board[2][1] + board[2][2]) == 3)
					|| (Math.abs(board[0][0] + board[1][0] + board[2][0]) == 3)
					|| (Math.abs(board[0][1] + board[1][1] + board[2][1]) == 3)
					|| (Math.abs(board[0][2] + board[1][2] + board[2][2]) == 3)
					|| (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 3)
					|| (Math.abs(board[0][2] + board[1][1] + board[2][0]) == 3)
					)
				break;

			move++;											// next move
		}

		// Output win/lose/draw message
		StdDraw.setPenColor(StdDraw.RED);
		if (move == 9) {									// A draw
			StdDraw.text(0.5, 0.5, "A Draw");
		} else {											// last player won
			StdDraw.text(0.5, 0.5, (move % 2 == 0 ? "I" : "You") + " Win");
		}

	}

}