package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import util.Utils;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ai.Coup;
import ai.MultiLayerPerceptron;


public class ControllerAIGame implements Initializable{
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	private Label currentPlayer;
	
	private Game game;
	private int turn;
	
	private MultiLayerPerceptron model;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	  ObservableList<Node> children = gameGrid.getChildren();
	  
	  game = new Game();
	  turn = -1;  // player X starts

	  for (Node node : children) {
		  node.setOnMouseClicked(e -> {
			  System.out.println("onMouseClicked called");

			  turn = -1;  // player X starts
			  
			  if (game.isOver()) return;
			  
			  Integer col = gameGrid.getColumnIndex(node);
			  Integer row = gameGrid.getRowIndex(node);
			  
			  String path = "./assets/";
			  String imageUrl = "unown_x.png";
			  
			  // if it's player X turn (the human)
			  imageUrl = "unown_x.png";
			  
			  if (col == null)
				  col = 0;
			  
			  if (row == null)
				  row = 0;
			  
			  System.out.println("player x -> "+turn+" coordinates "+ row+", "+col);

			  ImageView nodeImage = (ImageView) node;
			  
			  // updates the cell's image
			  nodeImage.setImage(new Image(path+"unown_x.png"));

			  Optional<Integer> winner = game.setCellValue(row, col, turn);
			  
			  if (!winner.isEmpty()) {
				  
				  if (winner.get() == -1)
					  System.out.println("The winner is player X !");
				  
				  else 
					  System.out.println("The winner is player O !");
				  
				  game.setGameOver(true);
				  
			  }
			  
			  // Calling the ai to play
			  int k = aiPlay();
			  System.out.println("k = "+k);
			  turn = 1;
			  col = k%3;
			  row = (k - col)/3;
			  System.out.println("player o -> "+turn+" coordinates "+row+", "+col);
			
			  winner = game.setCellValue(row, col, turn);
			  imageUrl = "unown_o.png";
			  nodeImage = (ImageView) gameGrid.getChildren().get(k);
			  
			  // updates the cell's image
			  System.out.println("image not set yet ...");
			  nodeImage.setImage(new Image(path+"unown_o.png"));
			  System.out.println("image set");
			  if (!winner.isEmpty()) {
				  
				  if (winner.get() == -1)
					  System.out.println("The winner is player X !");
				  
				  else 
					  System.out.println("The winner is player O !");
				  
				  game.setGameOver(true);
				  
			  }
			  
			  turn = -1;
			  imageUrl = "unown_x.png";
			  
		  });
		  
	  }
	}
	
	public void setModel(MultiLayerPerceptron model) {
		this.model = model;
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
		coup.out = model.forwardPropagation(coup.in);
		
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
		int j = prochain_coup%3;
		int i = (prochain_coup - j)/3;
		
		int[] aiMove = {i,j};
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

}
