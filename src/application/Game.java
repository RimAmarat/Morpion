package application;


public class Game {

	private int[][] grid;
	
	public Game() {
		
		// creates the game grid
		// init each cell to 0
		grid = new int[3][3];
		
		for (int i=0; i < 3; i++)
			for (int j=0; j < 3; j++)
				grid[i][j] = 0;
		
	}
	
}
