package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ai.Coup;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

import ai.MultiLayerPerceptron;
import util.Utils;


public class ControllerGame implements Initializable {
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	private ImageView playerTurnIcon = new ImageView();
	
	public static boolean isAgainstAi = false;
	
	private Game game;
	private int turn;
	public static MultiLayerPerceptron model = null;
	private int coups;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		game = new Game();
		turn = -1;  // player X engages first
		coups = 0;
		
		// get the gridpane nodes
		ObservableList<Node> gridPaneChildren = gameGrid.getChildren();
		
		for (Node node : gridPaneChildren) {
			  node.setOnMouseClicked(e -> {
				  
				  Utils utils = new Utils();
				  
				  Optional<Integer> winner = Optional.empty();
				  
				  if (game.isOver()) return;
				  
				  Integer col = gameGrid.getColumnIndex(node);
				  Integer row = gameGrid.getRowIndex(node);
				  
				  // k is the index in both the grid and ai output
				  int k = 0;
				  
				  if (col == null)
					  col = 0;
				  
				  if (row == null)
					  row = 0;
				  
				  // check if the case if available
				  if (!game.caseAvailable(row, col)) return;
				  
				  String path = "./assets/";
				  String imageUrl = "unown_x.png";
				  
				  // if it's player X turn
				  if (turn == -1)
					  imageUrl = "unown_x.png";
				  
				  // if it's player O turn
				  else
					  imageUrl = "unown_o.png";
				  
				  ImageView nodeImage = (ImageView) node;
				  Image cellImage = new Image(path+imageUrl);
				  
				  // updates the cell's image
				  nodeImage.setImage(cellImage);
				  
				  // transition to display image (fade)
				  FadeTransition transition = new FadeTransition(Duration.millis(250), nodeImage);
				  
				  transition.setFromValue(0.0);
				  transition.setToValue(1.0);
				  transition.play();
				  
				  winner = game.setCellValue(row, col, turn);
				  coups++;
				  
				  if (ControllerGame.isAgainstAi) {
					  
					  // Calls the ai to play
					  // The indexes the grid go from 0 to 9
					  // k is the index in both the grid and ai output
					  k = aiPlay();
					  turn = 1;
					  col = k % 3;
					  row = (k - col) / 3;
					  System.out.println("player o -> "+turn+" coordinates "+row+", "+col);
					  
					  // fills the cell in the game
					  winner = game.setCellValue(row, col, turn);
					  imageUrl = "unown_o.png";
					  
					  // updates the cell's image
					  nodeImage = (ImageView) gameGrid.getChildren().get(k);
					  nodeImage.setImage(new Image(path+"unown_o.png"));
				  }
				  
				  // check winner
				  if (!winner.isEmpty()) {
					  
					  ControllerGameWin.winner = winner.get();

					  utils.switchView("../views/ViewGameWin.fxml");
					  
					  game.setGameOver(true);
					  
				  }
				  
				  // check draw
				  if (winner.isEmpty() && coups == 9) {
						
					  utils.switchView("../views/ViewGameDraw.fxml");
					  return;
					  
				  }
				  
				  // player O turn
				  if (turn == -1) {
					  
					  playerTurnIcon.setImage(new Image(path+"unown_o.png"));
					  turn += 2;
					  
				  }
				  
				  // player X turn
				  else {
					
					  playerTurnIcon.setImage(new Image(path+"unown_x.png"));
					  turn -= 2;
					  
				  }
				  
			  }
			  
			);
			  
		  }
		
	}
	
	/**
	 * Computes and return the AI's next move
	 * 
	 */	
	public int aiPlay() {
		double[] input = new double[9];
		int k = 0;
		// Gets the game grid and converts it to a 1 array
		int[][] gridTable = game.getGrid();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				input[k] = gridTable[i][j];
				System.out.println("input["+k+"] = "+input[k]);
				k++;
			}
		}
		
		// Computes move probabilities
		Coup coup = new Coup(9, "game");
		coup.in = input;
		// Output of forwardprop : an array of scores given to each case
		coup.out = ControllerGame.model.forwardPropagation(coup.in);
		
		double[][] output = new double[3][3];
		k = 0;
		int prochain_coup = 0;
		
		// Gets to the first available case (that it can play) 
		while(input[k] != 0.0 && k < 8)
			k++;
		
		// Goes through all possible cases and gets the one with the highest output score
		prochain_coup = k;
		if(k < 9) k++;
		while(k < 9) {
			if(coup.out[k] > coup.out[prochain_coup] && input[k] == 0.0) {
				prochain_coup = k;
			}
			k++;
		}

		return prochain_coup;
	}
	
	/**
	 * Leads the user to the main menu
	 * 
	 * @param event - the triggered event
	 */
	public void switchToMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}
	
	/**
	 * Loads the settings view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToSettings(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewSettings.fxml");
		
	}
	
	/**
	 * Loads the model display view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToModelDisplay(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewModelDisplay.fxml");
		
	}

}
