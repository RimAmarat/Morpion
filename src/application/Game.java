package application;

import java.lang.IllegalArgumentException;

import java.util.Optional;


public class Game {

	private int[][] grid;
	
	public void test() {
		
		try {
			
			setCellValue(-1, 2, 8);
			
		} catch (IllegalArgumentException exception) {
			
			System.out.println(exception.getMessage());
			
		}
		
	}
	
	/**
	 * Creates an instance of Game and sets the game grid
	 */
	public Game() {
		
		// creates the game grid
		// init each cell to 0
		grid = new int[3][3];
		
		for (int i=0; i < 3; i++)
			for (int j=0; j < 3; j++)
				grid[i][j] = 0;
		
	}
	
	/**
	 * Sets the cell value
	 * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param value - the value to put in the cell
	 */
	public Optional<Integer> setCellValue(int x, int y, int value) throws IllegalArgumentException {
		
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("The value of x and y must be positive.");
		
		if (value < -1 || value > 1 || value == 0)
			throw new IllegalArgumentException("The value of the cell must be -1 or 1.");
		
		grid[x][y] = value;
		
		return checkWin();
		
	}
	
	/**
	 * Checks if there is a winner and returns the id of the winner
	 * 
	 * Returns 1 for the X and -1 for the O
	 */
	public Optional<Integer> checkWin() {
		
		Optional<Integer> winner = Optional.empty();

		int countX = 0;
		int countO = 0;
		
		// test rows
		for (int i=0; i < 3; i++) {
			
			for (int j=0; j < 3; j++) {
				
				// adds point to X player
				if (grid[i][j] == -1)
					countX++;
				
				// adds point to O player
				else if (grid[i][j] == 1)
					countO++;
				
			}
			
			// if player X has enough points
			if (countX == 3) {
				
				// sets player X as winner
				winner = Optional.of(-1);
				break;
				
			} else if (countO == 3) {
				
				winner = Optional.of(1);
				break;
				
			}
			
			// go to nex row
			countX = 0;
			countO = 0;
			
		}
		
		// test columns
		for (int i=0; i < 3; i++) {
			
			for (int j=0; j < 3; j++) {
				
				if (grid[j][i] == -1)
					countX++;
				
				else if (grid[j][i] == 1)
					countO++;
				
			}
			
			if (countX == 3) {
				
				winner = Optional.of(-1);
				break;
				
			} else if (countO == 3) {
				
				winner = Optional.of(1);
				break;
				
			}
			
			countX = 0;
			countO = 0;
			
		}
		
		// test diag right to left
		int j = 0;
		for (int i=2; i >= 0; i--) {
			
			if (grid[j][i] == -1)
				countX++;
			
			else if (grid[j][i] == 1)
				countO++;
			
			j++;
			
		}
		
		if (countX == 3)
			winner = Optional.of(-1);
		
		else if (countO == 3)
			winner = Optional.of(1);
		
		// test diag left to right
		j = 0;
		for (int i=0; i > 3; i++) {
			
			if (grid[i][j] == -1)
				countX++;
			
			else if (grid[i][j] == 1)
				countO++;
			
		}
		
		if (countX == 3)
			winner = Optional.of(-1);
		
		else if (countO == 3)
			winner = Optional.of(1);
		
		return winner;
		
	}
	
}
