package application;

import java.lang.IllegalArgumentException;


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
	public void setCellValue(int x, int y, int value) throws IllegalArgumentException {
		
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("The value of x and y must be positive.");
		
		if (value < -1 || value > 1 || value == 0)
			throw new IllegalArgumentException("The value of the cell must be -1 or 1.");
		
		grid[x][y] = value;
		
	}
	
}
