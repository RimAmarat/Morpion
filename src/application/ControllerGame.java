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
				  System.out.println("Coups: " + coups);
				  
				  if (ControllerGame.isAgainstAi) {
					  
					  // Calling the ai to play
					  int k = aiPlay();
					  System.out.println("k = "+k);
					  turn = 1;
					  col = k % 3;
					  row = (k - col) / 3;
					  System.out.println("player o -> "+turn+" coordinates "+row+", "+col);
					  
					  winner = game.setCellValue(row, col, turn);
					  imageUrl = "unown_o.png";
					  nodeImage = (ImageView) gameGrid.getChildren().get(k);
					  
					  // updates the cell's image
					  System.out.println("image not set yet ...");
					  nodeImage.setImage(new Image(path+"unown_o.png"));
					  System.out.println("image set");
					  
					  /*if (col == 3) col = 2;
					  if (row == 3) row = 2;
					
					  winner = game.setCellValue(row, col, turn);
					  coups++;
					  System.out.println("Coups: " + coups);
					  nodeImage = (ImageView) gameGrid.getChildren().get(k);*/
					  
					  // updates the cell's image
					  System.out.println("image not set yet ...");
					  nodeImage.setImage(new Image(path+"unown_o.png"));
					  System.out.println("image set");
					  
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
	
	public int aiPlay() {
		double[] input = new double[9];
		int k = 0;
		int[][] gridTable = game.getGrid();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				input[k] = gridTable[i][j];
				System.out.println("input["+k+"] = "+input[k]);
				k++;
			}
		}
		Coup coup = new Coup(9, "game");
		coup.in = input;
		coup.out = ControllerGame.model.forwardPropagation(coup.in);
		
		double[][] output = new double[3][3];
		k = 0;
		int prochain_coup = 0;
		
		while(input[k] != 0.0 && k < 9)
			k++;
		
		prochain_coup = k;
		k++;
		while(coup.out[k] > coup.out[prochain_coup] && k < 8) {
			if(input[k] == 0.0) {
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
